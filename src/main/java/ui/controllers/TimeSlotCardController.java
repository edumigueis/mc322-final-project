package ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class TimeSlotCardController {
    @FXML
    private Region imageContainer;
    @FXML
    private Label titleLabel;
    @FXML
    private Label businessTimeLabel;
    @FXML
    private Label descriptionLabel;

    public void setData(String title, String businessTime, String description) {
        titleLabel.setText(title);
        businessTimeLabel.setText("Business Time: " + businessTime);
        descriptionLabel.setText("Description: " + description);
    }
    //TO DO: AQUI TEREMOS INTERAÇÕES USER-UI
}

