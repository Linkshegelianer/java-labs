package exercise;

import lombok.Value;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper; // object for serializing objects into JSON
import java.io.IOException;
//import java.io.JsonProcessingException;

// BEGIN
@Value
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public static String serialize(Car car) throws JsonProcessingException {
        String serialized = new ObjectMapper().writeValueAsString(car);
        return serialized;
    }

    public static Car unserialize(String car) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(car, Car.class);
    }
    // END
}
