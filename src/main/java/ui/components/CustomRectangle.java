package ui.components;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Path;

public class CustomRectangle extends Rectangle {

    public CustomRectangle() {
        super();
    }

    public CustomRectangle(double width, double height) {
        super(width, height);
    }


    protected javafx.scene.shape.Path createDefaultClip() {
        double width = getWidth();
        double height = getHeight();
        double arcTopLeft = getArcWidth(); // Radius for top left corner
        double arcTopRight = getArcWidth(); // Radius for top right corner
        double arcBottomRight = getArcHeight(); // Radius for bottom right corner
        double arcBottomLeft = getArcHeight(); // Radius for bottom left corner

        Path clipPath = new Path();
        clipPath.getElements().addAll(
                new javafx.scene.shape.MoveTo(arcTopLeft, 0),
                new javafx.scene.shape.LineTo(width - arcTopRight, 0),
                new javafx.scene.shape.ArcTo(arcTopRight, arcTopRight, 0, width, arcTopRight, false, false),
                new javafx.scene.shape.LineTo(width, height - arcBottomRight),
                new javafx.scene.shape.ArcTo(arcBottomRight, arcBottomRight, 0, width - arcBottomRight, height, false, false),
                new javafx.scene.shape.LineTo(arcBottomLeft, height),
                new javafx.scene.shape.ArcTo(arcBottomLeft, arcBottomLeft, 0, 0, height - arcBottomLeft, false, false),
                new javafx.scene.shape.LineTo(0, arcTopLeft),
                new javafx.scene.shape.ArcTo(arcTopLeft, arcTopLeft, 0, arcTopLeft, 0, false, false),
                new javafx.scene.shape.ClosePath()
        );

        return clipPath;
    }
}
