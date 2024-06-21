package mc322project.ui.components;

import mc322project.helpers.PriceRange;


public class Filter {
    private final String category;
    private final PriceRange priceRange;


    public Filter(String category, PriceRange priceRange){
        this.category = category;
        this.priceRange = priceRange;
    }

    public String getCategory(){
        return this.category;
    }
    public PriceRange getPriceRange(){
        return this.priceRange;
    }

    
}
