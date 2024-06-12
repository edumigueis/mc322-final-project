package entities.activities;

import helpers.BusinessHours;
import helpers.Location;

public class Places implements I_Activity {
    private String name;
    private String imageThumbURL;
    private Location location;
    private BusinessHours openTime;
    private String description;

    //filter attributes
    private Categories category;
    private double price;

    public Places(Location location, String name, BusinessHours openTime, String description, String imageThumbURL, Categories category, double price) {
        this.name = name;
        this.location = location;
        this.openTime = openTime;
        this.description = description;
        this.imageThumbURL = imageThumbURL;
        this.category = category;
        this.price = price;
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

    public Categories getCategory() {
        return this.category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
