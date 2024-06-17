package entities.activities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import helpers.Location;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "category")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Museum.class, name = "museum"),
        @JsonSubTypes.Type(value = Parks.class, name = "park"),
        @JsonSubTypes.Type(value = Restaurant.class, name = "restaurant"),
        @JsonSubTypes.Type(value = TouristicSights.class, name = "touristic"),
        @JsonSubTypes.Type(value = Theaters.class, name = "theaters"),
})
public interface I_Activity {

    String getName();
    void setName(String name);

    String getDescription();
    void setDescription(String name);

    Location getLocation();
    void setLocation(Location location);

    Categories getCategory();
    void setCategory(Categories category);

    double getPrice();
    void setPrice(double price);

    String getImageThumbURL();
    void setImageThumbURL(String name);
}
