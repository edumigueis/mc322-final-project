package entities;

import java.util.List;

import entities.activities.I_Activity;
import helpers.Location;

public class City {
    private String name;
    private String description;
    private String thumbImageUrl;
    private List<I_Activity> thingsToDo;
    private List<Hotel> hotels;
    private Location location;

    public City(String name, String description, String thumbImageUrl, List<I_Activity> thingsToDo, List<Hotel> hotels, Location loc) {
        this.name = name;
        this.description = description;
        this.thumbImageUrl = thumbImageUrl;
        this.thingsToDo = thingsToDo;
        this.hotels = hotels;
        this.location = loc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbImageUrl() {
        return thumbImageUrl;
    }

    public void setThumbImageUrl(String thumbImageUrl) {
        this.thumbImageUrl = thumbImageUrl;
    }

    public List<I_Activity> getThingsToDo() {
        return thingsToDo;
    }

    public void setThingsToDo(List<I_Activity> thingsToDo) {
        this.thingsToDo = thingsToDo;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", thumbImageUrl='" + thumbImageUrl + '\'' +
                ", thingsToDo=" + thingsToDo +
                ", hotels=" + hotels +
                '}';
    }
}
