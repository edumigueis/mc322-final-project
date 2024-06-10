package core.itinerary;

import entities.Hotel;
import entities.Transportation;
import entities.activities.Activity;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ItineraryDay {
    List<TimeSlot> activities = new ArrayList<>();
    LocalDateTime startOfDay;
    LocalDateTime endOfDay;
    Hotel hotel;

    public ItineraryDay(LocalDateTime startOfDay, LocalDateTime endOfDay, Hotel hotel) {
        this.startOfDay = startOfDay;
        this.endOfDay = endOfDay;
        this.hotel = hotel;
    }

    public ItineraryDay(LocalDate date) {
        this.startOfDay = LocalDateTime.of(date, LocalTime.of(0, 0));
        this.endOfDay = LocalDateTime.of(date, LocalTime.of(23, 59));
    }

    public List<TimeSlot> getActivities() {
        return activities;
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

    public void addActivity(Activity activity) {
        TimeSlot last;
        LocalTime startTrns;
        LocalTime acStart;
        Transportation transportation;

        if (!this.activities.isEmpty()) {
            last = this.activities.getLast();
            startTrns = last.getEnd();
            transportation = Transportation.betweenPlaces(startTrns, last.getData().getLocation(), activity.getLocation());
            last.setWayToNext(transportation);
            acStart = startTrns.plus(transportation.getEstimatedDuration());
        } else {
            acStart = LocalTime.of(8, 0);
            transportation = null;
        }

        TimeSlot ts = new TimeSlot(activity, transportation, acStart, acStart.plus(Duration.ofHours(1)));
        this.activities.add(ts);
    }

    public void removeActivity(Activity activity) {
        // TO DO: implement
    }

    public void setAll(List<TimeSlot> slots) {
        // TO DO: implementar - lembre-se de recalcular todos os transportes porque a idea é setar apenas uma lista de atividades
    }
}
