package mc322project;

import com.gluonhq.attach.storage.StorageService;
import com.gluonhq.attach.util.Services;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mc322project.core.Operator;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class GUIStarter extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(GUIStarter.class.getResource("screens/start.fxml"));
        Parent root = loader.load();
        System.setProperty("javafx.verbose", "true");
        Scene scene = new Scene(root, 800, 600);
        String stylesheet = GUIStarter.class.getResource("styling/styles.css").toExternalForm();
        String stylesheet2 = GUIStarter.class.getResource("styling/date_selector.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        scene.getStylesheets().add(stylesheet2);
        stage.setTitle("Itinerary Maker");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println("Application started.");

        /*String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win") || osName.contains("unix") || osName.contains("mac")) {
            System.setProperty("javafx.platform", "Desktop");
        }

        StorageService storageService = new StorageService() {
            @Override
            public Optional<File> getPrivateStorage() {
                return Optional.of(new File(System.getProperty("user.home")));
            }

            @Override
            public Optional<File> getPublicStorage(String subdirectory) {
                return getPrivateStorage();
            }

            @Override
            public boolean isExternalStorageWritable() {
                if (getPrivateStorage().isPresent())
                    return getPrivateStorage().get().canWrite();
                return false;
            }

            @Override
            public boolean isExternalStorageReadable() {
                if (getPrivateStorage().isPresent())
                    return getPrivateStorage().get().canRead();
                return false;
            }
        };

        ServiceFactory<StorageService> storageServiceFactory = new ServiceFactory<>() {
            @Override
            public Class<StorageService> getServiceType() {
                return StorageService.class;
            }
            @Override
            public Optional<StorageService> getInstance() {
                return Optional.of(storageService);
            }
        };
        // register service
        Services.registerServiceFactory(storageServiceFactory);
        System.setProperty("java.lang.module.ignoreUnsupportedJFX", "true");*/

        launch(args);
        System.out.println("Application finished.");
    }
}
