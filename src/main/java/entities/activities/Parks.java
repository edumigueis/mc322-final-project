package entities.activities;

import helpers.BusinessHours;
import helpers.Location;

public class Parks extends Places {

    public Parks(Location location, String name, BusinessHours openTime, String description, double price){
        super(location, name, openTime, description, description, Categories.PARKS, price);
    }
}
