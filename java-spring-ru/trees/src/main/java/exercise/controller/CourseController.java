package exercise.controller;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired // dependency injection
    private CourseRepository courseRepository; // repository implements the API for data storage

    @GetMapping(path = "")
    public Iterable<Course> getCorses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    // BEGIN
    @GetMapping(path = "/{id}/previous")
    public Iterable<Course> getPreviousCourses(@PathVariable long id) {
        Course course = courseRepository.findById(id);
        String path = course.getPath(); // each Course has path with parent courses, numberic String

        if (path != null) {
            List<Long> idList = Arrays.stream(path.split("\\.")) // split every number with a dot
                    .map(Long::parseLong) // converst String into a Long object
                    .collect(Collectors.toList());
            return courseRepository.findAllById(idList); // find every couse with parent id
        }

        return Arrays.asList(); // return an empty fix-sized list
    }
    // END

}
