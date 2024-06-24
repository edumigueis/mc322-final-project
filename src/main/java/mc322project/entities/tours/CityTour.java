package mc322project.entities.tours;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import mc322project.entities.activities.I_Activity;
import mc322project.helpers.location.Location;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "city-tour")
public class CityTour extends Tour {
    @JacksonXmlProperty(localName = "includesFood")
    private boolean includesFood;

    public CityTour(String name, String imageThumbURL, Location location, String description, String language, List<I_Activity> attractionList, TourType category, double price, boolean includesFood) {
        super(name, imageThumbURL, location, description, language, attractionList, category, price);
        this.includesFood = includesFood;
    }

    public CityTour() {
    }

    public boolean includesFood() {
        return includesFood;
    }

    public void setIncludesFood(boolean includesFood) {
        this.includesFood = includesFood;
    }
}
