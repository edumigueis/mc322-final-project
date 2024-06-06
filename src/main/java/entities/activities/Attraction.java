package entities.activities;

import helpers.BusinessHours;
import helpers.Location;

public class Attraction implements Activity {
    private Location location;
    private String name;
    private BusinessHours openTime;
    private String description;
    private String imageThumbURL;

    public Attraction(Location location, String name, BusinessHours openTime, String description, String imageThumbURL) {
        this.location = location;
        this.name = name;
        this.openTime = openTime;
        this.description = description;
        this.imageThumbURL = imageThumbURL;
    }
    // TO DO: REMOVE
    public Attraction(String name){
        this.name = name;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public BusinessHours getOpenTime() {
        return openTime;
    }

    public void setOpenTime(BusinessHours openTime) {
        this.openTime = openTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageThumbURL() {
        return imageThumbURL;
    }

    public void setImageThumbURL(String imageThumbURL) {
        this.imageThumbURL = imageThumbURL;
    }
}
