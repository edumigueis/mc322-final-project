package core.itinerary;

import entities.Hotel;
import entities.activities.Activity;

import java.time.LocalTime;
import java.util.LinkedList;

public class ItineraryDay {
    TimeSlot root;
    LocalTime startOfDay;
    LocalTime endOfDay;
    Hotel hotel;

    public ItineraryDay(TimeSlot root, LocalTime startOfDay, LocalTime endOfDay, Hotel hotel) {
        this.root = root;
        this.startOfDay = startOfDay;
        this.endOfDay = endOfDay;
        this.hotel = hotel;
    }

    public ItineraryDay() {
        this.startOfDay = LocalTime.of(0, 0);
        this.endOfDay = LocalTime.of(23, 59);
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

    public void addActivity(TimeSlot slot) {
    }
}
