package mc322project.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import mc322project.helpers.DurationFromStringDeserializer;
import mc322project.helpers.DurationToStringSerializer;
import mc322project.helpers.location.Location;

import java.time.Duration;
import java.time.LocalTime;

public class Transportation {
    @JacksonXmlProperty(localName = "type")
    private TransportationType type;

    @JacksonXmlProperty(localName = "estimated-duration")
    @JsonSerialize(using = DurationToStringSerializer.class)
    @JsonDeserialize(using = DurationFromStringDeserializer.class)
    private Duration estimatedDuration;

    @JacksonXmlProperty(localName = "start")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime start;

    @JacksonXmlProperty(localName = "price")
    private float price;

    public Transportation(TransportationType type, Duration estimatedDuration, LocalTime start, float price) {
        this.type = type;
        this.estimatedDuration = estimatedDuration;
        this.start = start;
        this.price = price;
    }

    public Transportation(){}

    public TransportationType getType() {
        return type;
    }

    public Duration getEstimatedDuration() {
        return estimatedDuration;
    }

    public LocalTime getStart() {
        return start;
    }

    public float getPrice() {
        return price;
    }

    public static Transportation betweenPlaces(LocalTime startTime, Location start, Location end) {
        double distance = start.calculateDistance(end);
        TransportationType suggested;
        if (distance < 2) {
            suggested = TransportationType.WALK;
        } else if (distance >= 2 && distance < 20) {
            suggested = TransportationType.CAR;
        } else
            suggested = TransportationType.BUS;

        double duration = distance / suggested.getAverageSpeed();

        float price = estimatePrice(distance, suggested);

        // Create and return the Transportation instance
        return new Transportation(suggested, Duration.ofSeconds((long)(duration * 3600)), startTime, price);
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
