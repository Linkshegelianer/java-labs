package exercise;

import java.util.List;
import java.util.stream.Collectors;

public class App {

    public static List<String> buildApartmentsList(List<Home> apartments, int n) {
        List<String> result = apartments.stream()
                .sorted((home1, home2) -> Double.compare(home1.getArea(), home2.getArea()))
                .limit(n)
                .map(home -> home.toString())
                .collect(Collectors.toList());

        return result;
    }
}
// END
