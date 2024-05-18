package ui.components;

import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import entities.activities.Activity;
import javafx.scene.layout.Pane;

public class AttractionCardCell extends ListCell<Activity> {
        @Override
        protected void updateItem(Activity activity, boolean empty) {
            super.updateItem(activity, empty);

            if (empty || activity == null) {
                setGraphic(null);
            } else {
                AttractionCard card = new AttractionCard();
                card.getController().setData(activity);
                setGraphic(card);
            }
        }
}
