package exercise.repository;

import exercise.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository // interface responsible for data access and storage
public interface CityRepository extends CrudRepository<City, Long> {

    // BEGIN
    // метод, который позволит извлечь города, имя которых начинается с указанных символов без учета регистра
    List<City> findByNameStartingWithIgnoreCase(String substring);

    // метод, который позволит извлечь все города и отсортировать их по имени в прямом порядке
    List<City> findAllByOrderByNameAsc(); // ascending (smallest value first)
    // END
}
