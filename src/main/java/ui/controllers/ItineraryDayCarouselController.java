package ui.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import ui.components.ItineraryDayView;

import java.util.ArrayList;
import java.util.List;

public class ItineraryDayCarouselController {
    private int currentIndex = 0;
    private List<ItineraryDayView> allChildren = new ArrayList<>();
    private static final int VISIBLE_COUNT = 3;
    @FXML
    private GridPane visibleBox;

    public void start(List<ItineraryDayView> children) {
        this.allChildren = children;
        this.currentIndex = 0;
        updateVisibleChildren();
    }

    private void updateVisibleChildren() {
        visibleBox.getChildren().clear();
        int endIndex = Math.min(currentIndex + VISIBLE_COUNT, allChildren.size());
        for (int i = 0; i < endIndex - currentIndex; i++) {
            ItineraryDayView child = allChildren.get(currentIndex + i);
            GridPane.setColumnIndex(child, i);
            GridPane.setRowIndex(child, 0);
            visibleBox.getChildren().add(child);
        }
    }

    public void next() {
        if (currentIndex + VISIBLE_COUNT < allChildren.size()) {
            currentIndex += VISIBLE_COUNT;
            updateVisibleChildren();
        }
    }

    public void previous() {
        if (currentIndex - VISIBLE_COUNT >= 0) {
            currentIndex -= VISIBLE_COUNT;
            updateVisibleChildren();
        }
    }
}