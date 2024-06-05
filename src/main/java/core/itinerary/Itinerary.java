package core.itinerary;

import entities.City;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Itinerary {
    City city;
    LocalDate startDate;
    LocalDate endDate;
    List<ItineraryDay> itineraryDayList;
    int duration;

    public Itinerary(City city, LocalDate startDate, LocalDate endDate, List<ItineraryDay> itineraryDayList) {
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
        this.itineraryDayList = itineraryDayList;
        this.updateDuration();
    }

    public Itinerary(City city, LocalDate startDate, LocalDate endDate) {
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
        this.updateDuration();
        this.itineraryDayList = new ArrayList<ItineraryDay>();
        IntStream.range(0, duration).forEach(i -> itineraryDayList.add(new ItineraryDay(startDate.plusDays(i))));
    }

    private void updateDuration(){
        if(startDate != null && endDate != null)
            this.duration = (int)ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        updateDuration();
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        updateDuration();
    }

    public List<ItineraryDay> getItineraryDayList() {
        return itineraryDayList;
    }

    public void setItineraryDayList(List<ItineraryDay> itineraryDayList) {
        this.itineraryDayList = itineraryDayList;
    }

    public int getDuration(){
        return this.duration;
    }
}
