package ui.controllers;

import entities.Hotel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
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
                            hBox.setPadding(new Insets(2, 10, 2, 10));
                            hBox.setAlignment(Pos.CENTER);
                            Region spacer = new Region();
                            Label text = new Label(item.getName());
                            text.getStyleClass().add("text-label");
                            HBox.setHgrow(spacer, Priority.ALWAYS);

                            // Add an example icon
                            FontIcon icon = new FontIcon("jam-arrow-right");
                            icon.setFill(Paint.valueOf("#070606"));
                            icon.setIconSize(17);

                            hBox.getChildren().addAll(text, spacer, icon);
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
