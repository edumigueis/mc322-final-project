package ui.controllers;

import entities.activities.Activity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class DaysHeaderController {
    private ItineraryController itineraryController;

    public void setItineraryController(ItineraryController intineraryController) {
        this.itineraryController = intineraryController;
    }

    @FXML
    private void nextWeek(){
        this.itineraryController.advanceWeek();
    }
}
