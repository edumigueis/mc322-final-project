package ui.components;

import javafx.scene.control.ListCell;
import entities.activities.I_Activity;

public class AttractionCardCell extends ListCell<I_Activity> {
        @Override
        protected void updateItem(I_Activity activity, boolean empty) {
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
