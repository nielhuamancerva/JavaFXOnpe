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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author rdela
 */
public class HeaderController implements Initializable {

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
        apShowMenu.setVisible(false);

    }

    @FXML
    private void btnAdministradorExited(MouseEvent event) {
        apShowMenu.setVisible(false);

    }

    @FXML
    private void btnAdministradorEntered(MouseEvent event) {
        apShowMenu.setVisible(true);

    }

    @FXML
    private void handleLogout() throws IOException {
        App.setRoot(null, "login");

    }

    @FXML
    private void apCerrarSesionExited(MouseEvent event) {
        apShowMenu.setVisible(false);

    }

    @FXML
    private void apCerrarSesionEntered(MouseEvent event) {
        apShowMenu.setVisible(true);

    }

    @FXML
    private void actionMenu() throws IOException {
        App.setRoot(null, "inicioMenu");
    }

}
