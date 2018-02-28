package pl.wojtech.weather.service;

import pl.wojtech.weather.model.WeatherForecastDataBundle;

/**
 * Created by Wojtech on 2018-03-01.
 */
public interface WeatherForecastCacheService {

    /**
     *
     * @param location - city name which data is being cached
     * @return cached weather forecast data
     */
    WeatherForecastDataBundle getCachedData(String location);
}
