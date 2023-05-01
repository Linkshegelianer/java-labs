package exercise;

import java.util.HashMap; // added
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {
        MinThread minThread = new MinThread(numbers);
        MaxThread maxThread = new MaxThread(numbers);

        Map<String, Integer> result = new HashMap<>();

        minThread.start();
        LOGGER.info("MinThread started");
        maxThread.start();
        LOGGER.info("MinThread started");

        try {
            minThread.join();
            LOGGER.info("MinThread finished");
            maxThread.join();
            LOGGER.info("MaxThread finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int min = minThread.getMin();
        int max = maxThread.getMax();

        result.put("min", min);
        result.put("max", max);

        return result;
    }
    // END
}
