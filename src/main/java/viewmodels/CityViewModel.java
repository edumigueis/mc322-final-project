package viewmodels;

import entities.City;
import entities.activities.Activity;
import entities.Hotel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CityViewModel {
    private final ObjectProperty<String> nameProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<String> descriptionProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<String> thumbImageUrlProperty = new SimpleObjectProperty<>();
    private final ObservableList<Activity> thingsToDo = FXCollections.observableArrayList();
    private final ObservableList<Hotel> hotels = FXCollections.observableArrayList();

    public CityViewModel(City city) {
        this.nameProperty.set(city.getName());
        this.descriptionProperty.set(city.getDescription());
        this.thumbImageUrlProperty.set(city.getThumbImageUrl());
        this.thingsToDo.addAll(city.getThingsToDo());
        if(city.getHotels() != null)
            this.hotels.addAll(city.getHotels());
    }

    public ObjectProperty<String> nameProperty() {
        return nameProperty;
    }

    public ObjectProperty<String> descriptionProperty() {
        return descriptionProperty;
    }

    public ObjectProperty<String> thumbImageUrlProperty() {
        return thumbImageUrlProperty;
    }

    public ObservableList<Activity> getThingsToDo() {
        return thingsToDo;
    }

    public ObservableList<Hotel> getHotels() {
        return hotels;
    }
}

