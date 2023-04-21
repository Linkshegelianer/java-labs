package exercise.controller;

import exercise.model.Person;
import exercise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

@RestController
@RequestMapping("/people")
public class PeopleController {

    // Автоматически заполняем значение поля
    @Autowired
    private PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    public Person getPerson(@PathVariable long id) {
        return this.personRepository.findById(id);
    }

    @GetMapping(path = "")
    public Iterable<Person> getPeople() {
        return this.personRepository.findAll();
    }

    // BEGIN
    @PostMapping(path = "")
    public void addPerson(@RequestBody Person person) {
        personRepository.save(person);
//        Person person = new Person();
//        person.setId(request.getId());
//        person.setFirstName(request.getFirstName());
//        person.setLarstName(request.getLastName());
//        userRepository.save(person);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePerson(@PathVariable long id) {
        Person person = personRepository.findById(id);
        personRepository.delete(person);
    }

    @PatchMapping("/{id}")
    public void updatePerson(@PathVariable long id, @RequestBody Person person) {
        Person updatedPerson = this.personRepository.findById(id);
        updatedPerson.setFirstName(person.getFirstName());
        updatedPerson.setLastName(person.getLastName());
        this.personRepository.save(updatedPerson);
    }

}
