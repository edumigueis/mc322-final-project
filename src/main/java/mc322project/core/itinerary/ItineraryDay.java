package mc322project.core.itinerary;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import mc322project.entities.Hotel;
import mc322project.entities.Transportation;
import mc322project.entities.activities.I_Activity;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ItineraryDay {
    @JacksonXmlElementWrapper(localName = "activities")
    @JacksonXmlProperty(localName = "activity")
    private List<TimeSlot> activities = new ArrayList<>();

    @JacksonXmlProperty(localName = "start-of-day")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime startOfDay;

    @JacksonXmlProperty(localName = "end-of-day")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime endOfDay;

    @JacksonXmlProperty(localName = "hotel")
    private Hotel hotel;

    public ItineraryDay(LocalDateTime startOfDay, LocalDateTime endOfDay, Hotel hotel) {
        validateDates(startOfDay, endOfDay);
        this.startOfDay = startOfDay;
        this.endOfDay = endOfDay;
        this.hotel = hotel;
    }

    public ItineraryDay(LocalDate date) {
        this.startOfDay = LocalDateTime.of(date, LocalTime.of(0, 0));
        this.endOfDay = LocalDateTime.of(date, LocalTime.of(23, 59));
    }

    public ItineraryDay() {}

    public List<TimeSlot> getActivities() {
        return activities;
    }

    public LocalDateTime getStartOfDay() {
        return startOfDay;
    }

    public void setStartOfDay(LocalDateTime startOfDay) {
        validateDates(startOfDay, this.endOfDay);
        this.startOfDay = startOfDay;
    }

    public LocalDateTime getEndOfDay() {
        return endOfDay;
    }

    public void setEndOfDay(LocalDateTime endOfDay) {
        validateDates(this.startOfDay, endOfDay);
        this.endOfDay = endOfDay;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void addActivity(I_Activity activity) {
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

        TimeSlot ts = new TimeSlot(activity, null, acStart, acStart.plus(Duration.ofHours(1)));
        this.activities.add(ts);
    }

    public void removeActivity(I_Activity activity) {
        int pos_activity = -1;
        int n = this.activities.size();
        for (int i=0; i<n; i++) {
            if (this.activities.get(i).getData() == activity) {
                pos_activity = i;
                break;
            }
        }
        if (pos_activity == n-1) {
            this.activities.remove(n - 1);
            if (n-2 >= 0) {
                TimeSlot last = this.activities.get(n - 2);
                last.setWayToNext(null);
            }
            return;
        }
        if (pos_activity == 0) {
            LocalTime c_start = this.activities.getFirst().getStart();
            for (int i=1; i<=n-1; i++) {
                TimeSlot current = this.activities.get(i);
                Duration duration = Duration.between(current.getStart(), current.getEnd());
                current.setStart(c_start);
                current.setEnd(c_start.plus(duration));
                if (i != n-1) c_start = current.getEnd().plus(current.getWayToNext().getEstimatedDuration());
            }
            this.activities.removeFirst();
            return;
        }

        TimeSlot prev = this.activities.get(pos_activity-1);
        TimeSlot next = this.activities.get(pos_activity+1);
        Transportation transportation = Transportation.betweenPlaces(prev.getEnd(), prev.getData().getLocation(), next.getData().getLocation());
        LocalTime c_start = prev.getEnd().plus(transportation.getEstimatedDuration());
        prev.setWayToNext(transportation);
        for (int i=pos_activity+1; i<=n-1; i++) {
            TimeSlot current = this.activities.get(i);
            Duration duration = Duration.between(current.getStart(), current.getEnd());
            current.setStart(c_start);
            current.setEnd(c_start.plus(duration));
            if (i != n-1) c_start = current.getEnd().plus(current.getWayToNext().getEstimatedDuration());
        }
        this.activities.remove(pos_activity);
    }

    public void swapPosition (TimeSlot timeslot, int new_position) {
        int cur_position = -1;
        int n = this.activities.size();
        for (int i=0; i<n; i++) {
            if (this.activities.get(i) == timeslot) {
                cur_position = i;
                break;
            }
        }
        if (new_position == cur_position) return;
        if (cur_position-1>=0 && cur_position+1<=n-1) {
            TimeSlot prev = this.activities.get(cur_position-1);
            TimeSlot next = this.activities.get(cur_position+1);
            Transportation transportation = Transportation.betweenPlaces(prev.getEnd(), prev.getData().getLocation(), next.getData().getLocation());
            prev.setWayToNext(transportation);
        }
        if (new_position < cur_position) {
            // OBS: implementar casos especiais new_position = 0
            // OBS: implementar casos especiais cur_position = n-1
            TimeSlot prev = this.activities.get(new_position-1);
            TimeSlot next = this.activities.get(new_position);
            Transportation transportation1 = Transportation.betweenPlaces(prev.getEnd(), prev.getData().getLocation(), timeslot.getData().getLocation());
            prev.setWayToNext(transportation1);
            Transportation transportation2 = Transportation.betweenPlaces(timeslot.getEnd(), timeslot.getData().getLocation(), next.getData().getLocation());
            timeslot.setWayToNext(transportation2);
            TimeSlot aux1 = next, aux2;
            for (int i=new_position+1; i<=cur_position; i++) {
                aux2 = this.activities.get(i);
                this.activities.set(i, aux1);
                aux1 = aux2;
            }
            this.activities.set(new_position, timeslot);
            for (int i=1; i<n; i++) {
                TimeSlot current = this.activities.get(i);
                prev = this.activities.get(i-1);
                Duration duration = Duration.between(current.getStart(), current.getEnd());
                current.setStart(prev.getEnd().plus(prev.getWayToNext().getEstimatedDuration()));
                current.setEnd(current.getStart().plus(duration));
            }
        }
        else {
            // OBS: implementar casos especiais new_position = n-1
            // OBS: implementar casos especiais cur_position = 0
            TimeSlot prev = this.activities.get(new_position);
            TimeSlot next = this.activities.get(new_position+1);
            Transportation transportation1 = Transportation.betweenPlaces(prev.getEnd(), prev.getData().getLocation(), timeslot.getData().getLocation());
            prev.setWayToNext(transportation1);
            Transportation transportation2 = Transportation.betweenPlaces(timeslot.getEnd(), timeslot.getData().getLocation(), next.getData().getLocation());
            timeslot.setWayToNext(transportation2);
            TimeSlot aux1 = prev, aux2;
            for (int i=new_position-1; i>=cur_position; i--) {
                aux2 = this.activities.get(i);
                this.activities.set(i, aux1);
                aux1 = aux2;
            }
            this.activities.set(new_position, timeslot);
            for (int i=1; i<n; i++) {
                TimeSlot current = this.activities.get(i);
                prev = this.activities.get(i-1);
                Duration duration = Duration.between(current.getStart(), current.getEnd());
                current.setStart(prev.getEnd().plus(prev.getWayToNext().getEstimatedDuration()));
                current.setEnd(current.getStart().plus(duration));
            }
        }
    }

    public void setAll(List<TimeSlot> slots) {
        // TO DO: implementar - lembre-se de recalcular todos os transportes porque a idea é setar apenas uma lista de atividades
    }

    private void validateDates(LocalDateTime startOfDay, LocalDateTime endOfDay) {
        if (startOfDay == null && endOfDay == null)
            throw new IllegalArgumentException("Start of day and end of day must not be null.");

        if (startOfDay != null && endOfDay != null)
            if (startOfDay.isAfter(endOfDay))
                throw new IllegalArgumentException("Start of day must be before or equal to end of day.");
    }
}
