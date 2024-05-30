package entities;

import entities.TransportationType;
import helpers.Location;

import java.time.Duration;
import java.time.LocalTime;

public class Transportation {
    private TransportationType type;
    private Duration estimatedDuration;
    private LocalTime start;
    private float price;

    public Transportation(TransportationType type, Duration estimatedDuration, LocalTime start, float price) {
        this.type = type;
        this.estimatedDuration = estimatedDuration;
        this.start = start;
        this.price = price;
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

    public static Transportation betweenPlaces(LocalTime startTime, Location start, Location end) {
        double distance = start.calculateDistance(end);
        TransportationType suggested;
        if (distance < 2000) {
            suggested = TransportationType.WALK;
        } else if (distance >= 2000 && distance < 15000) {
            suggested = TransportationType.CAR;
        } else
            suggested = TransportationType.BUS;
        //TO DO: ALTERAR TUDO PRA KM/H
        double duration = distance / suggested.getAverageSpeed();

        float price = estimatePrice(distance, suggested);

        // Create and return the Transportation instance
        return new Transportation(suggested, Duration.ofHours((long) duration), startTime, price);
    }

    private static float estimatePrice(double distance, TransportationType type) {
        return (float) (distance / 1000) * type.getAvgPriceRatePerKM();
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
