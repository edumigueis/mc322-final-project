package entities.activities;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import helpers.BusinessHours;
import helpers.Location;

@JacksonXmlRootElement(localName = "museum")
public class Museum extends Places {
    @JacksonXmlProperty(localName = "currentExpoName")
    private String currentExpoName;

    @JacksonXmlProperty(localName = "mostFamousWorks")
    private String mostFamousWorks;

    @JacksonXmlProperty(localName = "website")
    private String website;

    public Museum(Location location, String name, BusinessHours openTime, String description, String imageThumbURL, String currentExpoName, String mostFamousWorks, String website, double price) {
        super(location, name, openTime, description, imageThumbURL, Categories.MUSEUMS, price);
        this.currentExpoName = currentExpoName;
        this.mostFamousWorks = mostFamousWorks;
        this.website = website;
    }
    public Museum(){}

    public String getCurrentExpoName() {
        return this.currentExpoName;
    }

    public void setCurrentExpoName(String currentExpoName) {
        this.currentExpoName = currentExpoName;
    }

    public String getMostFamousWorks() {
        return this.mostFamousWorks;
    }

    public void setMostFamousWorks(String mostFamousWorks) {
        this.mostFamousWorks = mostFamousWorks;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
