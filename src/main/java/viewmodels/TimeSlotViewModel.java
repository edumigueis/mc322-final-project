package viewmodels;

import core.itinerary.TimeSlot;
import entities.Transportation;
import entities.activities.Activity;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;

import java.time.Duration;
import java.time.LocalTime;

public class TimeSlotViewModel {
    private final ObjectProperty<Activity> dataProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<Transportation> wayToNextProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalTime> startProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalTime> endProperty = new SimpleObjectProperty<>();

    private final ReadOnlyObjectWrapper<Duration> durationProperty = new ReadOnlyObjectWrapper<>();

    public TimeSlotViewModel(TimeSlot timeSlot) {
        this.dataProperty.set(timeSlot.getData());
        this.wayToNextProperty.set(timeSlot.getWayToNext());
        this.startProperty.set(timeSlot.getStart());
        this.endProperty.set(timeSlot.getEnd());

        this.durationProperty.bind(Bindings.createObjectBinding(() ->
                Duration.between(startProperty.get(), endProperty.get()), startProperty, endProperty));

    }

    public ObjectProperty<Activity> dataProperty() {
        return dataProperty;
    }

    public ObjectProperty<Transportation> wayToNextProperty() {
        return wayToNextProperty;
    }

    public ObjectProperty<LocalTime> startProperty() {
        return startProperty;
    }

    public ObjectProperty<LocalTime> endProperty() {
        return endProperty;
    }

    public ReadOnlyObjectProperty<Duration> durationProperty() {
        return durationProperty.getReadOnlyProperty();
    }

    public StringBinding nameBinding() {
        return Bindings.createStringBinding(() -> {
            Activity activity = dataProperty.get();
            return activity != null ? activity.getName() : "";
        }, dataProperty);
    }

    public StringBinding descriptionBinding() {
        return Bindings.createStringBinding(() -> {
            Activity activity = dataProperty.get();
            return activity != null ? activity.getDescription() : "";
        }, dataProperty);
    }
}
