package onpe.com.pe.gestorconfiguracionactas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage sc;

    @Override
    public void start(Stage stage) throws IOException {

        setRoot(stage, "login");
        sc = stage;
    }

    public static void setRoot(Stage stage, String fxml) throws IOException {
        if (Objects.nonNull(stage)) {

            scene = new Scene(loadFXML(fxml));// "login" coambiar por login para iniciar  
            stage.setFullScreen(true); //true
            stage.setScene(scene);
            stage.show();
        } else {
            sc.setFullScreen(true);
            scene.setRoot(loadFXML(fxml));
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
