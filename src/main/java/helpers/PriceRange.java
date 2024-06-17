package helpers;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public record PriceRange(@JacksonXmlProperty(localName = "min")double min,
                         @JacksonXmlProperty(localName = "max") double max) {

    public boolean contains(double value) {
        return value >= min && value <= max;
    }

    @Override
    public String toString() {
        return "[" + min + ", " + max + "]";
    }
}
