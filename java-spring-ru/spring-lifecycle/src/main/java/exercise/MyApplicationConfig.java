package exercise;

import java.time.LocalDateTime;

import exercise.daytimes.Daytime;
import exercise.daytimes.Morning;
import exercise.daytimes.Day;
import exercise.daytimes.Evening;
import exercise.daytimes.Night;

// BEGIN
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyApplicationConfig {

    @Bean
    public Daytime currentDayTime() {
        int currentTime = LocalDateTime.now().getHour();

        if (currentTime >= 6 && currentTime < 12) {
            return new Morning();
        }

        if (currentTime >= 12 && currentTime < 18) {
            return new Day();
        }

        if (currentTime >= 18 && currentTime < 23) {
            return new Evening();
        }

        return new Night();
    }
}
// END
