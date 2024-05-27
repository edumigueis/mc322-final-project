package ui.controllers;

import javafx.beans.property.*;
import javafx.scene.Node;
import viewmodels.ItineraryDayViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ItineraryDayViewController {
    @FXML
    private Label indexLabel;
    @FXML
    private Label dayLabel;

    private ItineraryDayViewModel viewModel;

    public void initialize(LocalDate date) {
        // Initialize the ViewModel
        this.viewModel = new ItineraryDayViewModel(date);

        DateTimeFormatter formatterWeekday = DateTimeFormatter.ofPattern("EEE");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM");
        indexLabel.setText(formatterDate.format(this.viewModel.startOfDayProperty().get().toLocalDate()));
        dayLabel.setText(formatterWeekday.format(this.viewModel.startOfDayProperty().get().toLocalDate()));
        // Additional initialization if needed
    }

    public void setViewModel(ItineraryDayViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @FXML
    private void handleLabelClick(MouseEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/attraction_modal.fxml"));
            Parent root = loader.load();
            AttractionModalController modalController = loader.getController();
            modalController.setViewModel(this.viewModel);
            // modalController.setData(this.itineraryController.getItinerary().getCity().getThingsToDo());
            stage.setScene(new Scene(root));
            // stage.setTitle("Things to do in " + this.itineraryController.getItinerary().getCity().getName());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            System.out.println("Modal not available. Check path.");
        }
    }
}
