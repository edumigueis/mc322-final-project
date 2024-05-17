package helpers;

public record PriceRange(float min, float max) {

    public boolean contains(float value) {
        return value >= min && value <= max;
    }

    @Override
    public String toString() {
        return "[" + min + ", " + max + "]";
    }
}
