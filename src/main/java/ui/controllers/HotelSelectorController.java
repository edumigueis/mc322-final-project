package ui.controllers;

import entities.Hotel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.List;

public class HotelSelectorController {
    private Hotel selectedHotel;

    @FXML
    private ListView<Hotel> hotelListView;

    private Stage stage;

    private final ObservableList<Hotel> hotelList = FXCollections.observableArrayList();

    public void start(List<Hotel> hotels) {
        this.hotelList.setAll(hotels);
        hotelListView.setItems(hotelList);

        // Set custom cell factory to add style class to each item
        hotelListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Hotel> call(ListView<Hotel> listView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Hotel item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            // Create HBox with Text and Icon
                            HBox hBox = new HBox();
                            Label text = new Label(item.getName());
                            text.getStyleClass().add("text-label");

                            // Add an example icon
                            FontIcon icon = new FontIcon("jam-building");

                            hBox.getChildren().addAll(icon, text);
                            setGraphic(hBox);
                        }
                    }
                };
            }
        });
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void handleSelect() {
        selectedHotel = hotelListView.getSelectionModel().getSelectedItem();
        if (selectedHotel != null) {
            // Close the modal
            stage.close();
        }
    }

    // Method to get the selected duration
    public Hotel getSelectedHotel() {
        return selectedHotel;
    }
}
