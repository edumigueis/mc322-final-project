package mc322project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GUIStarter extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(GUIStarter.class.getResource("screens/start.fxml"));
        Parent root = loader.load();
        System.setProperty("javafx.verbose", "true");
        Scene scene = new Scene(root, 800, 600);
        String stylesheet = Objects.requireNonNull(GUIStarter.class.getResource("styling/styles.css")).toExternalForm();
        String stylesheet2 = Objects.requireNonNull(GUIStarter.class.getResource("styling/date_selector.css")).toExternalForm();
        scene.getStylesheets().add(stylesheet);
        scene.getStylesheets().add(stylesheet2);
        stage.setTitle("Itinerary Maker");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println("Application started.");

        //Gluon is contained so there is no point in setting it up entirely for picoseconds of caching.
        System.setProperty("java.util.logging.config.file", "logging.properties");
        launch(args);
        System.out.println("Application finished.");
    }
}
