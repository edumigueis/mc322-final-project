package ui.components;

import javafx.scene.control.ListCell;
import entities.activities.I_Activity;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;

public class AttractionCardCell extends ListCell<I_Activity> {
    @Override
    protected void updateItem(I_Activity activity, boolean empty) {
        super.updateItem(activity, empty);

        if (empty || activity == null) {
            setGraphic(null);
        } else {
            AttractionCard card = new AttractionCard();
            card.getController().setData(activity);

            // Create an HBox to center the AttractionCard
            HBox hbox = new HBox(card);
            hbox.setAlignment(Pos.CENTER);  // Center the card within the HBox

            setGraphic(hbox);
        }
    }
}
