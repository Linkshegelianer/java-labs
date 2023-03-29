package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage {

    private Map<String, String> mainStorage;

    public InMemoryKV(Map<String, String> mainStorage) {
        this.mainStorage = new HashMap<>(mainStorage);
    }

    @Override
    public void set(String key, String value) {
        mainStorage.put(key, value);
    }

    @Override
    public void unset(String key) {
        mainStorage.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        return mainStorage.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(mainStorage);
    }
}
// END
