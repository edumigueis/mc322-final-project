package mc322project.helpers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BusinessHours {

    @JacksonXmlElementWrapper(localName = "openTime", useWrapping = false)
    @JacksonXmlProperty(localName = "day")
    private List<Day> days;

    public BusinessHours() {
    }

    public static class Day {
        @JacksonXmlProperty(localName = "key")
        private String key;

        @JacksonXmlProperty(localName = "value")
        private String value;
        @JsonIgnore
        private LocalTime openingTime;
        @JsonIgnore
        private LocalTime closingTime;
        @JsonIgnore
        private boolean isClosed;
        private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("H:mm");

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
            if (value.equalsIgnoreCase("Closed")) {
                this.isClosed = true;
            } else {
                String[] times = value.split("-");
                this.openingTime = LocalTime.parse(times[0].trim(), TIME_FORMATTER);
                this.closingTime = LocalTime.parse(times[1].trim(), TIME_FORMATTER);
                this.isClosed = false;
            }
        }

        public LocalTime getOpeningTime() {
            return openingTime;
        }

        public LocalTime getClosingTime() {
            return closingTime;
        }

        @JsonIgnore
        public boolean isClosed() {
            return isClosed;
        }
    }

    @JsonIgnore
    public boolean isOpenAtGivenTime(String dayOfWeek, String time) {
        LocalTime givenTime = LocalTime.parse(time, Day.TIME_FORMATTER);
        for (Day day : days) {
            if (day.getKey().equalsIgnoreCase(dayOfWeek)) {
                if (day.isClosed()) {
                    return false;
                }
                return !givenTime.isBefore(day.getOpeningTime()) && !givenTime.isAfter(day.getClosingTime());
            }
        }
        return false;
    }

    @JsonIgnore
    public String getCurrentOpenHours() {
        LocalDate currentDate = LocalDate.now();
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        for (Day day : days) {
            if (day.getKey().equalsIgnoreCase(dayOfWeek.name())) {
                return day.getValue();
            }
        }
        return "Closed";
    }
}
