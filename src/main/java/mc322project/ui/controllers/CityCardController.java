package mc322project.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import mc322project.entities.City;
import mc322project.ui.components.ResponsiveImage;
import mc322project.ui.helpers.CardParent;

public class CityCardController {
    @FXML
    private Label cityName;
    @FXML
    private ResponsiveImage cityImage;
    @FXML
    private StackPane imageContainer;
    @FXML
    private VBox card;

    private CardParent parent;

    public void setParentController(CardParent parentController) {
        this.parent = parentController;
    }

    public void setData(City city, int index) {
        this.cityName.setText(city.getName().toUpperCase());
        this.cityImage.setImageUrl(city.getThumbImageUrl());
        this.card.setUserData(index);
    }
    @FXML
    private void selectSelf(MouseEvent event) {
        VBox box = (VBox) event.getSource();
        imageContainer.setStyle("-fx-background-radius: 12;" +
                "-fx-border-radius: 12; " +
                "-fx-background-color: transparent; " + // Set background color to transparent
                "-fx-border-color: #BCA5ED; " +        // Border color
                "-fx-border-width: 2px;");

        parent.itemSelected((int)box.getUserData());
    }

    public void unselect(){
        imageContainer.setStyle("");
    }
}
