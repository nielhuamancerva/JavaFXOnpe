/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package onpe.com.pe.gestorconfiguracionactas.endpoint;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import onpe.com.pe.gestorconfiguracionactas.App;

/**
 * FXML Controller class
 *
 * @author NHuaman
 */
public class DashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void switchToConfig() throws IOException {
        App.setRoot(null, "configurarActa");
    }
//    @FXML
//    private void switchToConfig() throws IOException {
//        App.setRoot(null, "configurationDoc");
//    }
  
    @FXML

    private void switchToManagment() throws IOException {
        App.setRoot(null, "gestorActa");
    }

}
