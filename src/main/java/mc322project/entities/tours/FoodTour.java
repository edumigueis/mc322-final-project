package mc322project.entities.tours;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import mc322project.entities.activities.I_Activity;
import mc322project.helpers.location.Location;

import java.util.List;

@JacksonXmlRootElement(localName = "food-tour")
public class FoodTour extends Tour {
    @JacksonXmlProperty(localName = "foodDescription")
    private String foodDescription;

    public FoodTour(String name, String imageThumbURL, Location location, String description, String language, List<I_Activity> attractionList, TourType category, double price, String foodDescription) {
        super(name, imageThumbURL, location, description, language, attractionList, category, price);
        this.foodDescription = foodDescription;
    }

    public FoodTour() {
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }
}
