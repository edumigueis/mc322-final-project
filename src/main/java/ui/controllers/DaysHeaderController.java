package ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import viewmodels.CityViewModel;

public class DaysHeaderController{
    private ItineraryController itineraryController;
    private CityViewModel cityViewModel;

    @FXML
    private Label cityName;

    public void setItineraryController(ItineraryController intineraryController) {
        this.itineraryController = intineraryController;
    }

    public void setCityViewModel(CityViewModel cityViewModel) {
        this.cityViewModel = cityViewModel;
        cityName.textProperty().bindBidirectional(cityViewModel.nameProperty());
    }

    @FXML
    private void nextWeek(){
        this.itineraryController.advanceWeek();
    }

    @FXML
    private void prevWeek(){
        this.itineraryController.prevWeek();
    }
}
