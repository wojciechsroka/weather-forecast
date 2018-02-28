package pl.wojtech.weather;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wojtech on 2018-02-27.
 */

public class WeatherForecastConfiguration{
    private static List<String> cities;

    private static Map<String,String> cityCountryData;

    static {
        cities = Arrays.asList("New York", "Washington", "London");
        cityCountryData = new HashMap<>();
        cityCountryData.put("New York", "us");
        cityCountryData.put("Washington", "us");
        cityCountryData.put("London", "uk");

    }

    public static List<String> getCities() {
        return cities;
    }

    public static Map<String, String> getCityCountryData() {
        return cityCountryData;
    }
}
