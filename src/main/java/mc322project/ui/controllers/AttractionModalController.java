package mc322project.ui.controllers;

import java.io.IOException;

import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import mc322project.GUIStarter;
import mc322project.entities.Hotel;
import mc322project.entities.activities.I_Activity;
import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mc322project.entities.activities.Places;
import mc322project.ui.components.AttractionCardCell;
import mc322project.ui.components.CustomAlert;
import mc322project.ui.components.Filter;
import mc322project.ui.helpers.map.AttractionClickListener;
import mc322project.ui.helpers.map.AttractionMapLayer;
import mc322project.ui.helpers.map.HotelMapLayer;
import mc322project.viewmodels.CityViewModel;
import mc322project.viewmodels.ItineraryDayViewModel;

public class AttractionModalController implements FilterBarController.FilterChangeListener {
    private ItineraryDayViewModel viewModel;
    private CityViewModel cityViewModel;
    private AttractionMapLayer pinLayer;

    @FXML
    private Button closeButton;
    @FXML
    private ListView<I_Activity> cardsContainer;
    @FXML
    private Pane filterBarContainer;
    @FXML
    private MapView mapView;


    @FXML
    public void initialize() throws IOException {
        FXMLLoader loader = new FXMLLoader(GUIStarter.class.getResource("components/filter_bar.fxml"));
        AnchorPane filterBar = loader.load();
        FilterBarController controller = loader.getController();
        controller.setFilterChangeListener(this);
        filterBarContainer.getChildren().add(filterBar);
        mapView.setPrefSize(400, 600);
    }

    public void setViewModel(ItineraryDayViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void loadCity(CityViewModel viewModel) {
        this.cityViewModel = viewModel;
        cardsContainer.setItems(cityViewModel.getThingsToDo());
        cardsContainer.setCellFactory(param -> new AttractionCardCell());
        loadMap();
    }

    private void loadMap() {
        mapView.setCenter(new MapPoint(this.cityViewModel.getLocation().get().latitude(), this.cityViewModel.getLocation().get().longitude()));
        mapView.setZoom(12.5);

        this.pinLayer = getAttractionMapLayer();
        mapView.addLayer(pinLayer);

        Hotel dayHotel = this.viewModel.hotelProperty().get();
        if (dayHotel != null)
            mapView.addLayer(new HotelMapLayer(dayHotel));

        cardsContainer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                pinLayer.select(newValue);
            }
        });
    }

    private AttractionMapLayer getAttractionMapLayer() {
        AttractionClickListener listener = attraction -> {
            int index = cardsContainer.getItems().indexOf(attraction);

            if (index != -1) {
                cardsContainer.scrollTo(index);
                Platform.runLater(() -> {
                    cardsContainer.requestFocus();
                    cardsContainer.getSelectionModel().select(index);
                });
            }
        };
        return new AttractionMapLayer(this.cardsContainer.getItems(), listener);
    }

    @Override
    public void onFilterChange(Filter filter) {
        cardsContainer.setItems(cityViewModel.getThingsToDo());
        FilteredList<I_Activity> filteredActivities;

        if (filter.category().equals("All")) {
            filteredActivities = new FilteredList<>(cardsContainer.getItems(), i -> (filter.priceRange().contains(i.getPrice())));
        } else {
            filteredActivities = new FilteredList<>(cardsContainer.getItems(), i -> (((Places) i).getCategory().getStringValue().equals(filter.category())) && (filter.priceRange().contains(i.getPrice())));
        }
        cardsContainer.setItems(filteredActivities);
        pinLayer.updateMarkers(filteredActivities);
    }

    @FXML
    private void handleReturnValue() {
        I_Activity value = cardsContainer.getSelectionModel().getSelectedItem();

        // Call the callback method with the return value
        if (value != null){
            try{
                viewModel.addActivity(value);
            }catch(UnsupportedOperationException e){
                CustomAlert alert = CustomAlert.createWarningAlert("This duration is too long for this day.");
                alert.setTitle("Warning");
                alert.setHeaderText(null); // Remove header text
                alert.showAndWait();
            }
        }

        // Close the modal window
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
