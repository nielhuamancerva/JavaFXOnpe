/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package onpe.com.pe.transmisionscore;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author RDeLaCruz
 */
public class LoginFController implements Initializable {

    
    @FXML
    private TextField userName;
    
    @FXML
    private PasswordField passwordField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    private void iniciandoSecion(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Usuario no registrado");
        alert.setHeaderText("El usuario ingresado no está registrado");
        alert.setContentText("Por favor, verifique que ha ingresado correctamente sus datos");

        alert.showAndWait();

    }

}
