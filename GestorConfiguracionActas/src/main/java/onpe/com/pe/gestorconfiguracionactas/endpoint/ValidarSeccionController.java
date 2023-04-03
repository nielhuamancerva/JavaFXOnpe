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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import onpe.com.pe.gestorconfiguracionactas.App;

/**
 * FXML Controller class
 *
 * @author rdela
 */
public class ValidarSeccionController implements Initializable {

    @FXML
    private ComboBox<?> cboDocumentos;
    @FXML
    private ScrollPane scrollPaneActa;
    @FXML
    private ImageView imgViewActa;
    @FXML
    private Button btnFinaliza;
    @FXML
    private Button btnRegresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void actionFinaliza(ActionEvent event) {
    }

    @FXML
    private void actionRegresar() throws IOException{
        
        App.setRoot(null, "configuraSecciones");
    }
    
}
