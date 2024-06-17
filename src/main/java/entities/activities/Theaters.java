package entities.activities;

import java.util.ArrayList;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import helpers.BusinessHours;
import helpers.Location;

@JacksonXmlRootElement(localName = "theater")
public class Theaters extends Places {
    @JacksonXmlProperty(localName = "schedule")
    private ArrayList<String> schedule;

    public Theaters(Location location, String name, BusinessHours openTime, String description, double price, String imageThumbURL, ArrayList<String> schedule){
        super(location, name, openTime, description, imageThumbURL, Categories.THEATERS, price);
        this.schedule = schedule;
    }

    public Theaters(){}

    public ArrayList<String> getSchedule(){
        return this.schedule;
    }

    public void setSchedule(ArrayList<String> schedule){
        this.schedule = schedule;
    }
}
