package ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import objects.City;
import objects.Transportation;

public class CityCardController {
    @FXML
    private Label cityName;
    @FXML
    private ImageView cityImage;

    public void setData(City city) {
        this.cityName.setText(city.getName());
        this.cityImage.setImage(new Image(city.getThumbImageUrl()));
    }
}
