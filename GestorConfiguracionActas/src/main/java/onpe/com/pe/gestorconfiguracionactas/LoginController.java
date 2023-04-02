/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package onpe.com.pe.gestorconfiguracionactas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.stage.Screen;
import onpe.com.pe.gestorconfiguracionactas.core.business.BusinessService;
import onpe.com.pe.gestorconfiguracionactas.core.business.Impl.BusinessServiceImpl;
import org.bson.Document;

/**
 * FXML Controller class
 *
 * @author NHuaman
 */
public class LoginController implements Initializable {

    private final BusinessService businessService;
    @FXML
    private Button btnIngresar;

    public LoginController() {
        this.businessService = new BusinessServiceImpl();
    }

    @FXML
    TextField userName;

    @FXML
    PasswordField passwordField;

    @FXML
    AnchorPane anchoPaneLogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        double scaleX = screenWidth / anchoPaneLogin.getPrefWidth();
        double scaleY = screenHeight / anchoPaneLogin.getPrefHeight();
        System.out.println("escale X" + scaleX);
        System.out.println("escale Y" + scaleY);
        double scaleFactor = Math.min(scaleX, scaleY);
        System.out.println("escale min" + scaleFactor);
//        anchoPaneLogin.setScaleX(scaleY);
//        anchoPaneLogin.setScaleY(scaleY);
        // TODO
        // Obtiene el tamaño de la pantalla

        //  anchoPaneLogin.setLayoutX(screenWidth/2);
//   anchoPaneLogin.setTranslateX(screenWidth/2);
        System.out.println("pantalla ancho" + screenWidth);
        System.out.println("pantalla alto" + screenHeight);
        System.out.println("alto anchor" + anchoPaneLogin.getHeight());
        System.out.println("alto anchor" + anchoPaneLogin.getWidth());
        System.out.println("ancho origional anchor" + anchoPaneLogin.getPrefWidth());
        System.out.println("posicion ancho" + anchoPaneLogin.getLayoutX());

        // Ajusta el tamaño del AnchorPane a la pantalla
        //  anchoPaneLogin.setPrefSize(screenWidth, screenHeight);
    }

    @FXML
    private void iniciandoSecion() throws Exception, IOException {

        Document document = businessService.findUserBy(userName.getText(), passwordField.getText());
        if (!document.isEmpty()) {
            App.setRoot(null, "inicioMenu");
        }

    }

    private void salirApp() throws Exception {
        Platform.exit();
        System.exit(0);
    }
}
