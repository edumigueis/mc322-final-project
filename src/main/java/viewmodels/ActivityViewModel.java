package viewmodels;

import entities.activities.I_Activity;
import entities.activities.Museum;
import entities.activities.Parks;
import entities.activities.Places;
import entities.activities.Restaurant;
import entities.activities.Theaters;
import entities.activities.TouristicSights;
import helpers.location.Location;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// TO DO: é importante como vai fazer com as variações de implementação pra cada filho da interface
public class ActivityViewModel {
    private final StringProperty nameProperty = new SimpleStringProperty();
    private final StringProperty descriptionProperty = new SimpleStringProperty();
    private final ObjectProperty<Location> locationProperty = new SimpleObjectProperty<>();
    private final StringProperty specificInfoProperty = new SimpleStringProperty();

    public ActivityViewModel(I_Activity activity) {
        this.nameProperty.set(activity.getName());
        this.descriptionProperty.set(activity.getDescription());
        this.locationProperty.set(activity.getLocation());

        if (activity instanceof Places) {
            Places attraction = (Places) activity;
            // Handle specific properties for Attraction if needed
        }
        if (activity instanceof TouristicSights) {
            TouristicSights sight = (TouristicSights) activity;
            specificInfoProperty.set(String.join(", ", sight.getImages()));
        }
        if (activity instanceof Restaurant) {
            Restaurant restaurant = (Restaurant) activity;
            specificInfoProperty.set(restaurant.getEstrelas().toString());
            // Handle specific properties for Restaurant if needed
        }
        if (activity instanceof Museum) {
            Museum museum = (Museum) activity;
            specificInfoProperty.set(museum.getCurrentExpoName() + ", " + museum.getMostFamousWorks());
        }
        if(activity instanceof Parks) {
            Parks park = (Parks) activity;
            // Handle specific properties for Attraction if needed
        }
        if(activity instanceof Theaters){
            Theaters theater = (Theaters) activity;
            // Handle specific properties for Attraction if needed
        }
    }

    public StringProperty nameProperty() {
        return nameProperty;
    }

    public StringProperty descriptionProperty() {
        return descriptionProperty;
    }

    public ObjectProperty<Location> locationProperty() {
        return locationProperty;
    }

    public StringProperty specificInfoProperty() {
        return specificInfoProperty;
    }
}

