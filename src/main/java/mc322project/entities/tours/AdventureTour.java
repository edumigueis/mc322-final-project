package mc322project.entities.tours;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import mc322project.entities.activities.I_Activity;
import mc322project.helpers.location.Location;

import java.util.List;

@JacksonXmlRootElement(localName = "adventure-tour")
public class AdventureTour extends Tour{
    public AdventureTour(String name, String imageThumbURL, Location location, String description, String language, List<I_Activity> attractionList, TourType category, double price, String type) {
        super(name, imageThumbURL, location, description, language, attractionList, category, price, type);
    }

    public AdventureTour(){}
}
