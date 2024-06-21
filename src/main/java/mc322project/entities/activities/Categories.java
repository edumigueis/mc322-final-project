package mc322project.entities.activities;

public enum Categories {
    ALL("All"),
    MUSEUMS("Museums"),
    RESTAURANTS("Restaurants"),
    PARKS("Parks"),
    TOURISTIC_SIGHTS("Touristic"),
    THEATERS("Theater");

    private final String stringValue;

    Categories(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}
