package helpers;

public record PriceRange(double  min, double max) {

    public boolean contains(double value) {
        return value >= min && value <= max;
    }

    @Override
    public String toString() {
        return "[" + min + ", " + max + "]";
    }
}
