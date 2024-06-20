package mc322project.helpers;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

// Design pattern: factory method
public record PriceRange(@JacksonXmlProperty(localName = "min") double min,
                         @JacksonXmlProperty(localName = "max") double max) {

    public static PriceRange of(double min, double max) {
        if (min > max) {
            throw new IllegalArgumentException("Min value cannot be greater than max value");
        }
        return new PriceRange(min, max);
    }

    public boolean contains(double value) {
        return value >= min && value <= max;
    }

    public double mean() {
        return (this.min + this.max) / 2;
    }

    @Override
    public String toString() {
        return "[" + min + ", " + max + "]";
    }
}
