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

public class DaysHeaderController implements AttractionModalController.Callback {
    private int iterations = 0;
    private int dayToAdd = 0;
    private ItineraryController itineraryController;

    @FXML
    private GridPane grid;
    @FXML
    private Label cityName;
    @FXML
    private Label slotDay1;
    @FXML
    private Label slotDay2;
    @FXML
    private Label slotDay3;

    @FXML
    private void nextWeek() {
        itineraryController.setCurrentStartDate(itineraryController.getCurrentStartDate().plusDays(3)); // Advance the week by one week
        updateLabels();
    }

    public void setItineraryController(ItineraryController intineraryController) {
        this.itineraryController = intineraryController;
    }

    public void updateLabels() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE");
        for (int columnIndex = 0; columnIndex < 3; columnIndex++) {
            iterations++;

            Label labelDate = (Label) grid.lookup("#dayLabel" + (columnIndex + 1));
            Label labelIndex = (Label) grid.lookup("#indexLabel" + (columnIndex + 1));

            // Set the text to the label if found
            if (labelDate != null)
                labelDate.setText(formatter.format(itineraryController.getCurrentStartDate().plusDays(columnIndex)));
            if (labelIndex != null)
                labelIndex.setText(iterations + "");
        }
        slotDay3.setUserData(iterations - 1);
        slotDay2.setUserData(iterations - 2);
        slotDay1.setUserData(iterations - 3);
        this.cityName.setText(this.itineraryController.getItinerary().getCity().getName());
    }

    @FXML
    private void handleLabelClick(MouseEvent event) {
        try {
            Label clickedLabel = (Label) event.getSource(); // Get the label that was clicked
            this.dayToAdd = (int) clickedLabel.getUserData();

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/attraction_modal.fxml"));
            Parent root = loader.load();
            AttractionModalController modalController = loader.getController();
            modalController.setCallback(this);
            modalController.setData(this.itineraryController.getItinerary().getCity().getThingsToDo());
            stage.setScene(new Scene(root));
            stage.setTitle("Things to do in " + this.itineraryController.getItinerary().getCity().getName());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            System.out.println("Modal not available. Check path.");
        }
    }

    @Override
    public void returnResult(Activity result) {
        this.itineraryController.addActivity(result, dayToAdd);
    }
}
