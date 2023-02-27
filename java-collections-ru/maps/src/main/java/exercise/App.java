package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public static App {
    public static Map<String, Integer> getWordCount(String sentence) {
        Map<String, Integer> wordCount = new HashMap<>();
        String[] words = sentence.split(" ");

        for (String word : words) {
            if (wordCount.containsKey(word)) {
                int count = wordCount.get(word);
                wordCount.put(word, count + 1);
        } else {
            wordCount.put(word, 1);
        }
        }
        return wordCount;
    }

    public static String toString(Map<String, Integer> hashMap) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            sb.append("  ");
            sb.append(entry.getKey());
            sb.append(": ");
            sb.append(entry.getValue());
            sb.append(",\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
//END
