package ui.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ExpandableText extends VBox {
    private final Label textLabel = new Label();
    private final Label readMoreLabel = new Label("Read more");
    private final StringProperty fullText = new SimpleStringProperty();
    private static final int MAX_LENGTH = 100;

    public ExpandableText() {
        super();
        getChildren().addAll(textLabel, readMoreLabel);
        textLabel.setWrapText(true);
        textLabel.getStyleClass().add("main-text");
        readMoreLabel.getStyleClass().add("read-more");
        readMoreLabel.setVisible(false);

        readMoreLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> showFullText());

        fullText.addListener((obs, oldText, newText) -> updateText());
    }

    public String getFullText() {
        return fullText.get();
    }

    public void setFullText(String text) {
        fullText.set(text);
    }

    public StringProperty fullTextProperty() {
        return fullText;
    }

    private void updateText() {
        String text = fullText.get();
        if (text.length() > MAX_LENGTH) {
            textLabel.setText(text.substring(0, MAX_LENGTH) + "...");
            readMoreLabel.setVisible(true);
        } else {
            textLabel.setText(text);
            readMoreLabel.setVisible(false);
        }
    }

    private void showFullText() {
        textLabel.setText(fullText.get());
        readMoreLabel.setVisible(false);
    }
}
