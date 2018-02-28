package pl.wojtech.weather.model.json;

/**
 * Created by Wojtech on 2018-02-28.
 */
public class Sys {
    private String pod;

    public String getPod ()
    {
        return pod;
    }

    public void setPod (String pod)
    {
        this.pod = pod;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [pod = "+pod+"]";
    }
}
