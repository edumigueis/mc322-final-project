package mc322project.ui.controllers;

import java.io.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import mc322project.GUIStarter;
import mc322project.core.itinerary.Itinerary;
import mc322project.entities.City;
import javafx.application.Platform;
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
import mc322project.ui.components.CityCard;
import mc322project.ui.components.CustomAlert;
import mc322project.ui.helpers.CardParent;

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
            InputStream inputStream = GUIStarter.class.getResourceAsStream("data/cities.xml");
            this.cities = xmlMapper.readValue(inputStream, xmlMapper.getTypeFactory().constructCollectionType(List.class, City.class));

            loadCities();
            loadDatePicker();
        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
            CustomAlert alert = CustomAlert.createErrorAlert("Cities not loaded. Check format and try again.");
            alert.setTitle("Error");
            alert.setHeaderText(null); // Remove header text
            alert.showAndWait();

            Platform.exit();
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
            FXMLLoader loader = new FXMLLoader(GUIStarter.class.getResource("screens/itinerary.fxml"));
            Parent root = loader.load();

            ItineraryController secondScreenController = loader.getController();
            secondScreenController.initData(itinerary, fromSource);

            Scene scene = new Scene(root, 900, 600);
            Stage stage = new Stage();
            String stylesheet = GUIStarter.class.getResource("styling/styles.css").toExternalForm();
            scene.getStylesheets().add(stylesheet);

            stage.setScene(scene);
            stage.show();

            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException | RuntimeException e) {
            CustomAlert alert = CustomAlert.createErrorAlert("Oops. Something went wrong it wasn't possible to go to the next screen.");
            alert.setTitle("Error");
            alert.setHeaderText(null); // Remove header text
            alert.showAndWait();
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
        FXMLLoader loader = new FXMLLoader(GUIStarter.class.getResource("components/selectors/date_selector.fxml"));
        Parent datePickerRoot = loader.load();
        this.dateController = loader.getController();
        dateContainer.getChildren().add(datePickerRoot);
    }
}
