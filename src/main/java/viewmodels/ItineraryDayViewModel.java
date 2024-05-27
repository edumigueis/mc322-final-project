package viewmodels;

import core.itinerary.ItineraryDay;
import entities.Hotel;
import entities.activities.Activity;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ItineraryDayViewModel {

    private ItineraryDay itineraryDay;
    private final ObjectProperty<LocalDateTime> startOfDayProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDateTime> endOfDayProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<Hotel> hotelProperty = new SimpleObjectProperty<>();
    private final ObservableList<Activity> activities = FXCollections.observableArrayList();

    public ItineraryDayViewModel(LocalDate date) {
        this(new ItineraryDay(date));
    }

    public ItineraryDayViewModel(ItineraryDay itineraryDay) {
        this.itineraryDay = itineraryDay;
        this.startOfDayProperty.set(itineraryDay.getStartOfDay());
        this.endOfDayProperty.set(itineraryDay.getEndOfDay());
        this.hotelProperty.set(itineraryDay.getHotel());
    }

    // Getters for properties to bind with the View
    public ObjectProperty<LocalDateTime> startOfDayProperty() {
        return startOfDayProperty;
    }

    public ObjectProperty<LocalDateTime> endOfDayProperty() {
        return endOfDayProperty;
    }

    public ObjectProperty<Hotel> hotelProperty() {
        return hotelProperty;
    }

    public ObservableList<Activity> getActivities() {
        return activities;
    }
    /*
    TO DO
    // Methods to update the model from the ViewModel
    public void updateItineraryDay() {
        itineraryDay.setStartOfDay(startOfDayProperty.get());
        itineraryDay.setEndOfDay(endOfDayProperty.get());
        itineraryDay.setHotel(hotelProperty.get());
        // Assuming TimeSlot has a method to set all activities
        itineraryDay.getRoot().setActivities(activities);
    }*/

    public void addActivity(Activity activity) {
        // Assuming TimeSlot has a method to add an activity
        //itineraryDay.getRoot().addActivity(activity);
        activities.add(activity);
    }/*

    // Additional methods to manipulate activities
    public void removeActivity(Activity activity) {
        // Assuming TimeSlot has a method to remove an activity
        itineraryDay.getRoot().removeActivity(activity);
        activities.remove(activity);
    }*/
}
