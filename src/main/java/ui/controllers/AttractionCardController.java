package ui.controllers;

import entities.Tour;
import entities.activities.Activity;
import entities.activities.Attraction;
import entities.activities.Museum;
import entities.activities.Sight;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.components.ExpandableText;

public class AttractionCardController {
    @FXML
    private ExpandableText descriptionLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private Label businessTimeLabel;

    public void setData(Attraction attraction) {
        if (attraction instanceof Sight) {
            Sight tour = (Sight) attraction;

        } else {
            if (attraction instanceof Museum) {
                Museum museum = (Museum) attraction;
            }
            // ... TO DO
        }
        descriptionLabel.setFullText(attraction.getDescription());
        titleLabel.setText(attraction.getName().toUpperCase());
        businessTimeLabel.setText("Open hours: " + attraction.getOpenTime().getCurrentOpenHours());
    }
}
