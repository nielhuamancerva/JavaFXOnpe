/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onpe.com.pe.gestorconfiguracionactas.endpoint;

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
import onpe.com.pe.gestorconfiguracionactas.App;

/**
 *
 * @author rdela
 */
public class Footer_dashboardController implements Initializable {

    @FXML
    private Button btnAdministrador;
    @FXML
    private AnchorPane apShowMenu;
    @FXML
    private Button btnVersion;
    @FXML
    private Label lblOdpe;
    @FXML
    private Label lblAmbito;

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
    private void showVersion(ActionEvent event) {
    }

}
