package exercise.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// BEGIN
@Getter
@Setter
@Entity
// END
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // BEGIN
    private String firstName;
    private String lastName;
    // END
}
