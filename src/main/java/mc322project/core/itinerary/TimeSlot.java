package mc322project.core.itinerary;

import java.time.Duration;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import mc322project.entities.Transportation;
import mc322project.entities.activities.I_Activity;

public class TimeSlot {
    @JacksonXmlProperty(localName = "data")
    private I_Activity data;

    @JacksonXmlProperty(localName = "way-to-next")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Transportation wayToNext;

    @JacksonXmlProperty(localName = "start")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime start;

    @JacksonXmlProperty(localName = "end")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime end;

    @JsonIgnore
    private int appearances;

    @JsonIgnore
    private Duration duration;

    public TimeSlot(I_Activity data, Transportation wayToNext, LocalTime start, LocalTime end) {
        validateTimes(start, end);
        validateData(data);
        this.data = data;
        this.wayToNext = wayToNext;
        this.start = start;
        this.end = end;
        this.updateDuration();
    }

    public TimeSlot() {
    }

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
        this.updateDuration();
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        validateTimes(this.start, end);
        this.end = end;
        this.updateDuration();
    }

    public int getAppearances() {
        return appearances;
    }

    public void setAppearances(int appearances) {
        this.appearances = appearances;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setEndFromDuration(Duration duration) {
        this.end = this.start.plus(duration);
        this.duration = duration;
    }

    private void updateDuration() {
        if (start != null && end != null)
            this.duration = Duration.between(start, end);
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
