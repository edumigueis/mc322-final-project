package entities.activities;

import helpers.BusinessHours;
import helpers.Location;

import java.util.List;

public class Sight extends Attraction {
    private List<String> images;

    public Sight(Location location, String name, BusinessHours openTime, String description, String imageThumbURL, List<String> images) {
        super(location, name, openTime, description, imageThumbURL);
        this.images = images;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
