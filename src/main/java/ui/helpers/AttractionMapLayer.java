package ui.helpers;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import entities.activities.I_Activity;
import helpers.Location;
import javafx.geometry.Point2D;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.List;

public class AttractionMapLayer extends MapLayer {
    private final List<I_Activity> attractions;
    private boolean markersCreated = false;

    public AttractionMapLayer(List<I_Activity> attractions) {
        this.attractions = attractions;
    }

    private void createMarkers() {
        for (I_Activity attraction : attractions) {
            FontIcon marker = new FontIcon("jam-periscope");
            marker.setIconSize(25);

            // Add a tooltip to display additional information
            Tooltip tooltip = new Tooltip("Name: " + attraction.getName() + "\nPrice: $" + attraction.getPrice());
            Tooltip.install(marker, tooltip);

            getChildren().add(marker);
        }
    }

    @Override
    protected void layoutLayer() {
        if (!markersCreated) {
            createMarkers();
            markersCreated = true;
        }

        // Position markers correctly on the map
        for (int i = 0; i < attractions.size(); i++) {
            I_Activity attraction = attractions.get(i);
            Location location = attraction.getLocation();
            MapPoint mapPoint = new MapPoint(location.latitude(), location.longitude());
            Point2D mapPosition = getMapPoint(mapPoint.getLatitude(), mapPoint.getLongitude());

            FontIcon marker = (FontIcon) getChildren().get(i);
            marker.setTranslateX(mapPosition.getX());
            marker.setTranslateY(mapPosition.getY());
        }
    }
}
