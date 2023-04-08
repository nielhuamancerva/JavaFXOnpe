/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loging.endpoint.dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author rdela
 */
public class Footer_dashboardController implements Initializable {

    @FXML
    private Button btnVersion;
    @FXML
    private Button btnAdministrador;
    @FXML
    private AnchorPane apShowMenu;
    @FXML
    private Label lblOdpe;
    @FXML
    private Label lblAmbito;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void showVersion(ActionEvent event) {
    }

    @FXML
    private void btnAdministradorExited(MouseEvent event) {
    }

    @FXML
    private void btnAdministradorEntered(MouseEvent event) {
    }

    @FXML
    private void handleLogout(ActionEvent event) {
    }

    @FXML
    private void apCerrarSesionExited(MouseEvent event) {
    }

    @FXML
    private void apCerrarSesionEntered(MouseEvent event) {
    }

}
