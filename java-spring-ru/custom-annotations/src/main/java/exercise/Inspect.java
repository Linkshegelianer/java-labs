package exercise;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

// BEGIN
@Target(value = ElementType.TYPE) // отмечает класс
@Retention(RetentionPolicy.RUNTIME) // доступна в райнтайме
public @interface Inspect {
    String level() default "debug"; // принимает один аргумент level, который имеет два значения, debug по умолчанию
}
// END
