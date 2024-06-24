package mc322project.entities.tours;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import mc322project.entities.activities.I_Activity;
import mc322project.helpers.location.Location;

import java.util.List;

@JacksonXmlRootElement(localName = "adventure-tour")
public class AdventureTour extends Tour {
    @JacksonXmlProperty(localName = "includesFood")
    private boolean includesFood;

    @JacksonXmlProperty(localName = "difficulty")
    private TourDifficultyLevel difficulty;

    public AdventureTour(String name, String imageThumbURL, Location location, String description, String language, List<I_Activity> attractionList, TourType category, double price, boolean includesFood, TourDifficultyLevel difficulty) {
        super(name, imageThumbURL, location, description, language, attractionList, category, price);
        this.includesFood = includesFood;
        this.difficulty = difficulty;
    }

    public AdventureTour() {
    }

    public boolean includesFood() {
        return includesFood;
    }

    public void setIncludesFood(boolean includesFood) {
        this.includesFood = includesFood;
    }

    public TourDifficultyLevel getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(TourDifficultyLevel difficulty) {
        this.difficulty = difficulty;
    }
}
