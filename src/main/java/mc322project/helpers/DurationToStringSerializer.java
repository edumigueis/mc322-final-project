package mc322project.helpers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.Duration;

public class DurationToStringSerializer extends StdSerializer<Duration> {

    public DurationToStringSerializer() {
        super(Duration.class);
    }

    @Override
    public void serialize(Duration duration, JsonGenerator gen, SerializerProvider provider) throws IOException {
        long seconds = duration.getSeconds();
        gen.writeString(String.valueOf(seconds));
    }
}

