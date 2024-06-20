package mc322project.helpers.location;

// Vincenty strategy implementation
// Needed for long distances - future implementation
public class VincentyDistanceCalculator implements DistanceCalculator {
    @Override
    public double calculateDistance(Location loc1, Location loc2) {
        double lat1 = loc1.latitude();
        double lon1 = loc1.longitude();
        double lat2 = loc2.latitude();
        double lon2 = loc2.longitude();

        // Constants for WGS-84 ellipsoid (standard Earth model)
        double a = 6378137.0; // semi-major axis in meters
        double f = 1 / 298.257223563; // flattening

        // Calculate differences
        double deltaLon = Math.toRadians(lon2 - lon1);

        // U1, U2, sinU1, cosU1, sinU2, cosU2
        double tanU1 = (1 - f) * Math.tan(Math.toRadians(lat1));
        double cosU1 = 1 / Math.sqrt((1 + tanU1 * tanU1));
        double sinU1 = tanU1 * cosU1;
        double tanU2 = (1 - f) * Math.tan(Math.toRadians(lat2));
        double cosU2 = 1 / Math.sqrt((1 + tanU2 * tanU2));
        double sinU2 = tanU2 * cosU2;

        // Initial lambda, iterative variables
        double lambda = deltaLon;
        double lambdaP;
        double sinSigma, cosSigma, sigma, sinAlpha, cosSqAlpha, cos2SigmaM;
        double C;

        do {
            double sinLambda = Math.sin(lambda);
            double cosLambda = Math.cos(lambda);
            sinSigma = Math.sqrt((cosU2 * sinLambda) * (cosU2 * sinLambda) +
                    (cosU1 * sinU2 - sinU1 * cosU2 * cosLambda) *
                            (cosU1 * sinU2 - sinU1 * cosU2 * cosLambda));
            if (sinSigma == 0) {
                return 0; // Co-incident points
            }
            cosSigma = sinU1 * sinU2 + cosU1 * cosU2 * cosLambda;
            sigma = Math.atan2(sinSigma, cosSigma);
            sinAlpha = cosU1 * cosU2 * sinLambda / sinSigma;
            cosSqAlpha = 1 - sinAlpha * sinAlpha;
            cos2SigmaM = cosSigma - 2 * sinU1 * sinU2 / cosSqAlpha;
            C = f / 16 * cosSqAlpha * (4 + f * (4 - 3 * cosSqAlpha));
            lambdaP = lambda;
            lambda = deltaLon + (1 - C) * f * sinAlpha *
                    (sigma + C * sinSigma * (cos2SigmaM + C * cosSigma *
                            (-1 + 2 * cos2SigmaM * cos2SigmaM)));
        } while (Math.abs(lambda - lambdaP) > 1e-12); // Convergence criterion

        double uSq = cosSqAlpha * (a * a - 6356752.314245179 * 6356752.314245179) /
                (6356752.314245179 * 6356752.314245179);
        double A = 1 + uSq / 16384 * (4096 + uSq * (-768 + uSq * (320 - 175 * uSq)));
        double B = uSq / 1024 * (256 + uSq * (-128 + uSq * (74 - 47 * uSq)));
        double deltaSigma = B * sinSigma * (cos2SigmaM + B / 4 *
                (cosSigma * (-1 + 2 * cos2SigmaM * cos2SigmaM) - B / 6 * cos2SigmaM *
                        (-3 + 4 * sinSigma * sinSigma) * (-3 + 4 * cos2SigmaM * cos2SigmaM)));
        double s = 6356752.314245179 * A * (sigma - deltaSigma); // Distance in meters

        return s;
    }
}