package ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import objects.City;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class StartScreenController implements Initializable {
    private List<City> cities;
    private Date startDate;
    private Date endDate;
    private int pickedCity;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.cities = new ArrayList<City>();
        this.cities.add(new City());
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
}
