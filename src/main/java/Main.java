import core.Operator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.controllers.ItineraryController;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("screens/itinerary.fxml"));
        Parent root = loader.load();
        ItineraryController controller = loader.getController();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Week Calendar");
        stage.setScene(scene);
        stage.show();

        // Initial update of labels
        controller.updateLabels();
    }

    public static void main(String[] args) {
        System.out.println("Application started.");
        Operator.start();
        launch();
        System.out.println("Application finished.");
    }
}
