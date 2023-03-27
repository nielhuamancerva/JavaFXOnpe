/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package onpe.com.pe.transmisionscore.dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import onpe.com.pe.transmisionscore.App;

/**
 * FXML Controller class
 *
 * @author RDeLaCruz
 */
public class InicioMenuController implements Initializable {

    @FXML
    private Button btnTransmision;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ingresarTransmision() throws IOException {
        App.setRoot(null, "transmisionMq");

    }

}
