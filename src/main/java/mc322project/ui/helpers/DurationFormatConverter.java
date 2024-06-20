package mc322project.ui.helpers;

import java.time.Duration;

public class DurationFormatConverter {
    public static String durationToString(Duration duration) {
        long totalHours = duration.toHours();
        long days = totalHours / 24;
        long hours = totalHours % 24;
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;

        if (days > 0) {
            return String.format("%dh", totalHours);
        } else if (hours > 0 && minutes > 0) {
            return String.format("%dh%02dmin", hours, minutes);
        } else if (hours > 0) {
            return String.format("%dh", hours);
        } else if (minutes > 0) {
            return String.format("%dmin", minutes);
        } else {
            return String.format("%ds", seconds);
        }
    }
}
