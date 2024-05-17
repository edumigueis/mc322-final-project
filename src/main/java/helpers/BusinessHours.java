package helpers;

import java.util.HashMap;
import java.util.Map;

public class BusinessHours {
    private final Map<String, String> hoursMap;

    public BusinessHours() {
        hoursMap = new HashMap<>();
        // Initialize with default closed hours for each day
        hoursMap.put("Monday", "Closed");
        hoursMap.put("Tuesday", "Closed");
        hoursMap.put("Wednesday", "Closed");
        hoursMap.put("Thursday", "Closed");
        hoursMap.put("Friday", "Closed");
        hoursMap.put("Saturday", "Closed");
        hoursMap.put("Sunday", "Closed");
    }

    public void setHours(String dayOfWeek, String hours) throws Exception {
        if (hoursMap.containsKey(dayOfWeek)) {
            hoursMap.put(dayOfWeek, hours);
        } else {
            throw new Exception("Invalid day of the week.");
        }
    }

    public boolean isOpenAtGivenTime(String dayOfWeek, String time) throws Exception {
        if (!hoursMap.containsKey(dayOfWeek))
            throw new Exception("Invalid day of the week.");

        String hours = hoursMap.get(dayOfWeek);
        if (hours.equals("Closed"))
            return false;

        String[] openingClosing = hours.split("-");
        String openingTime = openingClosing[0].trim();
        String closingTime = openingClosing[1].trim();

        // Assuming time format is "HH:mm"
        String[] givenTimeSplit = time.split(":");
        int givenHour = Integer.parseInt(givenTimeSplit[0]);
        int givenMinute = Integer.parseInt(givenTimeSplit[1]);

        String[] openingTimeSplit = openingTime.split(":");
        int openingHour = Integer.parseInt(openingTimeSplit[0]);
        int openingMinute = Integer.parseInt(openingTimeSplit[1]);

        String[] closingTimeSplit = closingTime.split(":");
        int closingHour = Integer.parseInt(closingTimeSplit[0]);
        int closingMinute = Integer.parseInt(closingTimeSplit[1]);

        // Check if given time is within opening and closing hours
        if (givenHour > openingHour
                || (givenHour == openingHour
                && givenMinute >= openingMinute))
            return givenHour < closingHour
                    || (givenHour == closingHour
                    && givenMinute <= closingMinute);
        return false;
    }
}
