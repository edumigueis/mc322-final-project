package entities;

import helpers.Location;

public class Hotel {
    private Location location;
    private String name;

    public Hotel(Location location, String name) {
        this.location = location;
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "location=" + location +
                ", name='" + name + '\'' +
                '}';
    }
}
