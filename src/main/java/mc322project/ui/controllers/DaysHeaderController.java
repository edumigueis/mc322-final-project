package mc322project.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DaysHeaderController {
    private ItineraryController itineraryController;

    @FXML
    private Label cityNameLabel;
    @FXML
    private Label numberOfDaysLabel;

    public void setItineraryController(ItineraryController itineraryController) {
        this.itineraryController = itineraryController;
    }

    public void setData(String cityName, int numberOfDays) {
        cityNameLabel.setText(cityName);
        numberOfDaysLabel.setText(numberOfDays + " days");
    }

    @FXML
    private void nextWeek() {
        this.itineraryController.advanceWeek();
    }

    @FXML
    private void prevWeek() {
        this.itineraryController.prevWeek();
    }
}
