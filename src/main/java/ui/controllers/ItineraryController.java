package ui.controllers;

import core.itinerary.Itinerary;
import core.itinerary.ItineraryDay;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import entities.City;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ui.components.ItineraryDayCarousel;
import ui.components.ItineraryDayView;
import viewmodels.CityViewModel;
import viewmodels.ItineraryDayViewModel;

import java.time.LocalDate;

import java.io.IOException;
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
        //AQUI VEM O SAVE
    }
}