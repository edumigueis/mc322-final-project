package core.itinerary;

import objects.City;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public class Itinerary {
    City city;
    Date startDate;
    Date endDate;
    List<ItineraryDay> itineraryDayList;

    public Itinerary(City city, Date startDate, Date endDate, List<ItineraryDay> itineraryDayList) {
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
        this.itineraryDayList = itineraryDayList;
    }
}
