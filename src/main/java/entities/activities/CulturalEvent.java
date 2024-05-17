package entities.activities;

import helpers.BusinessHours;
import helpers.Location;

public class CulturalEvent extends Attraction {
    private CulturalEventType type;
    private String linkToBuy;

    public CulturalEvent(Location location, String name, BusinessHours openTime, String description, String imageThumbURL, CulturalEventType type, String linkToBuy) {
        super(location, name, openTime, description, imageThumbURL);
        this.type = type;
        this.linkToBuy = linkToBuy;
    }

    public CulturalEventType getType() {
        return type;
    }

    public void setType(CulturalEventType type) {
        this.type = type;
    }

    public String getLinkToBuy() {
        return linkToBuy;
    }

    public void setLinkToBuy(String linkToBuy) {
        this.linkToBuy = linkToBuy;
    }
}
