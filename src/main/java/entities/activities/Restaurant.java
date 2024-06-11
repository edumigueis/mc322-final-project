package entities.activities;

import helpers.BusinessHours;
import helpers.Location;

public class Restaurant extends Places{
    private Avaliacao estrelas;
    

    public Restaurant(Location location, String name, BusinessHours openTime, String description, String image, double price, Avaliacao estrelas) {
        super(location, name, openTime, description, image, Categories.RESTAURANTS, price);
        this.estrelas = estrelas;
    }

    public Avaliacao getEstrelas() {
        return this.estrelas;
    }

    public void setEstrelas(Avaliacao estrelas) {
        this.estrelas = estrelas;
    }

    public enum Avaliacao{
        UMA_ESTRELA,
        DUAS_ESTRELAS,
        TRES_ESTRELAS,
        QUATRO_ESTRELAS,
        CINCO_ESTRELAS
    }

}
