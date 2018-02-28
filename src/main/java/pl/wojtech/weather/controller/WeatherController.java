package pl.wojtech.weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Wojtech on 2018-02-26.
 */
@Controller
public class WeatherController {

    @RequestMapping("/weatherForecast")
    public String weatherForecastPage() {
        return "weather";
    }

    @RequestMapping("/")
    public String weatherForecastPageDefault() {
        return "weather";
    }


}
