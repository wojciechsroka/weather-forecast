package pl.wojtech.weather.service;

import pl.wojtech.weather.model.ForecastDataType;
import pl.wojtech.weather.model.WeatherForecastData;
import pl.wojtech.weather.model.WeatherForecastDataSeries;
import pl.wojtech.weather.model.json.WeatherForecastJSON;

import java.util.List;

/**
 * Created by Wojtech on 2018-03-01.
 */
public interface WeatherDataBuilder {

    /**
     * Builds forecast data series according to data type (eg. temperature, humidity)
     * @param weatherForecastData - series of parsed raw forecast data
     * @param forecastDataType - type of forecast data
     * @return data prepared to be displayed to page
     */
    WeatherForecastDataSeries buildForecastDataBundle(List<WeatherForecastData> weatherForecastData, ForecastDataType forecastDataType);

    /**
     *
     * @param weatherForecastJSON - raw json data retrieved from remote API
     * @return series of parsed raw forecast data
     */
    List<WeatherForecastData> parseWeatherData(WeatherForecastJSON weatherForecastJSON);
}
