package ui.components;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import ui.controllers.AttractionCardController;

public class AttractionCard extends Pane {
    
    private AttractionCardController controller;

    @FXML
    private Label priceLabel;

    


    public AttractionCard() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/components/cards/attraction_card.fxml"));
        try {
            fxmlLoader.load();
            this.controller = fxmlLoader.getController();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        getChildren().add(fxmlLoader.getRoot());
    }

    public String getPriceLabel(){
        return priceLabel.getText();
    }

    public void setController(AttractionCardController controller) {
        this.controller = controller;
    }

    public AttractionCardController getController() {
        return controller;
    }
}
