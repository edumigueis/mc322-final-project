package ui.helpers.map;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import entities.activities.I_Activity;
import helpers.Location;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Tooltip;
import javafx.geometry.Point2D;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.ArrayList;
import java.util.List;

public class AttractionMapLayer extends MapLayer {
    private final List<I_Activity> attractions;
    private final List<FontIcon> markers = new ArrayList<>();
    private boolean markersCreated = false;
    private final AttractionClickListener listener;

    public AttractionMapLayer(List<I_Activity> attractions, AttractionClickListener listener) {
        this.attractions = attractions;
        this.listener = listener;
    }

    private void createMarkers() {
        for (I_Activity attraction : attractions) {
            FontIcon marker = new FontIcon("jam-periscope");
            marker.setIconSize(30);

            // Add a tooltip to display additional information
            Tooltip tooltip = new Tooltip("Name: " + attraction.getName() + "\nPrice: $" + attraction.getPrice());
            Tooltip.install(marker, tooltip);

            marker.setOnMouseClicked((MouseEvent event) -> {
                listener.onAttractionClicked(attraction);
            });

            markers.add(marker);
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

            FontIcon marker = markers.get(i);
            marker.setTranslateX(mapPosition.getX());
            marker.setTranslateY(mapPosition.getY());
        }
    }

    public void select(I_Activity activity){
        // Reset all markers to default size and color
        for (FontIcon marker : markers) {
            marker.setIconSize(30);
            marker.setFill(javafx.scene.paint.Color.valueOf("#070606")); // Change to default color if needed
            marker.setStroke(null);
        }

        // Find the index of the selected activity
        int selectedIndex = attractions.indexOf(activity);
        if (selectedIndex != -1) {
            // Adjust the marker for the selected activity
            FontIcon selectedMarker = markers.get(selectedIndex);
            selectedMarker.setIconSize(35); // Larger size
            selectedMarker.setFill(javafx.scene.paint.Color.valueOf("#F7FF88"));
            selectedMarker.setStroke(javafx.scene.paint.Color.valueOf("#070606"));
            selectedMarker.setStrokeWidth(1);
        }
    }
}
