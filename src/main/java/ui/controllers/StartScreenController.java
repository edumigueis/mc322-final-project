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

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class StartScreenController implements Initializable {
    private List<City> cities;
    private Date startDate;
    private Date endDate;
    private int pickedCity;

    @FXML
    private GridPane cityGrid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.cities = new ArrayList<City>();
        this.cities.add(new City("Paris", "The city of light", "https://i.pinimg.com/originals/d7/0c/c9/d70cc9765d8453704872287f8160536a.jpg", new ArrayList<Activity>()));
        this.cities.add(new City("Shanghai", "The city of light", "https://images.travelandleisureasia.com/wp-content/uploads/sites/2/2023/01/04161010/shanghai-fi.jpeg?tr=w-1200,q-60", new ArrayList<Activity>()));
        this.cities.add(new City("Florence", "The city of light", "https://cdn.britannica.com/71/8671-050-2EE6A745/Cathedral-Florence-Santa-Maria-del-Fiore.jpg", new ArrayList<Activity>()));

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
        for(int i = 0; i < this.cities.size(); i++){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/city_card.fxml"));
            VBox card3 = loader.load();
            CityCardController controller = loader.getController();
            controller.setData(cities.get(i));
            cityGrid.add(card3, i % 3, i / 3);
        }
    }
}
