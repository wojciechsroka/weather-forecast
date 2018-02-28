package pl.wojtech.weather.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.wojtech.weather.model.ForecastDataType;
import pl.wojtech.weather.model.WeatherForecastData;
import pl.wojtech.weather.model.WeatherForecastDataSeries;
import pl.wojtech.weather.model.json.WeatherForecastJSON;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Wojtech on 2018-02-27.
 */
@Service
public class DefaultWeatherDataBuilder implements WeatherDataBuilder {

    Logger logger = LoggerFactory.getLogger(DefaultWeatherDataBuilder.class);

    private static final float KELVIN_RATIO = 273.15F;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    public WeatherForecastDataSeries buildForecastDataBundle(List<WeatherForecastData> weatherForecastData, ForecastDataType forecastDataType){

        List<Float> data = weatherForecastData.stream().map(weatherForecastDataRecord -> getData(weatherForecastDataRecord,forecastDataType)).collect(Collectors.toList());
        List<String> dates = weatherForecastData.stream().map(weatherForecastDataRecord -> getDate(weatherForecastDataRecord)).collect(Collectors.toList());
        WeatherForecastDataSeries weatherForecastDataSeries = new WeatherForecastDataSeries();
        weatherForecastDataSeries.setX(dates.toArray(new String[0]));
        weatherForecastDataSeries.setY(data.toArray(new Float[0]));

        return weatherForecastDataSeries;
    }

    private String getDate(WeatherForecastData weatherForecastDataRecord) {
        Long timestamp = weatherForecastDataRecord.getForecastTimeStamp();
        Date date = new Date(timestamp * 1000);
        return simpleDateFormat.format(date);
    }

    private Float getData(WeatherForecastData weatherForecastDataRecord, ForecastDataType forecastDataType) {
        switch (forecastDataType){
            case HUMIDITY:
                return Float.valueOf(weatherForecastDataRecord.getHumidity());
            case PRESSURE:
                return weatherForecastDataRecord.getPressure();
            case TEMPERATURE:
                return weatherForecastDataRecord.getTemperature() - KELVIN_RATIO;
            default:
                throw new RuntimeException("Forecast data type " + forecastDataType + " not supported!");
        }
    }

    @Override
    public List<WeatherForecastData> parseWeatherData(WeatherForecastJSON weatherForecastJSON) {
        List<WeatherForecastData> weatherForecastData = new ArrayList<>();

        if(weatherForecastJSON.getList() == null) {
            logger.warn("Empty data from remote api!");
            return weatherForecastData;
        }
        for(pl.wojtech.weather.model.json.List list : weatherForecastJSON.getList()){
            String tempStr = list.getMain().getTemp();
            String humidityStr = list.getMain().getHumidity();
            String pressureStr = list.getMain().getPressure();
            String dateTime = list.getDt();

            WeatherForecastData weatherForecastRecord = new WeatherForecastData();
            weatherForecastRecord.setForecastTimeStamp(Long.parseLong(dateTime));
            weatherForecastRecord.setTemperature(Float.parseFloat(tempStr));
            weatherForecastRecord.setPressure(Float.parseFloat(pressureStr));
            weatherForecastRecord.setHumidity(Integer.parseInt(humidityStr));

            weatherForecastData.add(weatherForecastRecord);
        }

        return weatherForecastData;
    }
}
