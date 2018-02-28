package pl.wojtech.weather.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.wojtech.weather.WeatherForecastConfiguration;
import pl.wojtech.weather.model.WeatherForecastDataBundle;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Wojtech on 2018-02-28.
 */
@Service
public class ConcurrentMapWeatherForecastCacheService implements WeatherForecastCacheService {

    Logger logger = LoggerFactory.getLogger(ConcurrentMapWeatherForecastCacheService.class);

    @Autowired
    private WeatherService weatherService;


    private ConcurrentHashMap<String,WeatherForecastDataBundle> cachedData;

    public ConcurrentMapWeatherForecastCacheService(){
        cachedData = new ConcurrentHashMap<>();
    }

    @Scheduled(fixedRate = 60000)
    public void cacheDataFromRemoteDataSource() {
        logger.debug("Fetching data from remote api to cache");
        for(String location : WeatherForecastConfiguration.getCities()){
            WeatherForecastDataBundle weatherForecastDataBundle = weatherService.getWeatherForecastData(location);
            cachedData.put(location,weatherForecastDataBundle);
        }
    }

    @Override
    public WeatherForecastDataBundle getCachedData(String location){
        return cachedData.get(location);
    }



}
