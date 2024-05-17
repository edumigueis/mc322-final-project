package ui.components;

import javafx.scene.control.ListCell;
import entities.activities.Activity;

public class AttractionCardCell extends ListCell<Activity> {
        @Override
        protected void updateItem(Activity activity, boolean empty) {
            super.updateItem(activity, empty);

            if (empty || activity == null) {
                setGraphic(null);
            } else {
                AttractionCard card = new AttractionCard();
                card.getController().setData();
                setGraphic(card);
            }
        }
}
