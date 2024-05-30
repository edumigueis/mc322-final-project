package entities.activities;

import helpers.BusinessHours;
import helpers.Location;
import helpers.PriceRange;

public class Restaurant implements Activity {
    private Location location;
    private String name;
    private String description;
    private BusinessHours openTime;
    private PriceRange priceRange;

    public Restaurant(Location location, String name, BusinessHours openTime, PriceRange priceRange, String description) {
        this.location = location;
        this.name = name;
        this.description = description;
        this.openTime = openTime;
        this.priceRange = priceRange;
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

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }


    public BusinessHours getOpenTime() {
        return openTime;
    }

    public void setOpenTime(BusinessHours openTime) {
        this.openTime = openTime;
    }

    public PriceRange getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(PriceRange priceRange) {
        this.priceRange = priceRange;
    }
}
