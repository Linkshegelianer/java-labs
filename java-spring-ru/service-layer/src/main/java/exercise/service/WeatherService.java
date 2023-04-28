package exercise.service;

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
    // send GET-request to URL, get JSON and return JSON
    public Map<String, String> getWeatherData(long id) {

        // find needed city by id
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException("City not found"));

        // find name of the given city
        String cityName = city.getName();
        // adress to find data from
        String url = "http://weather/api/v2/cities/" + cityName;

        ObjectMapper mapper = new ObjectMapper();
        // send GET-request to the needed url
        String responce = client.get(url);

        Map<String, String> result;

        try {
            result = mapper.readValue(responce, Map.class); // in which class the value will be transformed
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }
    // END
}
