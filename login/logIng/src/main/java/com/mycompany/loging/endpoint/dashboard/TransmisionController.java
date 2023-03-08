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
import com.mycompany.loging.score.util.DropShadowE;
import com.mycompany.loging.score.util.constanst.VariableGlobales;
import java.util.HashMap;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;

/**
 *
 * @author LMedina
 */
public class TransmisionController implements Initializable {

    private final NegocioService negocioService;
    private File fileSeleccionado;
    private DropShadowE dropShadowE;

    @FXML
    private Button btnSalir;
    @FXML
    private Button btnIniciar;

    private void initialize() {

        dropShadowE.setTabEffect(btnIniciar);
        dropShadowE.setTabEffect(btnSalir);
    }

    public TransmisionController() {
        this.negocioService = new NegocioServiceImpl();
        this.dropShadowE = new DropShadowE();
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

        int dotIndex = fileSeleccionado.getPath().lastIndexOf(fileSeleccionado.getName());
        String pathFile = fileSeleccionado.getPath().substring(0, dotIndex);
        VariableGlobales.lecturaActasEnMemoria.put("pathTesseract", "D:\\TESSORC\\tessdata");
        VariableGlobales.lecturaActasEnMemoria.put("fileNamePathOriginal", fileSeleccionado.getPath());
        VariableGlobales.lecturaActasEnMemoria.put("fileNamePath", pathFile);
        VariableGlobales.lecturaActasEnMemoria.put("fileName", fileSeleccionado.getName());

        int dotNombreIndex = fileSeleccionado.getName().lastIndexOf(".");
        VariableGlobales.lecturaActasEnMemoria.put("fileNameSinExtension", fileSeleccionado.getName().substring(0, dotNombreIndex));

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
