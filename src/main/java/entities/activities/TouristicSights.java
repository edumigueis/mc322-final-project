package entities.activities;

import java.util.List;

import helpers.BusinessHours;
import helpers.Location;

public class TouristicSights extends Places{
    private List<String> images;

    public TouristicSights(Location location, String name, BusinessHours openTime, String description, String imageThumbURL, double price, List<String>images) {
        super(location, name, openTime, description, imageThumbURL, Categories.TOURISTIC_SIGHTS, price);
        this.images = images;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
