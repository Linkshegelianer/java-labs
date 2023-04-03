package exercise;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.io.IOException;

// BEGIN
public class App {

    public static void save(Path filePath, Car car) throws IOException {
        String serializedCar = Car.serialize(car); // call these methods as static methods
        byte[] serializedBytes = serializedCar.getBytes();
        Files.write(filePath, serializedBytes, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    }

    public static Car extract(Path filePath) throws IOException {
        byte[] fileBytes = Files.readAllBytes(filePath);
        String serializedCar = new String(fileBytes);
        return Car.unserialize(serializedCar);
    }
}
// END
