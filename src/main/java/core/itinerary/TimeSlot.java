package core.itinerary;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import entities.Transportation;
import entities.activities.I_Activity;

public class TimeSlot {
    @JacksonXmlProperty(localName = "data")
    private I_Activity data;

    @JacksonXmlProperty(localName = "way-to-next")
    private Transportation wayToNext;

    @JacksonXmlProperty(localName = "start")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime start;

    @JacksonXmlProperty(localName = "end")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime end;

    public TimeSlot(I_Activity data, Transportation wayToNext, LocalTime start, LocalTime end) {
        validateTimes(start, end);
        validateData(data);
        this.data = data;
        this.wayToNext = wayToNext;
        this.start = start;
        this.end = end;
    }

    public TimeSlot(){}

    public I_Activity getData() {
        return data;
    }

    public void setData(I_Activity data) {
        validateData(data);
        this.data = data;
    }

    public Transportation getWayToNext() {
        return wayToNext;
    }

    public void setWayToNext(Transportation wayToNext) {
        this.wayToNext = wayToNext;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        validateTimes(start, this.end);
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        validateTimes(this.start, end);
        this.end = end;
    }

    private void validateTimes(LocalTime start, LocalTime end) {
        if (start == null && end == null)
            throw new IllegalArgumentException("Start time and end time must not be null.");

        if (start != null && end != null)
            if (start.isAfter(end))
                throw new IllegalArgumentException("Start time must be before end time.");
    }

    private void validateData(I_Activity data) {
        if (data == null) {
            throw new IllegalArgumentException("Activity data must not be null.");
        }
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "data=" + data +
                ", wayToNext=" + wayToNext +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
