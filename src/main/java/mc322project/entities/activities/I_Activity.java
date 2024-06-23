package mc322project.entities.activities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import mc322project.entities.tours.AdventureTour;
import mc322project.entities.tours.CityTour;
import mc322project.entities.tours.FoodTour;
import mc322project.helpers.location.Location;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Museum.class, name = "MUSEUMS"),
        @JsonSubTypes.Type(value = Parks.class, name = "PARKS"),
        @JsonSubTypes.Type(value = Restaurant.class, name = "RESTAURANTS"),
        @JsonSubTypes.Type(value = TouristicSights.class, name = "TOURISTIC_SIGHTS"),
        @JsonSubTypes.Type(value = Theaters.class, name = "THEATERS"),
        @JsonSubTypes.Type(value = CityTour.class, name = "CITY_TOUR"),
        @JsonSubTypes.Type(value = AdventureTour.class, name = "ADVENTURE_TOUR"),
        @JsonSubTypes.Type(value = FoodTour.class, name = "FOOD_TOUR"),
})
public interface I_Activity {

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String name);

    Location getLocation();

    void setLocation(Location location);

    double getPrice();

    void setPrice(double price);

    String getImageThumbURL();

    void setImageThumbURL(String name);
}
