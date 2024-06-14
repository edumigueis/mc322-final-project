package entities.activities;

public enum Categories {
    ALL ("All"),
    MUSEUMS ("Museums"),
    RESTAURANTS("Restaurants"),
    PARKS("Parks"),
    TOURISTIC_SIGHTS("Touristic"),
    THEATERS("Theater");

    private final String stringValue;

    private Categories(String stringValue){
        this.stringValue = stringValue;
    }

    public String getStringValue(){
        return this.stringValue;
    }
}
