package exercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.CityNotFoundException;
import exercise.HttpClient;
import exercise.model.City;
import exercise.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
    public Map<String, String> getWeather(Long id) throws JsonProcessingException {
        String weatherURL = "http://weather/api/v2/cities/";
        City city = cityRepository.findById(id)
                .orElseThrow(()-> new CityNotFoundException("City with id " + id + " not found"));
        return new ObjectMapper().readValue(client.get(weatherURL + city.getName()),
                new TypeReference<>() {
                });
    }
    // END
}
