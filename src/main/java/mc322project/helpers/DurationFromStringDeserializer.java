package mc322project.helpers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Duration;

public class DurationFromStringDeserializer extends JsonDeserializer<Duration> {

    @Override
    public Duration deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        double seconds = Double.parseDouble(value); // Assuming value is like "10800.000000000"
        long secondsLong = (long) seconds;
        return Duration.ofSeconds(secondsLong);
    }
}
