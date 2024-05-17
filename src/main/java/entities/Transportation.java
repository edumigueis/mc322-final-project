package entities;

import entities.TransportationType;

import java.time.Duration;
import java.time.LocalTime;

public class Transportation {
    private TransportationType type;
    private Duration estimatedDuration;
    private LocalTime start;
    private float price;

    public Transportation(TransportationType type, Duration estimatedDuration, LocalTime start) {
        this.type = type;
        this.estimatedDuration = estimatedDuration;
        this.start = start;
    }

    public TransportationType getType() {
        return type;
    }

    public void setType(TransportationType type) {
        this.type = type;
    }

    public Duration getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Duration estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Transportation{" +
                "type=" + type +
                ", estimatedDuration=" + estimatedDuration +
                ", start=" + start +
                ", price=" + price +
                '}';
    }
}
