package mc322project.entities.tours;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import mc322project.entities.activities.I_Activity;
import mc322project.helpers.location.Location;

public abstract class Tour implements I_Activity {
    @JacksonXmlProperty(localName = "name")
    private String name;
    @JacksonXmlProperty(localName = "imageThumbURL")
    private String imageThumbURL;
    @JacksonXmlProperty(localName = "location")
    private Location location;
    @JacksonXmlProperty(localName = "description")
    private String description;
    @JacksonXmlProperty(localName = "language")
    private String language;
    @JacksonXmlElementWrapper(localName = "activities")
    @JacksonXmlProperty(localName = "activity")
    private List<I_Activity> attractionList;
    @JacksonXmlProperty(localName = "category")
    private TourType category;
    @JacksonXmlProperty(localName = "price")
    private double price;
    @JacksonXmlProperty(localName = "type")
    @JsonIgnore
    private String type;

    public Tour(String name, String imageThumbURL, Location location, String description, String language, List<I_Activity> attractionList, TourType category, double price, String type) {
        this.name = name;
        this.imageThumbURL = imageThumbURL;
        this.location = location;
        this.description = description;
        this.language = language;
        this.attractionList = attractionList;
        this.category = category;
        this.price = price;
        this.type = type;
    }

    public Tour() {
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getImageThumbURL() {
        return imageThumbURL;
    }

    @Override
    public void setImageThumbURL(String imageThumbURL) {
        this.imageThumbURL = imageThumbURL;
    }
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<I_Activity> getAttractionList() {
        return attractionList;
    }

    public TourType getCategory() {
        return this.category;
    }

    public void setCategory(TourType category) {
        this.category = category;
    }
}
