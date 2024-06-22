package mc322project.ui.controllers;

import mc322project.core.itinerary.TimeSlot;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import mc322project.viewmodels.ItineraryDayViewModel;

import java.util.List;

public class DraggableListController {

    @FXML
    private ListView<TimeSlot> listView;
    private ItineraryDayViewModel viewModel;

    @FXML
    public void initialize() {
        listView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<TimeSlot> call(ListView<TimeSlot> lv) {
                ListCell<TimeSlot> cell = new ListCell<>() {
                    @Override
                    protected void updateItem(TimeSlot item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            HBox hBox = new HBox();
                            Label nameText = new Label(item.getData().getName());
                            nameText.getStyleClass().add("text-label");
                            Label startTimeText = new Label(item.getStart().toString());
                            startTimeText.getStyleClass().add("text-label");
                            hBox.getChildren().addAll(nameText, new Label(" "), startTimeText);
                            setGraphic(hBox);
                        }
                    }
                };

                cell.setOnDragDetected(event -> {
                    if (!cell.isEmpty()) {
                        Dragboard db = cell.startDragAndDrop(TransferMode.MOVE);
                        ClipboardContent cc = new ClipboardContent();
                        cc.putString(cell.getItem().getData().getName());
                        db.setContent(cc);
                        event.consume();
                    }
                });

                cell.setOnDragOver(event -> {
                    if (event.getGestureSource() != cell && event.getDragboard().hasString()) {
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                    event.consume();
                });

                cell.setOnDragEntered(event -> {
                    if (event.getGestureSource() != cell && event.getDragboard().hasString()) {
                        cell.setStyle("-fx-background-color: #081e23;");
                    }
                });

                cell.setOnDragExited(event -> {
                    if (event.getGestureSource() != cell && event.getDragboard().hasString()) {
                        cell.setStyle("");
                    }
                });

                cell.setOnDragDropped(event -> {
                    if (!cell.isEmpty()) {
                        Dragboard db = event.getDragboard();
                        boolean success = false;
                        if (db.hasString()) {
                            int draggedIndex = findIndexByName(db.getString());
                            int thisIndex = cell.getIndex();

                            if (draggedIndex != thisIndex) {
                                TimeSlot draggedItem = listView.getItems().remove(draggedIndex);
                                listView.getItems().add(thisIndex, draggedItem);
                                viewModel.swapActivity(draggedItem, draggedIndex, thisIndex);
                                success = true;
                            }
                        }
                        event.setDropCompleted(success);
                        event.consume();
                    }
                });

                cell.setOnDragDone(Event::consume);

                return cell;
            }
        });
    }

    public void setViewModel(ItineraryDayViewModel viewModel) {
        this.viewModel = viewModel;
        listView.setItems(viewModel.getActivities());
    }

    private int findIndexByName(String name) {
        for (int i = 0; i < listView.getItems().size(); i++) {
            if (listView.getItems().get(i).getData().getName().equals(name)) {
                return i;
            }
        }
        return -1; // Should not happen if names are unique
    }

    public List<TimeSlot> getCurrentTimeSlotsOrder() {
        return listView.getItems();
    }
}
