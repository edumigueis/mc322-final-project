package ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import objects.Activity;
import objects.City;
import objects.Transportation;
import objects.TransportationType;
import ui.components.CityCard;
import ui.helpers.CardParent;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class StartScreenController implements Initializable, CardParent {
    private List<City> cities;
    private Date startDate;
    private Date endDate;
    private int pickedCity;

    private final Map<Node, CityCardController> controllersMap = new HashMap<>();

    @FXML
    private GridPane cityGrid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.cities = new ArrayList<City>();
        this.cities.add(new City("Paris", "The city of light", "https://i.pinimg.com/originals/d7/0c/c9/d70cc9765d8453704872287f8160536a.jpg", new ArrayList<Activity>()));
        this.cities.add(new City("Shanghai", "The city of light", "https://images.travelandleisureasia.com/wp-content/uploads/sites/2/2023/01/04161010/shanghai-fi.jpeg?tr=w-1200,q-60", new ArrayList<Activity>()));
        this.cities.add(new City("Florence", "The city of light", "https://cdn.britannica.com/71/8671-050-2EE6A745/Cathedral-Florence-Santa-Maria-del-Fiore.jpg", new ArrayList<Activity>()));
        // TO DO: aqui faremos leitura de arquivos
        try {
            loadCities();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void createItinerary(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/itinerary.fxml"));
            Parent root = loader.load();

            ItineraryController secondScreenController = loader.getController();

            secondScreenController.initData(cities.get(pickedCity), startDate, endDate);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
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
            cityGrid.add(card, i % 3, i / 3);
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
                int index = GridPane.getColumnIndex(child) + GridPane.getRowIndex(child) * cityGrid.getRowCount();
                if (index != selectedIndex) {
                    controller.unselect();
                }
            }
        }
    }
}
