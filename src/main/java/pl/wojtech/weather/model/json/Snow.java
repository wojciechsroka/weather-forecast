package pl.wojtech.weather.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Wojtech on 2018-02-28.
 */
public class Snow {

    @JsonProperty("3h")
    private String h3;

    public String get3h ()
    {
        return h3;
    }

    public void set3h (String h3)
    {
        this.h3 = h3;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [3h = "+h3+"]";
    }
}
