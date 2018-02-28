package pl.wojtech.weather.model.json;

/**
 * Created by Wojtech on 2018-02-28.
 */
public class City {
    private Coord coord;

    private String id;

    private String name;

    private String population;

    private String country;

    public Coord getCoord ()
    {
        return coord;
    }

    public void setCoord (Coord coord)
    {
        this.coord = coord;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getPopulation ()
    {
        return population;
    }

    public void setPopulation (String population)
    {
        this.population = population;
    }

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [coord = "+coord+", id = "+id+", name = "+name+", population = "+population+", country = "+country+"]";
    }
}
