package ui.controllers;
import java.io.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import core.itinerary.Itinerary;
import entities.City;
import entities.Hotel;
import entities.activities.I_Activity;
import helpers.input.XMLReader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ui.components.CityCard;
import ui.components.CustomAlert;
import ui.helpers.CardParent;

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
        try {
            List<I_Activity> activitiesParis = XMLReader.readActivities("data/activityParis.xml");
            List<Hotel> hotelsParis = XMLReader.readHotels("data/hotelsParis.xml");
            List<I_Activity> activitiesFlorence = XMLReader.readActivities("data/activityFlorence.xml");
            List<Hotel> hotelsFlorence = XMLReader.readHotels("data/hotelsFlorence.xml");
            List<I_Activity> activitiesShanghai = XMLReader.readActivities("data/activityShangai.xml");
            List<Hotel> hotelsShanghai = XMLReader.readHotels("data/hotelsShangai.xml");

            this.cities.add(new City("Paris", "The city of light", "https://i.pinimg.com/originals/d7/0c/c9/d70cc9765d8453704872287f8160536a.jpg", activitiesParis, hotelsParis));
            this.cities.add(new City("Shanghai", "The center of the future", "https://images.travelandleisureasia.com/wp-content/uploads/sites/2/2023/01/04161010/shanghai-fi.jpeg?tr=w-1200,q-60", activitiesShanghai, hotelsShanghai));
            this.cities.add(new City("Florence", "The capital of Tuscany", "https://cdn.britannica.com/71/8671-050-2EE6A745/Cathedral-Florence-Santa-Maria-del-Fiore.jpg", activitiesFlorence, hotelsFlorence));

        } catch (Exception e) {
            System.err.println("Exception in StartScreenController 1!");
        }

        // Load UI
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

    @FXML
    private void openItinerary(MouseEvent event) throws JAXBException {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        JAXBContext jaxbContext = JAXBContext.newInstance(Itinerary.class);
        if (file != null) {
            try {
                Itinerary itinerary = new Itinerary(null, null, null);
                // TO DO - LER ARQUIVO SALVO
                // Process the file (e.g., read and parse the XML)
                // Example: System.out.println("File selected: " + file.getAbsolutePath());
                throw new IOException();
            } catch (IOException e) {
                CustomAlert alert = CustomAlert.createErrorAlert("File not read. Check format and try again.");
                alert.setTitle("Error");
                alert.setHeaderText(null); // Remove header text
                alert.showAndWait();
            }
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/selectors/date_selector.fxml"));
        Parent datePickerRoot = loader.load();
        this.dateController = loader.getController();
        dateContainer.getChildren().add(datePickerRoot);
    }
}
