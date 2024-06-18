package ui.controllers;

import java.io.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.sun.tools.javac.Main;
import core.itinerary.Itinerary;
import entities.City;
import entities.Hotel;
import entities.activities.I_Activity;
import helpers.Location;
import helpers.input.ActivityParsingStrategy;
import helpers.input.HotelParsingStrategy;
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
        XmlMapper xmlMapper = new XmlMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        xmlMapper.registerModule(javaTimeModule);

        try {
            InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("data/cities.xml");
            this.cities = xmlMapper.readValue(inputStream, xmlMapper.getTypeFactory().constructCollectionType(List.class, City.class));
        } catch (IOException e) {
            e.printStackTrace();
            CustomAlert alert = CustomAlert.createErrorAlert("Cities not loaded. Check format and try again.");
            alert.setTitle("Error");
            alert.setHeaderText(null); // Remove header text
            alert.showAndWait();
            return;
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
        if (pickedCity == -1) {
            CustomAlert alert = CustomAlert.createErrorAlert("Please select a city");
            alert.setTitle("Error");
            alert.setHeaderText(null); // Remove header text
            alert.showAndWait();
        }
        goToSecondScreen(event, new Itinerary(this.cities.get(pickedCity), dateController.getStartDate(), dateController.getEndDate()), false);
    }

    private void goToSecondScreen(MouseEvent event, Itinerary itinerary, boolean fromSource) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/itinerary.fxml"));
            Parent root = loader.load();

            ItineraryController secondScreenController = loader.getController();
            secondScreenController.initData(itinerary, fromSource);

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
    private void openItinerary(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        XmlMapper xmlMapper = new XmlMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        xmlMapper.registerModule(javaTimeModule);

        Itinerary readItinerary = null;
        if (file != null) {
            try {
                readItinerary = xmlMapper.readValue(file, Itinerary.class);
            } catch (IOException e) {
                e.printStackTrace();
                CustomAlert alert = CustomAlert.createErrorAlert("File not read. Check format and try again.");
                alert.setTitle("Error");
                alert.setHeaderText(null); // Remove header text
                alert.showAndWait();
            }
        }
        goToSecondScreen(event, readItinerary, true);
    }

    private void loadCities() {
        this.cityGrid.setMinHeight((double)(((this.cities.size() + 2) / 3) * 250));
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
