
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
public class HeaderController implements Initializable  {

    @FXML
    private Button btnAdministrador;
    @FXML
    private Label lblNameEleccion;
    @FXML
    private AnchorPane apBloker;
    @FXML
    private Label lblModule;
    @FXML
    private Button btnBackWindow;
    @FXML
    private Label lblOption;
    @FXML
    private AnchorPane apShowMenu;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void btnAdministradorExited(MouseEvent event) {
    }

    @FXML
    private void btnAdministradorEntered(MouseEvent event) {
    }

    @FXML
    private void backAction(ActionEvent event) {
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
