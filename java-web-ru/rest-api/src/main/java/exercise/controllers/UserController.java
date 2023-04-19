package exercise.controllers;

import io.javalin.http.Context;
import io.javalin.apibuilder.CrudHandler;
import io.ebean.DB;
import java.util.List;

import exercise.domain.User;
import exercise.domain.query.QUser;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

public class UserController implements CrudHandler {

    public void getAll(Context ctx) {
        // BEGIN
        List<User> users = new QUser() // QUser represents table (from ORM topic)
                .orderBy()
                .id.asc() // order by id in ascending order
                .findList();
        String json = DB.json().toJson(users); // returns json from List<User> users
        ctx.json(json); // send json as string as a response to the client
        // END
    };

    public void getOne(Context ctx, String id) {
        // BEGIN
        User user = new QUser()
                .id.equalTo(Long.parseLong(id)) // since id is String here
                .findOne();
        String json = DB.json().toJson(user); // do the same thing as in getAll()
        ctx.json(json);
        // END
    };

    public void create(Context ctx) {

        // BEGIN
        // retrieve body of the HTTP-request, body is JSON string containing the data for the new User
        String body = ctx.body();
        User user = DB.json().toBean(User.class, body); // deserialize JSON String into new User object
        ctx.bodyValidator(User.class) // validate the previously created user
                .check(usr -> !usr.getFirstName().isEmpty(), "Firstname cannot be empty")
                .check(usr -> !usr.getLastName().isEmpty(), "Lastname cannot be empty")
                .check(usr -> EmailValidator.getInstance().isValid(usr.getEmail()), "Lastname cannot be empty")
                .check(usr -> StringUtils.isNumeric(usr.getPassword()), "Password must contain only numbers")
                .check(usr -> usr.getPassword().length() > 4, "Amount of chars in password should be greater that 4")
                .get();
        user.save();
        // END
    };

    public void update(Context ctx, String id) {
        // BEGIN
        String body = ctx.body();
        User user = DB.json().toBean(User.class, body); // deserialize JSON String into new User object
        user.setId(id);
        user.update();
        // END
    };

    public void delete(Context ctx, String id) {
        // BEGIN
        new QUser()
                .id.equalTo(Long.parseLong(id)) // .id.equalTo(Long.parseLong(id))
                .delete();
        // END
    };
}
