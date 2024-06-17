package core.itinerary;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import entities.Hotel;
import entities.Transportation;
import entities.activities.I_Activity;

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
    private LocalDateTime startOfDay;

    @JacksonXmlProperty(localName = "end-of-day")
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
        validateDates(this.startOfDay, endOfDay);
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
        for (int i = 0; i < n; i++) {
            if (this.activities.get(i).getData() == activity) {
                pos_activity = i;
                break;
            }
        }
        if (pos_activity == n - 1) {
            this.activities.remove(n - 1);
            TimeSlot last = this.activities.get(n - 2);
            last.setWayToNext(null);
            return;
        }
        if (pos_activity == 0) {
            for (int i = n - 1; i >= 1; i--) {
                // OBS: foi feito para a duração padrão de uma hora de cada ts
                // alterar para possibilitar diferentes durações de ts
                TimeSlot current = this.activities.get(i);
                TimeSlot prev = this.activities.get(i - 1);
                current.setStart(prev.getStart());
                current.setEnd(prev.getEnd());
            }
            this.activities.remove(0);
            return;
        }
        for (int i = n - 1; i > pos_activity; i--) {
            TimeSlot current = this.activities.get(i);
            TimeSlot prev = this.activities.get(i - 1);
            current.setStart(prev.getStart());
            current.setEnd(prev.getEnd());
        }
        this.activities.remove(pos_activity);
        TimeSlot prev = this.activities.get(pos_activity - 1);
        TimeSlot next = this.activities.get(pos_activity);
        prev.setWayToNext(Transportation.betweenPlaces(prev.getEnd(), prev.getData().getLocation(), next.getData().getLocation()));
        return;
    }

    public void setAll(List<TimeSlot> slots) {
        // TO DO: implementar - lembre-se de recalcular todos os transportes porque a idea é setar apenas uma lista de atividades
    }

    private void validateDates(LocalDateTime startOfDay, LocalDateTime endOfDay) {
        /* TO DO UNCOMMENT
        if (startOfDay == null || endOfDay == null) {
            throw new IllegalArgumentException("Start of day and end of day must not be null.");
        }
        if (startOfDay.isAfter(endOfDay)) {
            throw new IllegalArgumentException("Start of day must be before or equal to end of day.");
        }*/
    }
}
