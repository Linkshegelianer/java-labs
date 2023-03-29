package exercise;

import java.util.Map;
import java.util.stream.Collectors;

// BEGIN
public class App {

    public static void swapKeyValue(KeyValueStorage input) {

        Map<String, String> result = input.toMap().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

        for (Map.Entry<String, String> keys : input.toMap().entrySet()) {
            input.unset(keys.getKey());
        }

        for (Map.Entry<String, String> entry : result.entrySet()) {
            input.set(entry.getKey(), entry.getValue());
        }
    }
}
// END