package entities.activities;

import helpers.BusinessHours;
import helpers.Location;
import helpers.PriceRange;

public class Restaurant implements Activity {
    private Location location;
    private String name;
    private BusinessHours openTime;
    private PriceRange priceRange;

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

    public PriceRange getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(PriceRange priceRange) {
        this.priceRange = priceRange;
    }
}
