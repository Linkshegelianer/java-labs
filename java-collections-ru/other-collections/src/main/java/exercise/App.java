package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

// BEGIN
public class App {

    public static LinkedHashMap<String, String> genDiff(Map<String, Object> first, Map<String, Object> second) {
        LinkedHashMap<String, String> diff = new LinkedHashMap<>();
        Set<String> keys = new TreeSet<>(first.keySet());
        keys.addAll(second.keySet());

        for (String key : keys) {
            Object firstValue = first.get(key);
            Object secondValue = second.get(key);

            if (!first.containsKey(key)) {
                diff.put(key, "added");
            } else if (!second.containsKey(key)) {
                diff.put(key, "deleted");
            } else if (!firstValue.equals(secondValue)) {
                diff.put(key, "changed");
            } else {
                diff.put(key, "unchanged");
            }
        }

        return diff;
    }
}
//END
