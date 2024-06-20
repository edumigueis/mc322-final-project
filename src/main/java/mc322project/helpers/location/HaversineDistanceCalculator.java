package mc322project.helpers.location;

// Haversine strategy implementation
public class HaversineDistanceCalculator implements DistanceCalculator {
    @Override
    public double calculateDistance(Location loc1, Location loc2) {
        double latDistance = Math.toRadians(loc2.latitude() - loc1.latitude());
        double lonDistance = Math.toRadians(loc2.longitude() - loc1.longitude());

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(loc1.latitude())) * Math.cos(Math.toRadians(loc2.latitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Approximate Earth radius in kilometers
        return 6371 * c;
    }
}
