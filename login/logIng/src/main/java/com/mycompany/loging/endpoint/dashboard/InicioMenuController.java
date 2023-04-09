package com.mycompany.loging.endpoint.dashboard;

import com.mycompany.loging.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class InicioMenuController implements Initializable {

    @FXML
    private Label lblNameEleccion;
    @FXML
    private Button btnRegistro;
    @FXML
    private Button btnSeguimiento;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

    }

    @FXML
    private void switchToTransmision() throws IOException {
        App.setRoot(null, "verificaFirmas");

    }

    @FXML
    private void ingresarSeguimientoActa() throws IOException {
        App.setRoot(null, "seguimientoActa");

    }


}
