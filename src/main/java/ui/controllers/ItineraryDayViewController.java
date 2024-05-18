package ui.controllers;

import javafx.beans.property.*;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ItineraryDayViewController {
    @FXML
    private Label indexLabel;
    @FXML
    private Label dayLabel;

    private ItineraryDayViewModel viewModel;

    private ObjectProperty<LocalDate> dateProperty = new SimpleObjectProperty<>();
    private StringProperty dayOfWeek = new SimpleStringProperty();
    private IntegerProperty index = new SimpleIntegerProperty();

    public void initialize(LocalDate date) {
        // Initialize the ViewModel
        this.viewModel = new ItineraryDayViewModel(date);

        // Bind ViewModel properties to the view components
        dateProperty.set(date);
        indexLabel.textProperty().bind(Bindings.convert(dateProperty));
        dayLabel.textProperty().bind(Bindings.convert(viewModel.endOfDayProperty()));

        // Additional initialization if needed
    }

    public void setViewModel(ItineraryDayViewModel viewModel) {
        this.viewModel = viewModel;
        // Bind ViewModel properties to the view components
        indexLabel.textProperty().bind(Bindings.convert(viewModel.startOfDayProperty()));
        dayLabel.textProperty().bind(Bindings.convert(viewModel.endOfDayProperty()));
    }

    @FXML
    private void handleLabelClick(MouseEvent event) {
        /*
        try {
            Label clickedLabel = (Label) event.getSource(); // Get the label that was clicked

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/attraction_modal.fxml"));
            Parent root = loader.load();
            AttractionModalController modalController = loader.getController();
            modalController.setCallback(this);
            // modalController.setData(this.itineraryController.getItinerary().getCity().getThingsToDo());
            stage.setScene(new Scene(root));
            // stage.setTitle("Things to do in " + this.itineraryController.getItinerary().getCity().getName());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            System.out.println("Modal not available. Check path.");
        }
        */
    }
    public void updateDay(int daysToAdd) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE");
        dayOfWeek.set(formatter.format(this.dateProperty.get().plusDays(daysToAdd)));
    }
}
