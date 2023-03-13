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
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author RDeLaCruz
 */
public class IniciarConfController implements Initializable {

    @FXML
    private Label lbArchivosEncontrados;
    @FXML
    private TableView<?> tableActas;
    @FXML
    private Button btnCarga;
    @FXML
    private Button btnSalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void regresarDashboard() throws IOException{
        App.setRoot(null, "dashboard");
    }

    @FXML
    private void CargaActaConfig() throws IOException{
        App.setRoot(null, "configuraActa");
    }
    
}
