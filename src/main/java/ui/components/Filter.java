package ui.components;

import helpers.PriceRange;


public class Filter {
    private String category;
    private PriceRange priceRange;


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
