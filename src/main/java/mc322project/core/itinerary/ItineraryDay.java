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
import java.util.*;
import java.util.stream.IntStream;

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

    public ItineraryDay() {
    }

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
        int appearances = (int) activities.stream()
                .filter(timeSlot -> timeSlot.getData().equals(activity))
                .count();

        if (!this.activities.isEmpty()) {
            last = this.activities.getLast();
            startTrns = last.getEnd();
            transportation = Transportation.betweenPlaces(startTrns, last.getData().getLocation(), activity.getLocation());
            last.setWayToNext(transportation);
            acStart = startTrns.plus(transportation.getEstimatedDuration());
        } else {
            acStart = LocalTime.of(8, 0);
        }

        TimeSlot ts = new TimeSlot(activity, null, acStart, acStart.plus(Duration.ofHours(1)));
        ts.setAppearances(appearances + 1);
        this.activities.add(ts);
    }

    public void removeActivity(I_Activity activity, int appearances) {
        int n = this.activities.size();
        int posActivity = IntStream.range(0, activities.size())
                .filter(i -> activities.get(i).getData().equals(activity) && activities.get(i).getAppearances() == appearances)
                .findFirst().orElse(-1);
        if (posActivity == -1)
            throw new NoSuchElementException("This activity is absent");

        if (posActivity == n - 1) {
            this.activities.remove(n - 1);
            if (n - 2 >= 0) {
                TimeSlot last = this.activities.get(n - 2);
                last.setWayToNext(null);
            }
            return;
        }
        if (posActivity == 0) {
            LocalTime cStart = this.activities.getFirst().getStart();
            for (int i = 1; i <= n - 1; i++) {
                TimeSlot current = this.activities.get(i);
                current.setStart(cStart);
                current.setEnd(cStart.plus(current.getDuration()));
                if (i != n - 1) cStart = current.getEnd().plus(current.getWayToNext().getEstimatedDuration());
            }
            this.activities.removeFirst();
            return;
        }

        TimeSlot prev = this.activities.get(posActivity - 1);
        TimeSlot next = this.activities.get(posActivity + 1);
        Transportation transportation = Transportation.betweenPlaces(prev.getEnd(), prev.getData().getLocation(), next.getData().getLocation());
        LocalTime cStart = prev.getEnd().plus(transportation.getEstimatedDuration());
        prev.setWayToNext(transportation);
        for (int i = posActivity + 1; i <= n - 1; i++) {
            TimeSlot current = this.activities.get(i);
            current.setStart(cStart);
            current.setEnd(cStart.plus(current.getDuration()));
            if (i != n - 1) cStart = current.getEnd().plus(current.getWayToNext().getEstimatedDuration());
        }
        this.activities.remove(posActivity);
    }

    public void alterActivityDuration(I_Activity activity, int appearances, Duration newDuration) {
        int n = this.activities.size();
        int posActivity = IntStream.range(0, activities.size())
                .filter(i -> activities.get(i).getData().equals(activity) && activities.get(i).getAppearances() == appearances)
                .findFirst().orElse(-1);
        if (posActivity == -1)
            throw new NoSuchElementException("This activity is absent");

        TimeSlot base = activities.get(posActivity);
        base.setEndFromDuration(newDuration);
        if (n > 1) {
            for (int i = posActivity + 1; i < n; i++) {
                TimeSlot current = activities.get(i);
                Duration dur = current.getDuration();
                current.setStart(activities.get(i - 1).getEnd());
                current.setEndFromDuration(dur);
            }
        }
    }

    public void swapPosition(TimeSlot timeslot, int curPosition, int newPosition) {
        int n = this.activities.size();
        if (newPosition == curPosition) return;
        if (curPosition - 1 >= 0 && curPosition + 1 <= n - 1) {
            TimeSlot prev = this.activities.get(curPosition - 1);
            TimeSlot next = this.activities.get(curPosition + 1);
            Transportation transportation = Transportation.betweenPlaces(prev.getEnd(), prev.getData().getLocation(), next.getData().getLocation());
            prev.setWayToNext(transportation);
        }
        if (newPosition < curPosition) {
            if (newPosition == 0) {
                TimeSlot next = this.activities.get(newPosition);
                Transportation transportation2 = Transportation.betweenPlaces(timeslot.getEnd(), timeslot.getData().getLocation(), next.getData().getLocation());
                timeslot.setWayToNext(transportation2);
                LocalTime newStart = next.getStart();
                TimeSlot aux1 = next, aux2;
                for (int i = newPosition + 1; i <= curPosition; i++) {
                    aux2 = this.activities.get(i);
                    this.activities.set(i, aux1);
                    aux1 = aux2;
                }
                this.activities.set(newPosition, timeslot);
                Duration duration = timeslot.getDuration();
                timeslot.setStart(newStart);
                timeslot.setEnd(newStart.plus(duration));
                for (int i = 1; i < n; i++) {
                    TimeSlot current = this.activities.get(i);
                    TimeSlot prev = this.activities.get(i - 1);
                    duration = current.getDuration();
                    current.setStart(prev.getEnd().plus(prev.getWayToNext().getEstimatedDuration()));
                    current.setEnd(current.getStart().plus(duration));
                }
                if (curPosition == n - 1) {
                    TimeSlot last = this.activities.getLast();
                    last.setWayToNext(null);
                }
                return;
            }
            TimeSlot prev = this.activities.get(newPosition - 1);
            TimeSlot next = this.activities.get(newPosition);
            Transportation transportation1 = Transportation.betweenPlaces(prev.getEnd(), prev.getData().getLocation(), timeslot.getData().getLocation());
            prev.setWayToNext(transportation1);
            Transportation transportation2 = Transportation.betweenPlaces(timeslot.getEnd(), timeslot.getData().getLocation(), next.getData().getLocation());
            timeslot.setWayToNext(transportation2);
            TimeSlot aux1 = next, aux2;
            for (int i = newPosition + 1; i <= curPosition; i++) {
                aux2 = this.activities.get(i);
                this.activities.set(i, aux1);
                aux1 = aux2;
            }
            this.activities.set(newPosition, timeslot);
            for (int i = 1; i < n; i++) {
                TimeSlot current = this.activities.get(i);
                prev = this.activities.get(i - 1);
                Duration duration = Duration.between(current.getStart(), current.getEnd());
                current.setStart(prev.getEnd().plus(prev.getWayToNext().getEstimatedDuration()));
                current.setEnd(current.getStart().plus(duration));
            }
            if (curPosition == n - 1) {
                TimeSlot last = this.activities.getLast();
                last.setWayToNext(null);
            }
        } else {
            if (newPosition == n - 1) {
                TimeSlot prev = this.activities.getLast();
                Transportation transportation1 = Transportation.betweenPlaces(prev.getEnd(), prev.getData().getLocation(), timeslot.getData().getLocation());
                prev.setWayToNext(transportation1);
                timeslot.setWayToNext(null);
                TimeSlot aux1 = prev, aux2;
                for (int i = newPosition - 1; i >= curPosition; i--) {
                    aux2 = this.activities.get(i);
                    this.activities.set(i, aux1);
                    aux1 = aux2;
                }
                this.activities.set(newPosition, timeslot);
                if (curPosition == 0) {
                    LocalTime new_start = timeslot.getStart();
                    TimeSlot first = this.activities.getFirst();
                    Duration duration = Duration.between(first.getStart(), first.getEnd());
                    first.setStart(new_start);
                    first.setEnd(new_start.plus(duration));
                }
                for (int i = 1; i < n; i++) {
                    TimeSlot current = this.activities.get(i);
                    prev = this.activities.get(i - 1);
                    Duration duration = Duration.between(current.getStart(), current.getEnd());
                    current.setStart(prev.getEnd().plus(prev.getWayToNext().getEstimatedDuration()));
                    current.setEnd(current.getStart().plus(duration));
                }
                return;
            }
            TimeSlot prev = this.activities.get(newPosition);
            TimeSlot next = this.activities.get(newPosition + 1);
            Transportation transportation1 = Transportation.betweenPlaces(prev.getEnd(), prev.getData().getLocation(), timeslot.getData().getLocation());
            prev.setWayToNext(transportation1);
            Transportation transportation2 = Transportation.betweenPlaces(timeslot.getEnd(), timeslot.getData().getLocation(), next.getData().getLocation());
            timeslot.setWayToNext(transportation2);
            TimeSlot aux1 = prev, aux2;
            for (int i = newPosition - 1; i >= curPosition; i--) {
                aux2 = this.activities.get(i);
                this.activities.set(i, aux1);
                aux1 = aux2;
            }
            this.activities.set(newPosition, timeslot);
            if (curPosition == 0) {
                LocalTime new_start = timeslot.getStart();
                TimeSlot first = this.activities.getFirst();
                Duration duration = Duration.between(first.getStart(), first.getEnd());
                first.setStart(new_start);
                first.setEnd(new_start.plus(duration));
            }
            for (int i = 1; i < n; i++) {
                TimeSlot current = this.activities.get(i);
                prev = this.activities.get(i - 1);
                Duration duration = Duration.between(current.getStart(), current.getEnd());
                current.setStart(prev.getEnd().plus(prev.getWayToNext().getEstimatedDuration()));
                current.setEnd(current.getStart().plus(duration));
            }
        }
    }

    private void validateDates(LocalDateTime startOfDay, LocalDateTime endOfDay) {
        if (startOfDay == null && endOfDay == null)
            throw new IllegalArgumentException("Start of day and end of day must not be null.");

        if (startOfDay != null && endOfDay != null)
            if (startOfDay.isAfter(endOfDay))
                throw new IllegalArgumentException("Start of day must be before or equal to end of day.");
    }
}
