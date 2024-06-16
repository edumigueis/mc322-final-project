import core.Operator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.controllers.StartScreenController;

import java.io.IOException;
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("screens/start.fxml"));
        Parent root = loader.load();
        StartScreenController controller = loader.getController();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Itinerary Maker");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println("Application started.");
        Operator.start();
        launch(args);
        System.out.println("Application finished.");
    }
}
