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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import onpe.com.pe.gestorconfiguracionactas.App;
import onpe.com.pe.gestorconfiguracionactas.core.business.BusinessService;
import onpe.com.pe.gestorconfiguracionactas.core.business.Impl.BusinessServiceImpl;
import onpe.com.pe.gestorconfiguracionactas.core.model.Setting;
import onpe.com.pe.gestorconfiguracionactas.core.util.VariableGlobales;

/**
 * FXML Controller class
 *
 * @author rdela
 */
public class ValidarSeccionController implements Initializable {

    private final BusinessService businessService;
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
    @FXML
    private Label lbActaValidar;

    /**
     * Initializes the controller class.
     */
    public ValidarSeccionController() {
        this.businessService = new BusinessServiceImpl();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        lbActaValidar.setText(VariableGlobales.coordenadasActa.get("nombreActaSeleccion"));
        //System.out.println("DATO:"+VariableGlobales.coordenadasActa.get("nombreActaSeleccion"));
    }

    @FXML
    private void actionFinaliza(ActionEvent event) {

        try {
            System.out.println("datos en Globales=" + VariableGlobales.coordenadasActa.toString());
            System.out.println("datos en Globales=" + VariableGlobales.identificaActa.get("idSectionActaSeleccion"));
            businessService.uploadSections(VariableGlobales.identificaActa.get("idSectionActaSeleccion"), VariableGlobales.coordenadasActa.toString());// actualiza en base de datos ESTO VA A CONFIRMAR
        } catch (Exception e) {
            System.out.println("datos:" + e);
        }
//        try {
//            for (Setting item : businessService.findAllSettingOnlyEleccion()) {
//            }
//
//        } catch (Exception e) {
//        }
    }

    @FXML
    private void actionRegresar() throws IOException {

        App.setRoot(null, "configuraSecciones");
    }

}
