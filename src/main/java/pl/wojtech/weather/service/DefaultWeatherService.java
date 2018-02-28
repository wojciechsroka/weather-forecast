package pl.wojtech.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

import org.springframework.web.client.RestTemplate;
import pl.wojtech.weather.WeatherForecastConfiguration;
import pl.wojtech.weather.model.ForecastDataType;
import pl.wojtech.weather.model.WeatherForecastData;
import pl.wojtech.weather.model.WeatherForecastDataBundle;
import pl.wojtech.weather.model.WeatherForecastDataSeries;
import pl.wojtech.weather.model.json.WeatherForecastJSON;

/**
 * Created by Wojtech on 2018-02-26.
 */

@Service
public class DefaultWeatherService implements WeatherService {

    public static final String API_OPENWEATHERMAP_ADDRESS = "http://api.openweathermap.org/data/2.5/forecast?";
    @Value("${appid}")
    private String weatherApiKey;

    @Autowired
    private WeatherForecastCacheService weatherForecastCacheService;

    @Autowired
    WeatherDataBuilder weatherDataBuilder;

    @Override
    public WeatherForecastJSON getWeatherDataFromRemoteAPI(String location){
        RestTemplate restTemplate = new RestTemplate();
        String country = WeatherForecastConfiguration.getCityCountryData().get(location);
        String query = "q=" + location + "," + country;
        ResponseEntity<WeatherForecastJSON> weatherForecastResponse = restTemplate.getForEntity(API_OPENWEATHERMAP_ADDRESS + query + "&appid=" + weatherApiKey, WeatherForecastJSON.class);

        return weatherForecastResponse.getBody();
    }

    @Override
    public WeatherForecastDataBundle getWeatherForecastData(String location){
        WeatherForecastJSON result = getWeatherDataFromRemoteAPI(location);
        List<WeatherForecastData> weatherForecastData = weatherDataBuilder.parseWeatherData(result);
        WeatherForecastDataSeries weatherForecastDataSeriesHumidity = weatherDataBuilder.buildForecastDataBundle(weatherForecastData, ForecastDataType.HUMIDITY);
        WeatherForecastDataSeries weatherForecastDataSeriesTemperature = weatherDataBuilder.buildForecastDataBundle(weatherForecastData, ForecastDataType.TEMPERATURE);
        WeatherForecastDataSeries weatherForecastDataSeriesPressure = weatherDataBuilder.buildForecastDataBundle(weatherForecastData, ForecastDataType.PRESSURE);

        Map<String,WeatherForecastDataSeries> weatherForecastDataSeries = new HashMap<>();
        weatherForecastDataSeries.put(ForecastDataType.HUMIDITY.toString(),weatherForecastDataSeriesHumidity);
        weatherForecastDataSeries.put(ForecastDataType.TEMPERATURE.toString(),weatherForecastDataSeriesTemperature);
        weatherForecastDataSeries.put(ForecastDataType.PRESSURE.toString(),weatherForecastDataSeriesPressure);

        WeatherForecastDataBundle weatherForecastDataBundle = new WeatherForecastDataBundle();
        weatherForecastDataBundle.setWeatherForecastDataSeries(weatherForecastDataSeries);

        return weatherForecastDataBundle;
    }


}
