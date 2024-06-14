package core.itinerary;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.xml.bind.annotation.XmlRootElement;

import entities.City;

@XmlRootElement
public class Itinerary {
    City city;
    LocalDate startDate;
    LocalDate endDate;
    List<ItineraryDay> itineraryDayList;
    int duration;

    public Itinerary(City city, LocalDate startDate, LocalDate endDate, List<ItineraryDay> itineraryDayList) {
        validateDates(startDate, endDate);
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
        this.itineraryDayList = itineraryDayList;
        this.updateDuration();
    }

    public Itinerary(City city, LocalDate startDate, LocalDate endDate) {
        validateDates(startDate, endDate);
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
        this.updateDuration();
        this.itineraryDayList = new ArrayList<>();
        IntStream.range(0, duration).forEach(i -> itineraryDayList.add(new ItineraryDay(startDate.plusDays(i))));
    }

    private void updateDuration() {
        if (startDate != null && endDate != null)
            this.duration = (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }

    private void validateDates(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start date and end date must not be null.");
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be before or equal to end date.");
        }
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
        validateDates(startDate, this.endDate);
        this.startDate = startDate;
        updateDuration();
        // Update itinerary days list if the start date changes
        updateItineraryDays();
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        validateDates(this.startDate, endDate);
        this.endDate = endDate;
        updateDuration();
        // Update itinerary days list if the end date changes
        updateItineraryDays();
    }

    public List<ItineraryDay> getItineraryDayList() {
        return itineraryDayList;
    }

    public void setItineraryDayList(List<ItineraryDay> itineraryDayList) {
        this.itineraryDayList = itineraryDayList;
    }

    public int getDuration() {
        return this.duration;
    }

    private void updateItineraryDays() {
        if (itineraryDayList != null && startDate != null && endDate != null) {
            itineraryDayList.clear();
            IntStream.range(0, duration).forEach(i -> itineraryDayList.add(new ItineraryDay(startDate.plusDays(i))));
        }
    }
}
