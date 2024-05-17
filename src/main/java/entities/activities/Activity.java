package entities.activities;

import helpers.Location;

public interface Activity {
    String getName();
    void setName(String name);
    Location getLocation();
    void setLocation(Location location);
}
