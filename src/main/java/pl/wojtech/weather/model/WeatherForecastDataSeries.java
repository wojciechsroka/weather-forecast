package pl.wojtech.weather.model;

/**
 * Created by Wojtech on 2018-02-27.
 */
public class WeatherForecastDataSeries {
    private String name;
    private String type;
    private String yaxis;

    private Float[] y;
    private String[] x;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYaxis() {
        return yaxis;
    }

    public void setYaxis(String yaxis) {
        this.yaxis = yaxis;
    }

    public Float[] getY() {
        return y;
    }

    public void setY(Float[] y) {
        this.y = y;
    }

    public String[] getX() {
        return x;
    }

    public void setX(String[] x) {
        this.x = x;
    }
}
