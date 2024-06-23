package mc322project.entities.tours;

public enum TourType {
    WINE_AND_FOOD("Food and Wine"),
    CITY_TOURING("City Touring"),
    EXTREME_SPORTS("Extreme Sports"),
    HISTORICAL_SITES("Historical Sites"),
    NATURE_AND_WILDLIFE("Nature and Wildlife"),
    ART_AND_CULTURE("Art and Culture"),
    BEACH_AND_SEASIDE("Beach and Seaside"),
    MOUNTAIN_AND_HIKING("Mountain and Hiking"),
    SHOPPING_AND_MARKETS("Shopping and Markets"),
    LUXURY_AND_LEISURE("Luxury and Leisure"),
    FAMILY_AND_KIDS("Family and Kids"),
    NIGHTLIFE("Nightlife"),
    RELIGIOUS_SITES("Religious Sites"),
    CRUISE_AND_BOAT_TOURS("Cruise and Boat Tours");

    private final String displayName;

    TourType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

