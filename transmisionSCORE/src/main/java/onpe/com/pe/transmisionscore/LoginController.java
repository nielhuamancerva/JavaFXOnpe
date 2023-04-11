/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package onpe.com.pe.transmisionscore;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import onpe.com.pe.transmisionscore.core.repository.FactoryServices;

/**
 * FXML Controller class
 *
 * @author NHuaman
 */
public class LoginController implements Initializable {

    private FactoryServices FactoryService;
    @FXML
    private Button btnIngresar;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField passwordField;
    @FXML
    private AnchorPane ppAnchorPane;
    @FXML
    private Button btnSalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    private void salirApp() throws Exception {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void iniciandoSecion() throws IOException{
        App.setRoot(null, "inicioMenu");
    }

    @FXML
    private void actionSalir() {
        Platform.exit();
        System.exit(0);

    }
}
