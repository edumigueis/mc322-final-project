package ui.controllers;

import entities.activities.Museum;
import helpers.BusinessHours;
import helpers.Location;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import entities.activities.Activity;
import entities.City;
import ui.components.CityCard;
import ui.components.CustomAlert;
import ui.helpers.CardParent;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class StartScreenController implements Initializable, CardParent {
    private List<City> cities;
    private int pickedCity = -1;

    private final Map<Node, CityCardController> controllersMap = new HashMap<>();
    private DateSelectorController dateController;

    @FXML
    private GridPane cityGrid;
    @FXML
    private Pane dateContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.cities = new ArrayList<>();
        // TO DO LOAD CITY FILE
        this.cities.add(new City("Paris", "The city of light", "https://i.pinimg.com/originals/d7/0c/c9/d70cc9765d8453704872287f8160536a.jpg", new ArrayList<Activity>() {{
            add(new Museum(new Location(1, 2), "Louvre Museum", new BusinessHours(), "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.", "https://imgmd.net/images/v1/guia/1703527/museu-do-louvre-piramide.jpg", "aaa", "aaaa", "aaaa"));
            add(new Museum(new Location(1, 2), "Louvre Museum", new BusinessHours(), "hey", "https://imgmd.net/images/v1/guia/1703527/museu-do-louvre-piramide.jpg", "aaa", "aaaa", "aaaa"));
            add(new Museum(new Location(1, 2), "Louvre Museum", new BusinessHours(), "hey", "https://imgmd.net/images/v1/guia/1703527/museu-do-louvre-piramide.jpg", "aaa", "aaaa", "aaaa"));
        }}));
        this.cities.add(new City("Shanghai", "The city of light", "https://images.travelandleisureasia.com/wp-content/uploads/sites/2/2023/01/04161010/shanghai-fi.jpeg?tr=w-1200,q-60", new ArrayList<Activity>()));
        this.cities.add(new City("Florence", "The city of light", "https://cdn.britannica.com/71/8671-050-2EE6A745/Cathedral-Florence-Santa-Maria-del-Fiore.jpg", new ArrayList<Activity>()));

        // TO DO: aqui faremos leitura de arquivos
        try {
            loadCities();
            loadDatePicker();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void createItinerary(MouseEvent event) {
        try {
            if (pickedCity == -1) {
                CustomAlert alert = CustomAlert.createErrorAlert("Please select a city");
                alert.setTitle("Error");
                alert.setHeaderText(null); // Remove header text
                alert.showAndWait();
                return;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/itinerary.fxml"));
            Parent root = loader.load();

            ItineraryController secondScreenController = loader.getController();
            secondScreenController.initData(cities.get(pickedCity), dateController.getStartDate(), dateController.getEndDate());

            Scene scene = new Scene(root, 900, 600);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCities() throws IOException {
        for (int i = 0; i < this.cities.size(); i++) {
            CityCard card = new CityCard(this);
            card.getController().setData(cities.get(i), i);
            int col = i % 3;
            int row = i / 3;
            cityGrid.add(card, col, row);
            controllersMap.put(card, card.getController());
        }
    }

    @Override
    public void itemSelected(int selectedIndex) {
        this.pickedCity = selectedIndex;
        List<Node> children = cityGrid.getChildren();
        for (Node child : children) {
            CityCardController controller = controllersMap.get(child);
            if (controller != null) {
                int index = (GridPane.getRowIndex(child) * cityGrid.getColumnCount())
                        + GridPane.getColumnIndex(child);
                if (index != selectedIndex) {
                    controller.unselect();
                }
            }
        }
    }

    private void loadDatePicker() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/date_selector.fxml"));
        Parent datePickerRoot = loader.load();
        this.dateController = loader.getController();
        dateContainer.getChildren().add(datePickerRoot);
    }
}
