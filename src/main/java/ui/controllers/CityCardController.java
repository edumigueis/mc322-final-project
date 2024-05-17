package ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import entities.City;
import ui.helpers.CardParent;

public class CityCardController {
    @FXML
    private Label cityName;
    @FXML
    private ImageView cityImage;
    @FXML
    private StackPane imageContainer;
    @FXML
    private VBox card;

    private CardParent parent;

    public void setParentController(CardParent parentController) {
        this.parent = parentController;
    }

    public void setData(City city, int index) {
        this.cityName.setText(city.getName());
        this.cityImage.setImage(new Image(city.getThumbImageUrl()));
        this.card.setUserData(index);
    }
    @FXML
    private void selectSelf(MouseEvent event) {
        VBox box = (VBox) event.getSource();
        BorderStroke borderStroke = new BorderStroke(Color.web("#BCA5ED"),
                BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM);

        // Create a border with the stroke
        Border border = new Border(borderStroke);

        // Apply the border to the node
        imageContainer.setBorder(border);
        parent.itemSelected((int)box.getUserData());
    }

    public void unselect(){
        imageContainer.setBorder(null);
    }
}
