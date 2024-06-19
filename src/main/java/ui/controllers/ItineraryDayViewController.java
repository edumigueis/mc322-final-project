package ui.controllers;

import core.itinerary.TimeSlot;
import entities.Hotel;
import entities.Transportation;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import ui.components.CustomAlert;
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
    private Button hotelText;
    @FXML
    private VBox cardsContainer;

    private ItineraryDayViewModel viewModel = null;
    private CityViewModel cityViewModel;

    public void initialize(LocalDate date) {
        // Initialize the ViewModel
        if(this.viewModel == null)
            this.viewModel = new ItineraryDayViewModel(date);

        DateTimeFormatter formatterWeekday = DateTimeFormatter.ofPattern("EEE");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM");
        indexLabel.setText(formatterDate.format(this.viewModel.startOfDayProperty().get().toLocalDate()));
        dayLabel.setText(formatterWeekday.format(this.viewModel.startOfDayProperty().get().toLocalDate()));
        hotelText.textProperty().bind(this.viewModel.hotelNameBinding());

        bindCards();
    }

    public void setCityViewModel(CityViewModel viewModel) {
        this.cityViewModel = viewModel;
    }
    public void setItineraryViewModel(ItineraryDayViewModel viewModel) {
        this.viewModel = viewModel;
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
            CustomAlert alert = CustomAlert.createErrorAlert("It was not possible to open the modal. Check paths.");
            alert.setTitle("Error");
            alert.setHeaderText(null); // Remove header text
            alert.showAndWait();
        }
    }

    @FXML
    private void pickHotel(MouseEvent event){
        try {
            // Load the FXML for the modal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/selectors/hotel_selector.fxml"));
            Parent root = loader.load();
            HotelSelectorController controller = loader.getController();
            controller.start(this.cityViewModel.getHotels());

            // Create a new stage for the modal
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Select Hotel");
            modalStage.setScene(new Scene(root));

            // Pass the stage reference to the modal controller
            controller.setStage(modalStage);

            // Show the modal
            modalStage.showAndWait();

            Hotel selectedHotel = controller.getSelectedHotel();
            if (selectedHotel != null) {
                this.viewModel.hotelProperty().set(selectedHotel);
            }
        } catch (NullPointerException | IOException e) {
            CustomAlert alert = CustomAlert.createErrorAlert("There are no registered hotels in this city.");
            alert.setTitle("Error");
            alert.setHeaderText(null); // Remove header text
            alert.showAndWait();
        }
    }
}
