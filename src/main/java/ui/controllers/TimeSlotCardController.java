package ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class TimeSlotCardController {
    @FXML
    private Region colorRegion;
    @FXML
    private Label titleLabel;
    @FXML
    private Label businessTimeLabel;
    @FXML
    private Label descriptionLabel;

    public void setData(String title, String businessTime, String description, String color) {
        titleLabel.setText(title);
        businessTimeLabel.setText("Business Time: " + businessTime);
        descriptionLabel.setText("Description: " + description);
        colorRegion.setStyle("-fx-background-color: " + color + ";");
    }
    //TO DO: AQUI TEREMOS INTERAÇÕES USER-UI
}

