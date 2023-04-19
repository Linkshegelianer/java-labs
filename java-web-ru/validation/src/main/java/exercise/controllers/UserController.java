package exercise.controllers;

import io.javalin.http.Handler;
import java.util.List;
import java.util.Map;
import io.javalin.core.validation.Validator;
import io.javalin.core.validation.ValidationError;
import io.javalin.core.validation.JavalinValidation;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

import exercise.domain.User;
import exercise.domain.query.QUser;

public final class UserController {

    public static Handler listUsers = ctx -> {

        List<User> users = new QUser()
            .orderBy()
                .id.asc()
            .findList();

        ctx.attribute("users", users);
        ctx.render("users/index.html");
    };

    public static Handler showUser = ctx -> {
        long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);

        User user = new QUser()
            .id.equalTo(id)
            .findOne();

        ctx.attribute("user", user);
        ctx.render("users/show.html");
    };

    public static Handler newUser = ctx -> {

        ctx.attribute("errors", Map.of());
        ctx.attribute("user", Map.of());
        ctx.render("users/new.html");
    };

    // ctx - Context - HTTP request and response for a particular roure in Javalin app
    public static Handler createUser = ctx -> {
        // BEGIN
        Validator<String> firstNameValidator = ctx.formParamAsClass("firstName", String.class)
                .check(firstName -> !firstName.isEmpty(), "Firstname cannot be empty");
        Validator<String> lastNameValidator = ctx.formParamAsClass("lastName", String.class)
                .check(lastName -> !lastName.isEmpty(), "Lastname cannot be empty");
        Validator<String> emailValidator = ctx.formParamAsClass("email", String.class)
                // don't use ! since we're adding error on false
                .check(email -> EmailValidator.getInstance().isValid(email), "Email must be valid");
        Validator<String> passwordValidator = ctx.formParamAsClass("password", String.class)
                // don't use ! since we're adding error on false
                .check(password -> StringUtils.isNumeric(password), "Password must contain only numbers")
                .check(password -> password.length() > 4, "Amount of chars in password should be greater that 4");

        Map<String, List<ValidationError<?>>> errors = JavalinValidation.collectErrors(
                firstNameValidator, lastNameValidator, emailValidator, passwordValidator);

        User user = new User( // for convenience, the form fields themselves must be filled in with the entered data
                ctx.formParam("firstName"), // retrieve data submitted through an HTML form
                ctx.formParam("lastName"),
                ctx.formParam("email"),
                ctx.formParam("password")
        );

        // if list with errors is not empty, set response status 422 and show page with creating new user
        if (!errors.isEmpty()) {
            ctx.status(422);
            ctx.attribute("errors", errors);
            ctx.attribute("user", user);
            ctx.render("users/new.html");
            return;
        }
        user.save();
        ctx.sessionAttribute("flash", "Пользователь успешно добавлен");
        ctx.redirect("/users");
        // END
    };
}
