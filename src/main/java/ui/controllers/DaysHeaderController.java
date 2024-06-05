package ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import viewmodels.CityViewModel;

import java.time.Duration;

public class DaysHeaderController {
    private ItineraryController itineraryController;

    @FXML
    private Label cityNameLabel;
    @FXML
    private Label numberOfDaysLabel;

    public void setItineraryController(ItineraryController intineraryController) {
        this.itineraryController = intineraryController;
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
