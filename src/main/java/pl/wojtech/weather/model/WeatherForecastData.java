package pl.wojtech.weather.model;

/**
 * Created by Wojtech on 2018-02-26.
 */
public class WeatherForecastData {
    private float temperature;
    private float pressure;
    private int humidity;
    private long forecastTimeStamp;

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getPressure() {
        return pressure;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setForecastTimeStamp(long forecastTimeStamp) {
        this.forecastTimeStamp = forecastTimeStamp;
    }

    public long getForecastTimeStamp() {
        return forecastTimeStamp;
    }
}
