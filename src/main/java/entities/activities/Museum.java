package entities.activities;

import helpers.BusinessHours;
import helpers.Location;

public class Museum extends Attraction {
    private String currentExpoName;
    private String mostFamousWorks;
    private String website;

    public Museum(Location location, String name, BusinessHours openTime, String description, String imageThumbURL, String currentExpoName, String mostFamousWorks, String website) {
        super(location, name, openTime, description, imageThumbURL);
        this.currentExpoName = currentExpoName;
        this.mostFamousWorks = mostFamousWorks;
        this.website = website;
    }

    public String getCurrentExpoName() {
        return currentExpoName;
    }

    public void setCurrentExpoName(String currentExpoName) {
        this.currentExpoName = currentExpoName;
    }

    public String getMostFamousWorks() {
        return mostFamousWorks;
    }

    public void setMostFamousWorks(String mostFamousWorks) {
        this.mostFamousWorks = mostFamousWorks;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
