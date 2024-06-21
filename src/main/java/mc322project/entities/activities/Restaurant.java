package mc322project.entities.activities;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import mc322project.helpers.BusinessHours;
import mc322project.helpers.location.Location;
import mc322project.helpers.PriceRange;

@JacksonXmlRootElement(localName = "restaurant")
public class Restaurant extends Places {
    @JacksonXmlProperty(localName = "stars")
    private Stars stars;

    @JacksonXmlProperty(localName = "priceRange")
    private PriceRange priceRange;
    

    public Restaurant(Location location, String name, BusinessHours openTime, String description, String image, Stars estrelas, PriceRange priceRange) {
        super(location, name, openTime, description, image, Categories.RESTAURANTS, 0);
        this.stars = estrelas;
        this.priceRange = priceRange;
    }

    public Restaurant(){}

    public Stars getEstrelas() {
        return this.stars;
    }

    public void setEstrelas(Stars estrelas) {
        this.stars = estrelas;
    }

    @Override
    public double getPrice(){
        return priceRange.mean();
    }

    public enum Stars {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE
    }

}
