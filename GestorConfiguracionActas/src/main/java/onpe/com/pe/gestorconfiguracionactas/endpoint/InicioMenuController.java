/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package onpe.com.pe.gestorconfiguracionactas.endpoint;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import onpe.com.pe.gestorconfiguracionactas.App;

/**
 * FXML Controller class
 *
 * @author RDeLaCruz
 */
public class InicioMenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ingresarConfiguracion() throws IOException {
//        App.setRoot(null, "configurarActa");
        App.setRoot(null, "configurationDoc");
    }

    @FXML
    private void ingresarGestor() throws IOException {
        App.setRoot(null, "gestorActa");
    }

    @FXML
    private void ingresarInicializador() throws IOException {
        App.setRoot(null, "completion");
    }

}
