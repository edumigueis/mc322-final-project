package ui.controllers;

import entities.activities.Activity;
import javafx.beans.property.*;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import ui.components.TimeSlotCard;
import viewmodels.CityViewModel;
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
    @FXML
    private VBox cardsContainer;

    private ItineraryDayViewModel viewModel;
    private CityViewModel cityViewModel;

    public void initialize(LocalDate date) {
        // Initialize the ViewModel
        this.viewModel = new ItineraryDayViewModel(date);

        DateTimeFormatter formatterWeekday = DateTimeFormatter.ofPattern("EEE");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM");
        indexLabel.setText(formatterDate.format(this.viewModel.startOfDayProperty().get().toLocalDate()));
        dayLabel.setText(formatterWeekday.format(this.viewModel.startOfDayProperty().get().toLocalDate()));
        // Additional initialization if needed
        bindCards();
    }

    public void setCityViewModel(CityViewModel viewModel) {
        this.cityViewModel = viewModel;
    }

    private void bindCards(){
        // Add TimeSlotCard for each activity
        for (Activity activity : viewModel.getActivities()) {
            TimeSlotCard timeSlotCard = new TimeSlotCard();
            TimeSlotCardController controller = timeSlotCard.getController();
            //controller.setActivity(activity); // Assuming TimeSlotCardController has a method to set the activity
            cardsContainer.getChildren().add(timeSlotCard);
        }

        // Bind activity list to UI
        viewModel.getActivities().addListener((ListChangeListener<Activity>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    for (Activity activity : c.getAddedSubList()) {
                        TimeSlotCard timeSlotCard = new TimeSlotCard();
                        TimeSlotCardController controller = timeSlotCard.getController();
                        //controller.setActivity(activity);
                        cardsContainer.getChildren().add(timeSlotCard);
                    }
                }
                if (c.wasRemoved()) {
                    cardsContainer.getChildren().removeAll(c.getRemoved());
                }
            }
        });
    }
    @FXML
    private void handleLabelClick(MouseEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/attraction_modal.fxml"));
            Parent root = loader.load();
            AttractionModalController modalController = loader.getController();
            modalController.setViewModel(this.viewModel);
            modalController.loadCity(this.cityViewModel);
            stage.setScene(new Scene(root));
            stage.setTitle("Things to do in " + this.cityViewModel.nameProperty().get());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            System.out.println("Modal not available. Check path.");
        }
    }
}
