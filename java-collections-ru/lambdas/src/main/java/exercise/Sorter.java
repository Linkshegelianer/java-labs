package exercise;

import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

// BEGIN
public class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        return users.stream()
                .filter(user -> user.get("gender").equals("male")) // find only men
                .sorted(Comparator.comparing(user -> user.get("birthday"))) // find the biggest birthday which are written like "1980-11-23"
                .map(user -> user.get("name")) // map each user in the stream to their name
                .collect(Collectors.toList()); // collect data to a List
    }
}
// END
