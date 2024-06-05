package viewmodels;

import entities.activities.*;
import helpers.Location;
import javafx.beans.property.*;

// TO DO: é importante como vai fazer com as variações de implementação pra cada filho da interface
public class ActivityViewModel {
    private final StringProperty nameProperty = new SimpleStringProperty();
    private final StringProperty descriptionProperty = new SimpleStringProperty();
    private final ObjectProperty<Location> locationProperty = new SimpleObjectProperty<>();
    private final StringProperty specificInfoProperty = new SimpleStringProperty();

    public ActivityViewModel(Activity activity) {
        this.nameProperty.set(activity.getName());
        this.descriptionProperty.set(activity.getDescription());
        this.locationProperty.set(activity.getLocation());

        if (activity instanceof Attraction) {
            Attraction attraction = (Attraction) activity;
            // Handle specific properties for Attraction if needed
        }
        if (activity instanceof Sight) {
            Sight sight = (Sight) activity;
            specificInfoProperty.set(String.join(", ", sight.getImages()));
        }
        if (activity instanceof Restaurant) {
            Restaurant restaurant = (Restaurant) activity;
            // Handle specific properties for Restaurant if needed
        }
        if (activity instanceof Museum) {
            Museum museum = (Museum) activity;
            specificInfoProperty.set(museum.getCurrentExpoName() + ", " + museum.getMostFamousWorks());
        }
        if (activity instanceof CulturalEvent) {
            CulturalEvent event = (CulturalEvent) activity;
            specificInfoProperty.set(event.getType().toString() + ", " + event.getLinkToBuy());
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

