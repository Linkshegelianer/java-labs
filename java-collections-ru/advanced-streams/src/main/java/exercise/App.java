package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
public class App {

    public static String getForwardedVariables(String configFile) {
        String[] lines = configFile.split("\n");
        String variablesString = Arrays.stream(lines)
                .filter(line -> line.trim().startsWith("environment") && line.contains("X_FORWARDED_"))
                .map(line -> line.trim().substring("environment".length() + 1).replaceAll("\"", ""))
                .collect(Collectors.joining(","));
        String[] variables = variablesString.split(",");
        return Arrays.stream(variables)
                .filter(variable -> variable.startsWith("X_FORWARDED_"))
                .map(variable -> variable.replaceFirst("X_FORWARDED_", ""))
                .map(variable -> variable.replaceFirst("=", "=").trim())
                .collect(Collectors.joining(",", "", ""))
                .replaceAll("^,|,$", "");
    }

}
//END
