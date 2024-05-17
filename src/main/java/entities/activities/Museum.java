package entities.activities;

public class Museum extends Attraction {
    private String currentExpoName;
    private String mostFamousWorks;
    private String website;

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
