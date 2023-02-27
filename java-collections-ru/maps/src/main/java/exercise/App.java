package exercise;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        Map<String, Integer> wordCount = new HashMap<>();
        String[] words = sentence.split(" ");
	
	if (sentence == "") {
		return Collections.emptyMap();
	}
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
        if (hashMap.size() == 0) {
		return "{}";
	}

	StringBuilder result = new StringBuilder();
        result.append("{" + "\n");
        for (Map.Entry word : hashMap.entrySet()) {
		result.append("  " + word.getKey() + ": " + word.getValue() + "\n");
        }
        result.append("}");
        return String.valueOf(result);
    }
}

//END
