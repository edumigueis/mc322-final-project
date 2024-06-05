package ui.controllers;

import core.itinerary.TimeSlot;
import entities.Transportation;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import ui.components.DisplacementCard;
import ui.components.TimeSlotCard;
import viewmodels.CityViewModel;
import viewmodels.ItineraryDayViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import viewmodels.TimeSlotViewModel;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ItineraryDayViewController {
    @FXML
    private Label indexLabel;
    @FXML
    private Label dayLabel;
    @FXML
    private VBox cardsContainer;
    @FXML
    private ScrollPane mainScrollPane;

    private ItineraryDayViewModel viewModel;
    private CityViewModel cityViewModel;

    public void initialize(LocalDate date) {
        // Initialize the ViewModel
        this.viewModel = new ItineraryDayViewModel(date);

        DateTimeFormatter formatterWeekday = DateTimeFormatter.ofPattern("EEE");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM");
        indexLabel.setText(formatterDate.format(this.viewModel.startOfDayProperty().get().toLocalDate()));
        dayLabel.setText(formatterWeekday.format(this.viewModel.startOfDayProperty().get().toLocalDate()));

        bindCards();
        // TO DO: CORRECT
        mainScrollPane.sceneProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue != null)
                mainScrollPane.setMaxHeight(Screen.getPrimary().getBounds().getHeight() - 290);
        }));
    }

    public void setCityViewModel(CityViewModel viewModel) {
        this.cityViewModel = viewModel;
    }

    private void bindCards() {
        populate();
        // Bind activity list to UI
        viewModel.getActivities().addListener((ListChangeListener<TimeSlot>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    cardsContainer.getChildren().clear();
                    populate();
                }
                if (c.wasRemoved())
                    cardsContainer.getChildren().removeAll(c.getRemoved());
            }
        });
    }

    private void populate() {
        for (TimeSlot ts : viewModel.getActivities()) {
            TimeSlotCard timeSlotCard = new TimeSlotCard();
            TimeSlotCardController controller = timeSlotCard.getController();
            DisplacementCard displacementCard;
            Transportation transportation = ts.getWayToNext();
            controller.initData(new TimeSlotViewModel(ts), this.viewModel);
            cardsContainer.getChildren().add(timeSlotCard);
            if (transportation != null) {
                displacementCard = new DisplacementCard(transportation);
                cardsContainer.getChildren().add(displacementCard);
            }
        }
    }

    @FXML
    private void addAttractionModal(MouseEvent event) {
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

    @FXML
    private void pickHotel(MouseEvent event){
        //TO DO
    }
}
