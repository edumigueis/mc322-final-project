package entities.activities;

import helpers.Location;

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
