package mc322project.ui.controllers;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import mc322project.core.itinerary.Itinerary;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mc322project.ui.components.CustomAlert;
import mc322project.ui.components.ItineraryDayCarousel;
import mc322project.ui.components.ItineraryDayView;
import mc322project.viewmodels.CityViewModel;
import mc322project.viewmodels.ItineraryDayViewModel;

import java.io.File;
import java.time.LocalDate;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ItineraryController {
    @FXML
    private VBox mainBox;
    private final ItineraryDayCarousel carousel = new ItineraryDayCarousel();
    private Itinerary itinerary;

    public void initData(Itinerary itinerary, boolean preLoaded) throws IOException {
        this.itinerary = itinerary;
        this.loadHeader();
        this.startCards(preLoaded);
        mainBox.sceneProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                mainBox.maxHeightProperty().bind(newValue.heightProperty());
            }
        });
    }

    private void loadHeader() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/days_header.fxml"));
        HBox header = loader.load();
        DaysHeaderController controller = loader.getController();
        controller.setItineraryController(this);
        controller.setData(this.itinerary.getCity().getName(), this.itinerary.getDuration());
        mainBox.getChildren().add(header); // Add first card to grid
    }

    private void startCards(boolean preLoaded) {
        List<ItineraryDayView> views = new ArrayList<ItineraryDayView>();
        LocalDate startUp = this.itinerary.getStartDate();
        for (int i = 0; i < this.itinerary.getDuration(); i++) {
            ItineraryDayView view = new ItineraryDayView();
            ItineraryDayViewController controller = view.getController();
            if(preLoaded){
                controller.setItineraryViewModel(new ItineraryDayViewModel(this.itinerary.getItineraryDayList().get(i)));
            }
            controller.setCityViewModel(new CityViewModel(this.itinerary.getCity()));
            controller.initialize(startUp.plusDays(i));
            views.add(view);
        }
        carousel.setChildren(views);

        mainBox.getChildren().add(carousel);
    }

    public void advanceWeek() {
        this.carousel.getController().next();
    }

    public void prevWeek() {
        this.carousel.getController().previous();
    }

    @FXML
    private void saveItinerary(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Itinerary");

        // Set extension filter for XML files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        Stage stage = (Stage) ((javafx.scene.Node) mouseEvent.getSource()).getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            if (!file.getPath().toLowerCase().endsWith(".xml"))
                file = new File(file.getPath() + ".xml");

            XmlMapper xmlMapper = new XmlMapper();
            JavaTimeModule javaTimeModule = new JavaTimeModule();
            javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            xmlMapper.registerModule(javaTimeModule);
            try {
                xmlMapper.writeValue(file, itinerary);
            } catch (IOException e) {
                CustomAlert alert = CustomAlert.createErrorAlert("Not able to save. Try again later.");
                alert.setTitle("Error");
                alert.setHeaderText(null); // Remove header text
                alert.showAndWait();
            }
        }
    }
}