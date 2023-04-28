package exercise.repository;

import exercise.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository // interface responsible for data access and storage
public interface CityRepository extends CrudRepository<City, Long> {

    // BEGIN
    //  извлечь все города, имя которых начинается с указанных символов без учета регистра
    List<City> findByNameStartingWithIgnoreCase(String prefix);
    // извлечь все города и отсортировать их по имени в прямом (ascending) порядке
    List<City> findAllByOrderByName();
    // END
}
