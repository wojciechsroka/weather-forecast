package pl.wojtech.weather.model;

import java.util.Map;

/**
 * Created by Wojtech on 2018-02-28.
 */
public class WeatherForecastDataBundle {
    private Map<String,WeatherForecastDataSeries> weatherForecastDataSeries;

    public Map<String, WeatherForecastDataSeries> getWeatherForecastDataSeries() {
        return weatherForecastDataSeries;
    }

    public void setWeatherForecastDataSeries(Map<String, WeatherForecastDataSeries> weatherForecastDataSeries) {
        this.weatherForecastDataSeries = weatherForecastDataSeries;
    }
}
