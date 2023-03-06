package com.mycompany.loging.endpoint.dashboard;

import com.mycompany.loging.score.model.Actas;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import com.mycompany.loging.App;
import com.mycompany.loging.score.negocio.NegocioServiceImpl;
import com.mycompany.loging.score.negocio.service.NegocioService;
import com.mycompany.loging.score.util.CreacionTable;
import com.mycompany.loging.score.util.VariableGlobales;
import java.util.HashMap;
import java.util.Map;
import javafx.event.ActionEvent;

/**
 *
 * @author LMedina
 */
public class TransmisionController implements Initializable {

    private final NegocioService negocioService;
    private File fileSeleccionado;

    public TransmisionController() {
        this.negocioService = new NegocioServiceImpl();
    }

    @FXML
    Label lbArchivosEncontrados;

    @FXML
    TableView<Actas> tableActas;

    @FXML
    private void elegirFichero() throws IOException {
        FileChooser fileChoiser = new FileChooser();
        fileChoiser.setTitle("Elegir Actas");
        fileSeleccionado = fileChoiser.showOpenDialog(null);
        lbArchivosEncontrados.setText(fileSeleccionado.getName());
        VariableGlobales.lecturaActasEnMemoria = new HashMap();
        VariableGlobales.lecturaActasEnMemoria.put("fileName", fileSeleccionado.getName());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            CreacionTable yy = new CreacionTable();
            yy.viewActas(tableActas);
            tableActas.setItems(negocioService.finAllActas());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void cargarActas() throws IOException {
        App.setRoot(null, "leerActas");
    }

    @FXML
    private void regresarDashboard() throws IOException {
        App.setRoot(null, "dashboard");
    }

}
