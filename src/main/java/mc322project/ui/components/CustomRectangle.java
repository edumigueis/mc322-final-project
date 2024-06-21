package mc322project.ui.components;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class CustomRectangle extends Path {
    private double width;
    private double height;
    private double topLeftRadius;
    private double topRightRadius;
    private double bottomRightRadius;
    private double bottomLeftRadius;

    public CustomRectangle(double width, double height,
                           double topLeftRadius, double topRightRadius,
                           double bottomRightRadius, double bottomLeftRadius) {
        this.width = width;
        this.bottomLeftRadius = bottomLeftRadius;
        this.topLeftRadius = topLeftRadius;
        this.height = height;
        this.topRightRadius = topRightRadius;
        this.bottomRightRadius = bottomRightRadius;
        update();
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
        update();
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
        update();
    }

    public void setClipRadii(double topLeftRadius, double topRightRadius,
                             double bottomRightRadius, double bottomLeftRadius) {
        this.bottomLeftRadius = bottomLeftRadius;
        this.topLeftRadius = topLeftRadius;
        this.topRightRadius = topRightRadius;
        this.bottomRightRadius = bottomRightRadius;
        update();
    }

    private void update() {
        // Clear the previous elements to avoid drawing over the old path
        getElements().clear();
        // Ensure the fill rule is set to NON_ZERO
        setFillRule(FillRule.NON_ZERO);
        // Define the path for the custom clip shape
        getElements().add(new MoveTo(0, topLeftRadius));
        // Top-left corner
        getElements().add(new ArcTo(topLeftRadius, topLeftRadius, 0, topLeftRadius, 0, false, true));
        // Top edge
        getElements().add(new LineTo(width - topRightRadius, 0));
        // Top-right corner
        getElements().add(new ArcTo(topRightRadius, topRightRadius, 0, width, topRightRadius, false, true));
        // Right edge
        getElements().add(new LineTo(width, height - bottomRightRadius));
        // Bottom-right corner
        getElements().add(new ArcTo(bottomRightRadius, bottomRightRadius, 0, width - bottomRightRadius, height, false, true));
        // Bottom edge
        getElements().add(new LineTo(bottomLeftRadius, height));
        // Bottom-left corner
        getElements().add(new ArcTo(bottomLeftRadius, bottomLeftRadius, 0, 0, height - bottomLeftRadius, false, true));
        // Left edge
        getElements().add(new LineTo(0, topLeftRadius));
        getElements().add(new ClosePath());
        setFill(Color.WHITE);
    }
}