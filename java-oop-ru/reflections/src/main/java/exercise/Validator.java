package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList; // only Field was imported
import java.util.List;

// BEGIN
public class Validator {
    public static List<String> validate(Address address) {
        List<String> invalidFields = new ArrayList<>();
        Field[] fields = Address.class.getDeclaredFields(); // an array containing all fields of the Address class
        for (Field field : fields) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true); // use reflection to access private fields
                try {
                    if (field.get(address) == null) {
                        invalidFields.add(field.getName());
                    }
                } catch (IllegalAccessException e) { // kinda useless since we set access to true
                    return invalidFields;
                }
            }
        }
        return invalidFields;
    }
}
// END
