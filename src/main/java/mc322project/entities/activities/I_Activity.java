package mc322project.entities.activities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import mc322project.helpers.location.Location;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Museum.class, name = "MUSEUMS"),
        @JsonSubTypes.Type(value = Parks.class, name = "PARKS"),
        @JsonSubTypes.Type(value = Restaurant.class, name = "RESTAURANTS"),
        @JsonSubTypes.Type(value = TouristicSights.class, name = "TOURISTIC_SIGHTS"),
        @JsonSubTypes.Type(value = Theaters.class, name = "THEATERS"),
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
