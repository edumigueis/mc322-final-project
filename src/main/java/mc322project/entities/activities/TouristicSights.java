package mc322project.entities.activities;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import mc322project.helpers.BusinessHours;
import mc322project.helpers.location.Location;

@JacksonXmlRootElement(localName = "touristic")
public class TouristicSights extends Places{
    @JacksonXmlElementWrapper(localName = "images")
    @JacksonXmlProperty(localName = "url")
    private List<String> images;

    public TouristicSights(Location location, String name, BusinessHours openTime, String description, String imageThumbURL, double price, List<String>images) {
        super(location, name, openTime, description, imageThumbURL, Categories.TOURISTIC_SIGHTS, price);
        this.images = images;
    }

    public TouristicSights(){}

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
