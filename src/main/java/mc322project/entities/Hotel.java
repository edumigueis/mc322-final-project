package mc322project.entities;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import mc322project.helpers.location.Location;

public class Hotel {
    @JacksonXmlProperty(localName = "location")
    private Location location;

    @JacksonXmlProperty(localName = "name")
    private String name;

    public Hotel(Location location, String name) {
        this.location = location;
        this.name = name;
    }

    public Hotel(){}

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "location=" + location +
                ", name='" + name + '\'' +
                '}';
    }
}
