package helpers.location;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

// Design pattern: strategy
public class Location {
    @JacksonXmlProperty(localName = "latitude")
    private double latitude;

    @JacksonXmlProperty(localName = "longitude")
    private double longitude;
    @JsonIgnore
    private DistanceCalculator distanceCalculator;

    // Default constructor for Jackson
    public Location() {
        this.distanceCalculator = new HaversineDistanceCalculator();
    }

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.distanceCalculator = new HaversineDistanceCalculator(); // Default strategy
    }

    public double latitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double longitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double calculateDistance(Location other) {
        return distanceCalculator.calculateDistance(this, other);
    }

    public void setDistanceCalculator(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }
}