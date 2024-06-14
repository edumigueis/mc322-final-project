package ui.components;

import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class ResponsiveImage extends StackPane {
    private final StringProperty imageUrl = new SimpleStringProperty();
    private final ObjectProperty<Insets> clipCornerRadii = new SimpleObjectProperty<>(new Insets(0));

    public ResponsiveImage() {
        super();
        initClip();
        // Listen for changes in width and height properties to update the clip
        widthProperty().addListener((obs, oldVal, newVal) -> updateClip());
        heightProperty().addListener((obs, oldVal, newVal) -> updateClip());
        // Listen for changes in clip corner radii property to update the clip
        clipCornerRadii.addListener((obs, oldVal, newVal) -> updateClip());
    }

    private void initClip() {
        CustomRectangle c = new CustomRectangle(getWidth(),
                getHeight(), 0, 0, 0, 0);
        setClip(c);
        // Initial clip update
        updateClip();
    }

    private void updateClip() {
        CustomRectangle clip = (CustomRectangle) getClip();
        clip.setWidth(getWidth());
        clip.setHeight(getHeight());

        Insets cornerRadii = clipCornerRadii.get();
        clip.setClipRadii(cornerRadii.getLeft(), cornerRadii.getTop(), cornerRadii.getRight(), cornerRadii.getBottom());
    }

    public final String getImageUrl() {
        return imageUrl.get();
    }

    public final void setImageUrl(String value) {
        imageUrl.set(value);
        updateBackground();
    }

    public ObjectProperty<Insets> clipCornerRadiiProperty() {
        return clipCornerRadii;
    }

    public Insets getClipCornerRadii() {
        return clipCornerRadii.get();
    }

    public void setClipCornerRadii(Insets insets) {
        clipCornerRadii.set(insets);
    }

    private void updateBackground() {
        if (imageUrl != null && !imageUrl.isEmpty().get()) {
            Region region = new Region();
            Image backgroundImage = new Image(imageUrl.get());

            BackgroundSize backgroundSize = new BackgroundSize(
                    BackgroundSize.AUTO,
                    BackgroundSize.AUTO,
                    false,
                    true,
                    false,
                    true
            );

            BackgroundImage background = new BackgroundImage(
                    backgroundImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    backgroundSize
            );

            region.setBackground(new Background(background));
            getChildren().add(region);
        }
    }
}
