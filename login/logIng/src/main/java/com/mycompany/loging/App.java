package com.mycompany.loging;

import com.mycompany.loging.score.util.constanst.VariableGlobales;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

/**
 * JavaFX App ERRsadsad
 */
public class App extends Application {

    private static Scene scene;
    private static Stage sc;

//    private double xPos;
//    private double yPos;
//    private double width;
//    private double height;

    @Override
    public void start(Stage stage) throws IOException {
        //scene = new Scene(loadFXML("login"), 1200, 800);
        setRoot(stage, "login");
        sc = stage;
        
        // Guardamos la posición y el tamaño de la ventana
//        stage.setOnHiding(event -> {
//            xPos = stage.getX();
//            yPos = stage.getY();
//            width = stage.getWidth();
//            height = stage.getHeight();
//        });

        // Restauramos la posición y el tamaño de la ventana
//        stage.setOnShowing(event -> {
//            stage.setX(xPos);
//            stage.setY(yPos);
//            stage.setWidth(width);
//            stage.setHeight(height);
//        });


    }

    public static void setRoot(Stage stage, String fxml) throws IOException {

        if (Objects.nonNull(stage)) {
            scene = new Scene(loadFXML(fxml));// "login" coambiar por login para iniciar  
            stage.setFullScreen(false); //true
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
