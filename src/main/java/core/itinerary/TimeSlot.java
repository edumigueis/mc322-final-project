package core.itinerary;

import entities.activities.Activity;
import entities.Transportation;

import java.time.LocalTime;

public class TimeSlot {
    Activity data;
    Transportation wayToNext;
    TimeSlot next;
    LocalTime start;
    LocalTime end;

    public Activity getData() {
        return data;
    }

    public void setData(Activity data) {
        this.data = data;
    }

    public Transportation getWayToNext() {
        return wayToNext;
    }

    public void setWayToNext(Transportation wayToNext) {
        this.wayToNext = wayToNext;
    }

    public TimeSlot getNext() {
        return next;
    }

    public void setNext(TimeSlot next) {
        this.next = next;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "data=" + data +
                ", wayToNext=" + wayToNext +
                ", next=" + next +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
