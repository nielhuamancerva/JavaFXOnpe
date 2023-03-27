package onpe.com.pe.gestorconfiguracionactas.endpoint;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.lang.System.Logger;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;

import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import onpe.com.pe.gestorconfiguracionactas.App;
import onpe.com.pe.gestorconfiguracionactas.core.business.BusinessService;
import onpe.com.pe.gestorconfiguracionactas.core.business.Impl.BusinessServiceImpl;

/**
 * FXML Controller class
 *
 * @author CASSHERN
 */
public class GestorActaController implements Initializable {

    private final BusinessService businessService;
    /**
     * Initializes the controller class.
     */
    @FXML
    Button btnActivar;

    @FXML
    ComboBox<String> cbSelectEleccion;

    @FXML
    TextField textEleccionIsActive;

    public GestorActaController() {
        this.businessService = new BusinessServiceImpl();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        try {
            textEleccionIsActive.setText(businessService.findSettingWithEleccionIsActived());
            cbSelectEleccion.setItems(businessService.findAllSettingOnlyEleccion());
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(GestorActaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void regresaMenu() throws IOException {
        App.setRoot(null, "inicioMenu");

    }

    @FXML
    private void methodForActivedEleccion() throws Exception {
        businessService.uploadSettingToIsActived(cbSelectEleccion.getValue());
       App.setRoot(null, "gestorActa");
    }
}
