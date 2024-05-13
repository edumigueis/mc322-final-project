package ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;


public class ItineraryController {
    @FXML
    private GridPane grid;

    public void start() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/attraction_card.fxml"));
        HBox card = loader.load();
        AttractionCardComponentController controller = loader.getController();
        controller.setData("Title 1", "9:00 AM - 5:00 PM", "Lorem ipsum dolor sit amet", "#FF5722");
        grid.add(card, 0, 3); // Add card to grid
        grid.add(new Button(), 0, 3);
    }
}