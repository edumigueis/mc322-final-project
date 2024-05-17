package core.itinerary;

import entities.Hotel;

import java.time.LocalTime;
import java.util.LinkedList;

public class ItineraryDay {
    LinkedList<TimeSlot> itinerary;
    TimeSlot root;
    LocalTime startOfDay;
    LocalTime endOfDay;
    Hotel hotel;

    public LinkedList<TimeSlot> getItinerary() {
        return itinerary;
    }

    public void setItinerary(LinkedList<TimeSlot> itinerary) {
        this.itinerary = itinerary;
    }

    public TimeSlot getRoot() {
        return root;
    }

    public void setRoot(TimeSlot root) {
        this.root = root;
    }

    public LocalTime getStartOfDay() {
        return startOfDay;
    }

    public void setStartOfDay(LocalTime startOfDay) {
        this.startOfDay = startOfDay;
    }

    public LocalTime getEndOfDay() {
        return endOfDay;
    }

    public void setEndOfDay(LocalTime endOfDay) {
        this.endOfDay = endOfDay;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
