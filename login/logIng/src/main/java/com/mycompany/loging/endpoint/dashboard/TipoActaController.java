/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.loging.endpoint.dashboard;

import com.mycompany.loging.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author RDeLaCruz
 */
public class TipoActaController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnSalirInicio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void actionActainstalacion() throws IOException {
        App.setRoot(null, "cargarActaInstalacion");
    }

    @FXML
    private void actionActaEscrutinio() throws IOException {
        App.setRoot(null, "cargarActaEscrutinio");

    }

    @FXML
    private void ActionSalir() throws IOException{
        App.setRoot(null, "inicioMenu");
    }

}
