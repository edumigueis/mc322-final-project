package mc322project.viewmodels;

import javafx.beans.property.*;
import mc322project.core.itinerary.TimeSlot;
import mc322project.entities.Transportation;
import mc322project.entities.activities.I_Activity;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import mc322project.ui.helpers.DurationFormatConverter;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeSlotViewModel {
    private final ObjectProperty<I_Activity> dataProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<Transportation> wayToNextProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalTime> startProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalTime> endProperty = new SimpleObjectProperty<>();
    private final ReadOnlyObjectWrapper<Duration> durationProperty = new ReadOnlyObjectWrapper<>();
    private final ReadOnlyIntegerWrapper appearancesProperty = new ReadOnlyIntegerWrapper();
    public TimeSlotViewModel(TimeSlot timeSlot) {
        this.dataProperty.set(timeSlot.getData());
        this.wayToNextProperty.set(timeSlot.getWayToNext());
        this.startProperty.set(timeSlot.getStart());
        this.endProperty.set(timeSlot.getEnd());

        this.durationProperty.bind(Bindings.createObjectBinding(timeSlot::getDuration));
        this.appearancesProperty.bind(Bindings.createObjectBinding(timeSlot::getAppearances));

    }

    public ObjectProperty<I_Activity> dataProperty() {
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

    public ReadOnlyIntegerProperty appearancesProperty() {
        return appearancesProperty.getReadOnlyProperty();
    }
    public StringBinding nameBinding() {
        return Bindings.createStringBinding(() -> {
            I_Activity activity = dataProperty.get();
            return activity != null ? activity.getName() : "";
        }, dataProperty);
    }

    public StringBinding descriptionBinding() {
        return Bindings.createStringBinding(() -> {
            I_Activity activity = dataProperty.get();
            return activity != null ? activity.getDescription() : "";
        }, dataProperty);
    }

    public StringBinding startTimeBinding() {
        return Bindings.createStringBinding(() -> {
            LocalTime time = startProperty().get();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            return time != null ? time.format(timeFormatter) : "Error";
        }, startProperty);
    }

    public StringBinding durationBinding() {
        return Bindings.createStringBinding(() -> {
            Duration dur = durationProperty().get();
            return dur != null ? DurationFormatConverter.durationToString(dur) : "Error";
        }, durationProperty);
    }
}
