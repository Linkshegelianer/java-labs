package exercise;

import exercise.daytimes.Daytime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
public class WelcomeController {
    @Autowired
    Meal meal;

    @Autowired
    Daytime time; // apply to the Daytime object from MyApplicationConfig

    @GetMapping("/daytime")
    public String mealMessage() {
        String now = time.getName(); // each Daytime implementation has getName(), which returns String
        String currentMeal = meal.getMealForDaytime(now);
        return String.format("It is %s now. Enjoy your %s", now, currentMeal);
    }

}
// END
