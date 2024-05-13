package ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class AttractionCardComponentController {
    @FXML
    private Region colorRegion;
    @FXML
    private Label titleLabel;
    @FXML
    private Label businessTimeLabel;
    @FXML
    private Label descriptionLabel;

    //TO DO: AQUI TEREMOS UM OBJETO DO TIPO TIMESLOT
    public void setData(String title, String businessTime, String description, String color) {
        titleLabel.setText(title);
        businessTimeLabel.setText("Business Time: " + businessTime);
        descriptionLabel.setText("Description: " + description);
        colorRegion.setStyle("-fx-background-color: " + color + ";");
    }
}

