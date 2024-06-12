package entities;

import java.time.LocalTime;
import java.util.List;

import entities.activities.Places;

public class Tour {
    private String name;
    private String description;
    private String language;
    private List<Places> attractionList;
    private LocalTime start;
    private LocalTime end;
    private TourType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Places> getAttractionList() {
        return attractionList;
    }

    public void setAttractionList(List<Places> attractionList) {
        this.attractionList = attractionList;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public TourType getType() {
        return type;
    }

    public void setType(TourType type) {
        this.type = type;
    }
}
