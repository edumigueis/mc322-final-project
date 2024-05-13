package objects;

import java.time.Duration;
import java.time.LocalTime;

public class Transportation {
    private TransportationType type;
    private Duration estimatedDuration;
    private LocalTime start;

    public Transportation(TransportationType type, Duration estimatedDuration, LocalTime start) {
        this.type = type;
        this.estimatedDuration = estimatedDuration;
        this.start = start;
    }
}
