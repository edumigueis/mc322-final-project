package entities.activities;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import helpers.BusinessHours;
import helpers.location.Location;

@JacksonXmlRootElement(localName = "parks")
public class Parks extends Places {
    public Parks(Location location, String name, BusinessHours openTime, String description, double price){
        super(location, name, openTime, description, description, Categories.PARKS, price);
    }
    public Parks(){}
}
