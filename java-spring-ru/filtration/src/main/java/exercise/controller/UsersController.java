package exercise.controller;
import java.util.List; // added
import exercise.model.User;
import exercise.model.QUser;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

// Зависимости для самостоятельной работы
// import org.springframework.data.querydsl.binding.QuerydslPredicate;
// import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    // BEGIN
    @GetMapping("")
    public Iterable<User> filterUsers(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName) {

        // automatically generated query type that correspond to the entities in the domain model
        QUser qUser = QUser.user;

        // containsIgnoreCase() means find the given substring
        // equalsIgnoreCase() means find exactly the same String
        if (firstName != null && lastName != null) {
            return userRepository.findAll(qUser.firstName.containsIgnoreCase(firstName)
                    .and(QUser.user.lastName.containsIgnoreCase(lastName)));
        } else if (firstName != null) {
            return userRepository.findAll(qUser.firstName.containsIgnoreCase(firstName));
        } else if (lastName != null) {
            return userRepository.findAll(qUser.lastName.containsIgnoreCase(lastName));
        } else {
            return userRepository.findAll();
        }
    }
    // END
}

