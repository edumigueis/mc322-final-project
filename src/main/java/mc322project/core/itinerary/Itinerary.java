package mc322project.core.itinerary;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import mc322project.entities.City;

@JacksonXmlRootElement(localName = "itinerary")
public class Itinerary {
    @JacksonXmlProperty(localName = "city")
    private City city;
    @JacksonXmlProperty(localName = "start")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;
    @JacksonXmlProperty(localName = "end")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;
    @JacksonXmlElementWrapper(localName = "itinerary-days")
    @JacksonXmlProperty(localName = "itinerary-day")
    private List<ItineraryDay> itineraryDayList;
    private int duration;

    public Itinerary(City city, LocalDate startDate, LocalDate endDate, List<ItineraryDay> itineraryDayList) {
        validateDates(startDate, endDate);
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
        this.itineraryDayList = itineraryDayList;
        this.updateDuration();
    }

    public Itinerary() {
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
        if (startDate == null && endDate == null)
            throw new IllegalArgumentException("Start date and end date must not be null.");

        if (startDate != null && endDate != null)
            if (startDate.isAfter(endDate))
                throw new IllegalArgumentException("Start date must be before or equal to end date.");
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
