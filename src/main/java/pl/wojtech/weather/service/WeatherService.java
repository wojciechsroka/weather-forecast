package pl.wojtech.weather.service;

import pl.wojtech.weather.model.WeatherForecastDataBundle;
import pl.wojtech.weather.model.json.WeatherForecastJSON;

/**
 * Created by Wojtech on 2018-03-01.
 */
public interface WeatherService {

    /**
     *  Callsr remote api for data
     * @param location city name which data is being retrieved
     * @return raw json data
     */
    WeatherForecastJSON getWeatherDataFromRemoteAPI(String location);

    /**
     * Parse and build weather forecast data ready to being cached
     * @param location
     * @return weather forecast data ready to being cached
     */
    WeatherForecastDataBundle getWeatherForecastData(String location);
}
