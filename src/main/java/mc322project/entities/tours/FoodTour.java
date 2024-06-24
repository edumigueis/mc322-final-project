package mc322project.entities.tours;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import mc322project.entities.activities.I_Activity;
import mc322project.helpers.location.Location;

import java.util.List;

@JacksonXmlRootElement(localName = "food-tour")
public class FoodTour extends Tour{
    public FoodTour(String name, String imageThumbURL, Location location, String description, String language, List<I_Activity> attractionList, TourType category, double price) {
        super(name, imageThumbURL, location, description, language, attractionList, category, price);
    }

    public FoodTour(){}
}
