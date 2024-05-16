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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<ItineraryDay> getItineraryDayList() {
        return itineraryDayList;
    }

    public void setItineraryDayList(List<ItineraryDay> itineraryDayList) {
        this.itineraryDayList = itineraryDayList;
    }
}
