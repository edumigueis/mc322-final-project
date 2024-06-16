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
            LocalTime c_start = this.activities.get(0).getStart();
            for (int i=1; i<=n-1; i++) {
                TimeSlot current = this.activities.get(i);
                Duration duration = Duration.between(current.getStart(), current.getEnd());
                current.setStart(c_start);
                current.setEnd(c_start.plus(duration));
                if (i != n-1) c_start = current.getEnd().plus(current.getWayToNext().getEstimatedDuration());
            }
            this.activities.remove(0);
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
        return;
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
