package viewmodels;

import core.itinerary.ItineraryDay;
import core.itinerary.TimeSlot;
import entities.Hotel;
import entities.activities.I_Activity;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ItineraryDayViewModel {

    private ItineraryDay itineraryDay;
    private final ObjectProperty<LocalDateTime> startOfDayProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDateTime> endOfDayProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<Hotel> hotelProperty = new SimpleObjectProperty<>();
    private final ObservableList<TimeSlot> activities = FXCollections.observableArrayList();

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

    public ObservableList<TimeSlot> getActivities() {
        return activities;
    }

    public void addActivity(I_Activity activity) {
        // Assuming TimeSlot has a method to add an activity
        itineraryDay.addActivity(activity);
        refreshActivities();
    }

    public void alterStart(I_Activity activity, LocalTime newStart) {
        // TO DO: implement
        System.out.println(newStart.toString());
    }

    // Additional methods to manipulate activities
    public void removeActivity(I_Activity activity) {
        itineraryDay.removeActivity(activity);
        refreshActivities();
    }

    public void setAllActivities(List<TimeSlot> slots) {
        itineraryDay.setAll(slots);
        refreshActivities();
    }

    private void refreshActivities() {
        if (itineraryDay.getActivities() != null)
            activities.setAll(itineraryDay.getActivities());
    }

    public StringBinding hotelNameBinding() {
        return Bindings.createStringBinding(() -> {
            Hotel hotel = hotelProperty.get();
            return hotel != null ? hotel.getName() : "";
        }, hotelProperty);
    }
}
