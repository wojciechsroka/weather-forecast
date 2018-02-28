package pl.wojtech.weather.model.json;

/**
 * Created by Wojtech on 2018-02-28.
 */
public class Clouds {
    private String all;

    public String getAll ()
    {
        return all;
    }

    public void setAll (String all)
    {
        this.all = all;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [all = "+all+"]";
    }
}
