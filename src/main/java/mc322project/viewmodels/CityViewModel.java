package mc322project.viewmodels;

import mc322project.entities.City;
import mc322project.entities.activities.I_Activity;
import mc322project.entities.Hotel;
import mc322project.helpers.location.Location;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CityViewModel {
    private final ObjectProperty<String> nameProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<String> descriptionProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<String> thumbImageUrlProperty = new SimpleObjectProperty<>();
    private final ObservableList<I_Activity> thingsToDo = FXCollections.observableArrayList();
    private final ObservableList<Hotel> hotels = FXCollections.observableArrayList();
    private final ObjectProperty<Location> location = new SimpleObjectProperty<>();
    public CityViewModel(City city) {
        this.nameProperty.set(city.getName());
        this.descriptionProperty.set(city.getDescription());
        this.thumbImageUrlProperty.set(city.getThumbImageUrl());
        this.thingsToDo.addAll(city.getThingsToDo());
        if(city.getHotels() != null)
            this.hotels.addAll(city.getHotels());
        this.location.set(city.getLocation());
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

    public ObservableList<I_Activity> getThingsToDo() {
        return thingsToDo;
    }

    public ObservableList<Hotel> getHotels() {
        return hotels;
    }
    public ObjectProperty<Location> getLocation() {
        return location;
    }
}

