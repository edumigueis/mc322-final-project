package entities;

public enum TransportationType {
    BUS(40, 1),     // average speed in km/h
    CAR(50, 0.4f),
    WALK(5, 0);

    private final int averageSpeed;

    private final float avgPriceRatePerKM;

    TransportationType(int averageSpeed, float avgPriceRatePerKM) {
        this.averageSpeed = averageSpeed;
        this.avgPriceRatePerKM = avgPriceRatePerKM;
    }

    public int getAverageSpeed() {
        return averageSpeed;
    }

    public float getAvgPriceRatePerKM() {
        return avgPriceRatePerKM;
    }
}
