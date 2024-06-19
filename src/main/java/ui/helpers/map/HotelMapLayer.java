package ui.helpers.map;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import entities.Hotel;
import helpers.location.Location;
import javafx.geometry.Point2D;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.kordamp.ikonli.javafx.FontIcon;

public class HotelMapLayer extends MapLayer {
    private final Hotel hotel;
    private StackPane marker;

    public HotelMapLayer(Hotel hotel) {
        this.hotel = hotel;
        createMarker();
    }

    private void createMarker() {
        this.marker = new StackPane();
        Circle circle = new Circle(18, Color.valueOf("#BCA5ED"));
        FontIcon pin = new FontIcon("jam-building");
        pin.setIconSize(28);
        marker.getChildren().addAll(circle, pin);
        getChildren().add(marker);
    }

@Override
protected void layoutLayer() {
        if(marker == null)
            return;
        Location location = hotel.getLocation();
        MapPoint mapPoint = new MapPoint(location.latitude(), location.longitude());
        Point2D mapPosition = getMapPoint(mapPoint.getLatitude(), mapPoint.getLongitude());

        marker.setTranslateX(mapPosition.getX());
        marker.setTranslateY(mapPosition.getY());
    }
}
