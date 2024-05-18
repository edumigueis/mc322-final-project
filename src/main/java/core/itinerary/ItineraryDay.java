package core.itinerary;

import entities.Hotel;
import entities.activities.Activity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;

public class ItineraryDay {
    TimeSlot root;
    LocalDateTime startOfDay;
    LocalDateTime endOfDay;
    Hotel hotel;

    public ItineraryDay(TimeSlot root, LocalDateTime startOfDay, LocalDateTime endOfDay, Hotel hotel) {
        this.root = root;
        this.startOfDay = startOfDay;
        this.endOfDay = endOfDay;
        this.hotel = hotel;
    }

    public ItineraryDay(LocalDate date) {
        this.startOfDay = LocalDateTime.of(date, LocalTime.of(0, 0));
        this.endOfDay = LocalDateTime.of(date, LocalTime.of(23, 59));
    }

    public TimeSlot getRoot() {
        return root;
    }

    public void setRoot(TimeSlot root) {
        this.root = root;
    }

    public LocalDateTime getStartOfDay() {
        return startOfDay;
    }

    public void setStartOfDay(LocalDateTime startOfDay) {
        this.startOfDay = startOfDay;
    }

    public LocalDateTime getEndOfDay() {
        return endOfDay;
    }

    public void setEndOfDay(LocalDateTime endOfDay) {
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
