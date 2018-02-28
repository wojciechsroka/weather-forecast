package pl.wojtech.weather.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.wojtech.weather.model.WeatherForecastData;
import pl.wojtech.weather.model.WeatherForecastDataBundle;
import pl.wojtech.weather.service.WeatherForecastCacheService;
import pl.wojtech.weather.service.WeatherService;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Wojtech on 2018-02-26.
 */

@RestController
public class WeatherRestController {

    Logger logger = LoggerFactory.getLogger(WeatherRestController.class);

    @Autowired
    WeatherService weatherService;

    @Autowired
    WeatherForecastCacheService weatherForecastCacheService;

    @RequestMapping("/{location}")
    public WeatherForecastData getWeatherForecast(@RequestBody String location){
        return null;
    }


    @RequestMapping(value= "/{location}/dataMap", method = GET, produces = "application/json")
    public ResponseEntity<WeatherForecastDataBundle> getWeatherForecastDataMap(@PathVariable("location") String location){

        WeatherForecastDataBundle weatherForecastDataBundle = weatherForecastCacheService.getCachedData(location);
        if(weatherForecastDataBundle != null) {
            return new ResponseEntity<>(weatherForecastDataBundle, HttpStatus.ACCEPTED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

/*
    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        logger.error("Request: " + req.getRequestURL() + " raised " + ex);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }
*/

}
