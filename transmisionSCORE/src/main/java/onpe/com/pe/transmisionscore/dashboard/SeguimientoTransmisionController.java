/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package onpe.com.pe.transmisionscore.dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import onpe.com.pe.transmisionscore.App;
import onpe.com.pe.transmisionscore.core.model.Actas;
import onpe.com.pe.transmisionscore.core.negocio.NegocioServiceImpl;
import onpe.com.pe.transmisionscore.core.negocio.service.NegocioService;
import onpe.com.pe.transmisionscore.core.util.common.CreacionTable;

/**
 * FXML Controller class
 *
 * @author RDeLaCruz
 */
public class SeguimientoTransmisionController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label numVotoPreferencial;
    @FXML
    private Label etiquetaVotoRev;
    @FXML
    private TableView<Actas> tableActas;

    private final NegocioService negocioService;

    public SeguimientoTransmisionController() {
        this.negocioService = new NegocioServiceImpl();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            negocioService.loadSettingActa();
            CreacionTable yy = new CreacionTable();
            yy.viewActas(tableActas);
            tableActas.setItems(negocioService.finAllActas());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void actionSalir() throws IOException {
        App.setRoot(null, "inicioMenu");
    }

}
