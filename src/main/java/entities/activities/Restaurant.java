package entities.activities;

import helpers.BusinessHours;
import helpers.Location;
import helpers.PriceRange;

public class Restaurant extends Places{
    private Avaliacao estrelas;
    private PriceRange priceRange;
    

    public Restaurant(Location location, String name, BusinessHours openTime, String description, String image, Avaliacao estrelas, PriceRange priceRange) {
        super(location, name, openTime, description, image, Categories.RESTAURANTS, 0);
        this.estrelas = estrelas;
        this.priceRange = priceRange;
    }

    public Avaliacao getEstrelas() {
        return this.estrelas;
    }

    public void setEstrelas(Avaliacao estrelas) {
        this.estrelas = estrelas;
    }

    @Override
    public double getPrice(){
        double valorMedio = (priceRange.max()+priceRange.min())/2;
        return valorMedio;
    }

    public enum Avaliacao{
        UMA_ESTRELA,
        DUAS_ESTRELAS,
        TRES_ESTRELAS,
        QUATRO_ESTRELAS,
        CINCO_ESTRELAS
    }

}
