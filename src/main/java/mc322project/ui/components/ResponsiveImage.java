package mc322project.ui.components;

import mc322project.helpers.ImageCache;
import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class ResponsiveImage extends StackPane {
    private final StringProperty imageUrl = new SimpleStringProperty();
    private final DoubleProperty imgWidth = new SimpleDoubleProperty();
    private final DoubleProperty imgHeight = new SimpleDoubleProperty();
    private final ObjectProperty<Insets> clipCornerRadii = new SimpleObjectProperty<>(new Insets(0));

    //Singleton to cache images
    private final ImageCache imageCache = ImageCache.getInstance();

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

    public DoubleProperty imgWidthProperty() {
        return imgWidth;
    }

    public double getImgWidth() {
        return imgWidth.get();
    }

    public void setImgWidth(double value) {
        imgWidth.set(value);
    }

    public DoubleProperty imgHeightProperty() {
        return imgHeight;
    }

    public double getImgHeight() {
        return imgHeight.get();
    }

    public void setImgHeight(double value) {
        imgHeight.set(value);
    }

    private void updateBackground() {
        String url = imageUrl.get();
        if (url != null && !url.isEmpty()) {
            Region region = new Region();
            ProgressBar progressBar = new ProgressBar();
            progressBar.setProgress(-1);
            getChildren().addAll(region, progressBar);
            String formattedUrl = formatUrl(url);
            // Check if image is already cached
            if (imageCache.containsImage(url)) {
                Image cachedImage = imageCache.getImage(formattedUrl);
                setBackgroundImage(region, cachedImage);
                progressBar.setVisible(false); // Hide progress bar since image is already loaded
            } else {
                // Image not cached, load it and cache
                Image backgroundImage = new Image(formattedUrl, imgWidth.get(), imgHeight.get(), true, true, true);
                backgroundImage.progressProperty().addListener((obs, oldProgress, newProgress) -> {
                    progressBar.setProgress(newProgress.doubleValue());

                    if (newProgress.doubleValue() >= 1.0) {
                        setBackgroundImage(region, backgroundImage);
                        progressBar.setVisible(false); // Hide progress bar
                        imageCache.putImage(formattedUrl, backgroundImage); // Cache the loaded image
                    }
                });
            }
        }
    }

    private String formatUrl(String url){
        return url.trim().replace("\n", "");
    }

    private void setBackgroundImage(Region region, Image image) {
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, true, false, true)
        );
        region.setBackground(new Background(backgroundImage));
    }
}
