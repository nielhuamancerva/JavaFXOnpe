/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.loging.endpoint.dashboard;

import com.mycompany.loging.App;
import com.mycompany.loging.score.util.DropShadowE;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author RDeLaCruz
 */
public class TransmisionRabbitController implements Initializable {

    private DropShadowE dropShadowE;

    @FXML
    private Button btnTransmitir;
    @FXML
    private Button btnRegresar;

    public TransmisionRabbitController() {
        this.dropShadowE = new DropShadowE();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dropShadowE.setTabEffect(btnTransmitir);
        dropShadowE.setTabEffect(btnRegresar);
    }

    @FXML
    private void transmitir() throws IOException {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("ok");
        alert.setContentText("Transmision confirmada");
        alert.showAndWait();
    }

    @FXML
    private void regresarFirmas() throws IOException {
        App.setRoot(null, "registrarFirma");

    }

}
