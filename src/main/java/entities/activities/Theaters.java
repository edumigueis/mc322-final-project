package entities.activities;

import java.util.ArrayList;

import helpers.BusinessHours;
import helpers.Location;

public class Theaters extends Places{
    private ArrayList<String> schedule;

    public Theaters(Location location, String name, BusinessHours openTime, String description, double price, ArrayList<String> schedule){
        super(location, name, openTime, description, description, Categories.THEATERS, price);
        this.schedule = schedule;
    }

    public ArrayList<String> getSchedule(){
        return this.schedule;
    }

    public void setSchedule(ArrayList<String> schedule){
        this.schedule = schedule;
    }
}
