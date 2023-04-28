package exercise.controller;

import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Map;


@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherService weatherService;

    // BEGIN
    @GetMapping(path = "cities/{id}")
    public Map<String, String> getCity(@PathVariable long id) { return weatherService.getWeatherData(id); }

    @GetMapping(path = "/search")
    public List<Map<String, String>> getCities(@RequestParam(required = false) String name) {

        // crate empty list and filter it depending on the provided name
        List<City> filteredCities;

        if (name == null) {
            filteredCities = cityRepository.findAllByOrderByName();
        } else {
            filteredCities = cityRepository.findByNameStartingWithIgnoreCase(name);
        }

        // create new list and fill it filtered content of the filteredCities
        List<Map<String, String>> citiesWithWeather = filteredCities.stream()
                .map(city -> {
                    Map<String, String> weather = weatherService.getWeatherData(city.getId());
                    return Map.of(
                            "name", city.getName(),
                            "temperature", weather.get("temperature")
                    );
                })
                .collect(Collectors.toList());

        return citiesWithWeather;
    }


    // END
}

