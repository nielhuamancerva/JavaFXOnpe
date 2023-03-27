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
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import onpe.com.pe.transmisionscore.App;

/**
 * FXML Controller class
 *
 * @author RDeLaCruz
 */
public class TransmisionMqController implements Initializable {

    @FXML
    private Button btnRecepcionar;
    @FXML
    private Button btnPlayPause;
    @FXML
    private ProgressBar progressBarRx;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnRecepcionar1;
    @FXML
    private Button btnPlayPause1;
    @FXML
    private ProgressBar progressBarRx1;
    @FXML
    private Label lblMensaje;
    @FXML
    private TextArea txtAreaResultado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnRecepcionar_OnAction(ActionEvent event) {
    }

    @FXML
    private void btnPlayPause_OnAction(ActionEvent event) {
    }

    @FXML
    private void regresarInicio() throws IOException{
        App.setRoot(null, "inicioMenu");
    }
    
}
