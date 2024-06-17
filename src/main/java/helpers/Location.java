package helpers;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public record Location(@JacksonXmlProperty(localName = "latitude") double latitude,
                       @JacksonXmlProperty(localName = "longitude") double longitude) {
    public double calculateDistance(Location other) {
        double latDistance = Math.toRadians(other.latitude() - this.latitude());
        double lonDistance = Math.toRadians(other.longitude() - this.longitude());

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(this.latitude())) * Math.cos(Math.toRadians(other.latitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Approximate Earth radius in kilometers
        return 6371 * c;
    }
}
