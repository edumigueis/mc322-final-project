package ui.components;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ExpandableText extends VBox {
    private final Label textLabel = new Label();
    private final Label readMoreLabel = new Label("Read more");
    private final StringProperty fullText = new SimpleStringProperty();
    private final IntegerProperty maxLength = new SimpleIntegerProperty(100); // Default max length
    private final IntegerProperty maximumWidth = new SimpleIntegerProperty(); // Width property

    public ExpandableText() {
        super();
        getChildren().addAll(textLabel, readMoreLabel);
        textLabel.setWrapText(true);
        textLabel.getStyleClass().add("main-text");
        readMoreLabel.getStyleClass().add("read-more");
        readMoreLabel.setVisible(false);

        readMoreLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> showFullText());

        fullText.addListener((obs, oldText, newText) -> updateText());

        // Bind width property to ensure line breaks when the width is reached
        textLabel.maxWidthProperty().bind(maximumWidth);

        maxLength.addListener((obs, oldLength, newLength) -> updateText());
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

    public int getMaxLength() {
        return maxLength.get();
    }

    public void setMaxLength(int maxLength) {
        this.maxLength.set(maxLength);
    }

    public IntegerProperty maxLengthProperty() {
        return maxLength;
    }

    public int getMaximumWidth() {
        return maximumWidth.get();
    }

    public void setMaximumWidth(int width) {
        this.maximumWidth.set(width);
    }

    public IntegerProperty maximumWidthProperty() {
        return maximumWidth;
    }

    private void updateText() {
        String text = fullText.get();
        if(text != null)
            if (text.length() > maxLength.get()) {
                textLabel.setText(text.substring(0, maxLength.get()) + "...");
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
