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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import onpe.com.pe.gestorconfiguracionactas.App;

/**
 * FXML Controller class
 *
 * @author rdela
 */
public class ConfigurationDocController implements Initializable {

    @FXML
    private ComboBox<?> cobDocumentos;
    @FXML
    private Button btnAddDocumentos;
    @FXML
    private Button btnAddSeccion;
    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnContinuar;
    @FXML
    private Button ico_grabar;
    @FXML
    private Button ico_editar;
    @FXML
    private Button ico_eliminar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void actionRegresar(ActionEvent event) {
    }

    @FXML
    private void actionGuardar(ActionEvent event) {
    }

    @FXML
    private void actionContinuar() throws IOException{
        
        App.setRoot(null, "configuraSecciones");
        
        
    }
    
}
