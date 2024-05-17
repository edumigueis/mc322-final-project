package entities;

import entities.activities.Activity;

import java.util.List;

public class City {
    private String name;
    private String description;
    private String thumbImageUrl;
    private List<Activity> thingsToDo;
    private List<Hotel> hotels;

    public City(String name, String description, String thumbImageUrl, List<Activity> thingsToDo) {
        this.name = name;
        this.description = description;
        this.thumbImageUrl = thumbImageUrl;
        this.thingsToDo = thingsToDo;
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

    public List<Activity> getThingsToDo() {
        return thingsToDo;
    }

    public void setThingsToDo(List<Activity> thingsToDo) {
        this.thingsToDo = thingsToDo;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
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
