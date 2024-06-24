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
        TimeSlot lastActivity = this.activities.getLast();
        LocalTime newStartTime = startOfDay.toLocalTime();
        Duration timeFromStartToLastActivityEnd = Duration.between(startOfDay.toLocalTime(), lastActivity.getEnd());

        // Check if the new start time plus the duration exceeds 24 hours
        if (timeFromStartToLastActivityEnd.toSeconds() + newStartTime.toSecondOfDay() > Duration.ofHours(24).toSeconds()) {
            throw new IllegalArgumentException("The new start time causes the last activity to exceed 24 hours and go into the next day.");
        }

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

            LocalDateTime currentDateTime = LocalDateTime.of(LocalDate.now(), last.getEnd());
            LocalDateTime newEndDateTime = currentDateTime.plus(Duration.ofMinutes(15));

            if (newEndDateTime.toLocalTime().isBefore(last.getEnd()))
                throw new UnsupportedOperationException("The duration surpasses the day mark.");

            startTrns = last.getEnd();
            transportation = Transportation.betweenPlaces(startTrns, last.getData().getLocation(), activity.getLocation());
            last.setWayToNext(transportation);
            acStart = startTrns.plus(transportation.getEstimatedDuration());
        } else {
            acStart = this.startOfDay.toLocalTime();
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

        LocalDateTime currentDateTime = LocalDateTime.of(LocalDate.now(), base.getEnd());
        LocalDateTime newEndDateTime = currentDateTime.plus(newDuration);

        if (newEndDateTime.toLocalTime().isBefore(base.getEnd()))
            throw new UnsupportedOperationException("The duration surpasses the day mark.");

        base.setEndFromDuration(newDuration);
        if (n > 1) {
            for (int i = posActivity + 1; i < n; i++) {
                TimeSlot current = activities.get(i);
                Duration dur = current.getDuration();
                current.setEnd(LocalTime.of(23, 59, 59));
                TimeSlot prev = activities.get(i - 1);
                current.setStart(prev.getEnd().plus(prev.getWayToNext().getEstimatedDuration()));
                current.setEndFromDuration(dur);
            }
        }
    }

    public void swapPosition(TimeSlot timeslot, int curPosition, int newPosition) {
        if (newPosition == curPosition) return;
        int n = this.activities.size();

        updatePreviousTransportation(curPosition, n);

        if (newPosition < curPosition) {
            handleSwapTowardsStart(timeslot, curPosition, newPosition);
        } else {
            handleSwapTowardsEnd(timeslot, curPosition, newPosition);
        }
    }

    private void updatePreviousTransportation(int curPosition, int n) {
        if (curPosition - 1 >= 0 && curPosition + 1 <= n - 1) {
            TimeSlot prev = this.activities.get(curPosition - 1);
            TimeSlot next = this.activities.get(curPosition + 1);
            Transportation transportation = Transportation.betweenPlaces(prev.getEnd(), prev.getData().getLocation(), next.getData().getLocation());
            prev.setWayToNext(transportation);
        }
    }

    private void handleSwapTowardsStart(TimeSlot timeslot, int curPosition, int newPosition) {
        if (newPosition == 0) {
            moveTimeSlotToStart(timeslot, curPosition, newPosition);
        } else {
            swapTimeslot(timeslot, curPosition, newPosition);
        }
    }

    private void moveTimeSlotToStart(TimeSlot timeslot, int curPosition, int newPosition) {
        TimeSlot next = this.activities.get(newPosition);
        Transportation transportation2 = Transportation.betweenPlaces(timeslot.getEnd(), timeslot.getData().getLocation(), next.getData().getLocation());
        timeslot.setWayToNext(transportation2);
        LocalTime newStart = next.getStart();
        shiftActivities(newPosition, curPosition);
        this.activities.set(newPosition, timeslot);
        updateTimeslotStartAndEnd(timeslot, newStart);
        updateAllTimeslots();
        handleLastTimeslot(curPosition);
    }

    private void swapTimeslot(TimeSlot timeslot, int curPosition, int newPosition) {
        TimeSlot prev = this.activities.get(newPosition - 1);
        TimeSlot next = this.activities.get(newPosition);
        Transportation transportation1 = Transportation.betweenPlaces(prev.getEnd(), prev.getData().getLocation(), timeslot.getData().getLocation());
        prev.setWayToNext(transportation1);
        Transportation transportation2 = Transportation.betweenPlaces(timeslot.getEnd(), timeslot.getData().getLocation(), next.getData().getLocation());
        timeslot.setWayToNext(transportation2);
        shiftActivities(newPosition, curPosition);
        this.activities.set(newPosition, timeslot);
        updateAllTimeslots();
        handleLastTimeslot(curPosition);
    }

    private void handleSwapTowardsEnd(TimeSlot timeslot, int curPosition, int newPosition) {
        int n = this.activities.size();
        if (newPosition == n - 1) {
            moveTimeSlotToEnd(timeslot, curPosition, newPosition);
        } else {
            swapTimeslotToEnd(timeslot, curPosition, newPosition);
        }
    }

    private void moveTimeSlotToEnd(TimeSlot timeslot, int curPosition, int newPosition) {
        TimeSlot prev = this.activities.getLast();
        Transportation transportation1 = Transportation.betweenPlaces(prev.getEnd(), prev.getData().getLocation(), timeslot.getData().getLocation());
        prev.setWayToNext(transportation1);
        timeslot.setWayToNext(null);
        shiftActivitiesBackwards(curPosition, newPosition);
        this.activities.set(newPosition, timeslot);
        handleFirstTimeslot(curPosition, timeslot);
        updateAllTimeslots();
    }

    private void swapTimeslotToEnd(TimeSlot timeslot, int curPosition, int newPosition) {
        TimeSlot prev = this.activities.get(newPosition);
        TimeSlot next = this.activities.get(newPosition + 1);
        Transportation transportation1 = Transportation.betweenPlaces(prev.getEnd(), prev.getData().getLocation(), timeslot.getData().getLocation());
        prev.setWayToNext(transportation1);
        Transportation transportation2 = Transportation.betweenPlaces(timeslot.getEnd(), timeslot.getData().getLocation(), next.getData().getLocation());
        timeslot.setWayToNext(transportation2);
        shiftActivitiesBackwards(curPosition, newPosition);
        this.activities.set(newPosition, timeslot);
        handleFirstTimeslot(curPosition, timeslot);
        updateAllTimeslots();
    }

    private void shiftActivities(int start, int end) {
        TimeSlot aux1 = this.activities.get(start), aux2;
        for (int i = start + 1; i <= end; i++) {
            aux2 = this.activities.get(i);
            this.activities.set(i, aux1);
            aux1 = aux2;
        }
    }

    private void shiftActivitiesBackwards(int start, int end) {
        TimeSlot aux1 = this.activities.get(end), aux2;
        for (int i = end - 1; i >= start; i--) {
            aux2 = this.activities.get(i);
            this.activities.set(i, aux1);
            aux1 = aux2;
        }
    }

    private void updateTimeslotStartAndEnd(TimeSlot timeslot, LocalTime newStart) {
        Duration duration = timeslot.getDuration();
        timeslot.setStart(newStart);
        timeslot.setEnd(newStart.plus(duration));
    }

    private void updateAllTimeslots() {
        int n = this.activities.size();
        for (int i = 1; i < n; i++) {
            TimeSlot current = this.activities.get(i);
            TimeSlot prev = this.activities.get(i - 1);
            Duration duration = current.getDuration();
            current.setEnd(LocalTime.of(23, 59, 59));
            current.setStart(prev.getEnd().plus(prev.getWayToNext().getEstimatedDuration()));
            current.setEnd(current.getStart().plus(duration));
        }
    }

    private void handleFirstTimeslot(int curPosition, TimeSlot timeslot) {
        if (curPosition == 0) {
            LocalTime new_start = timeslot.getStart();
            TimeSlot first = this.activities.getFirst();
            Duration duration = first.getDuration();
            first.setStart(new_start);
            first.setEnd(new_start.plus(duration));
        }
    }

    private void handleLastTimeslot(int curPosition) {
        int n = this.activities.size();
        if (curPosition == n - 1) {
            TimeSlot last = this.activities.getLast();
            last.setWayToNext(null);
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
