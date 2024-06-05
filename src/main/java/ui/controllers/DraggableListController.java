package ui.controllers;

import core.itinerary.TimeSlot;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import viewmodels.ItineraryDayViewModel;

public class DraggableListController {

    @FXML
    private ListView<TimeSlot> listView;

    private ItineraryDayViewModel viewModel;

    public void setListViewModel(ItineraryDayViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void initData() {
        listView.setItems(viewModel.getActivities());

        listView.setCellFactory(lv -> {
            ListCell<TimeSlot> cell = new ListCell<>() {
                @Override
                protected void updateItem(TimeSlot item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null : item.getData().getName());
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
                    cell.setStyle("-fx-background-color: #a8a8a8;");
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
                        int draggedIndex = listView.getItems().indexOf(db.getString());
                        int thisIndex = cell.getIndex();

                        if (draggedIndex != thisIndex) {
                            TimeSlot draggedItem = listView.getItems().remove(draggedIndex);
                            listView.getItems().add(thisIndex, draggedItem);
                            success = true;
                        }
                    }
                    event.setDropCompleted(success);
                    event.consume();
                }
            });

            cell.setOnDragDone(DragEvent::consume);

            return cell;
        });
    }
}
