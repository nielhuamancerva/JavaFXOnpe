package onpe.com.pe.gestorconfiguracionactas.endpoint;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import onpe.com.pe.gestorconfiguracionactas.App;
import onpe.com.pe.gestorconfiguracionactas.core.util.VariableGlobales;

public class InicioMenuController implements Initializable {

    @FXML
    private Label lblNameEleccion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void ingresarConfiguracion() throws IOException {
//        App.setRoot(null, "configurarActa");
//        VariableGlobales.lecturaActasEnMemoria.clear();// para limpiara la imagen
//        VariableGlobales.coordenadasActa.clear();
//        VariableGlobales.identificaActa.clear();
        App.setRoot(null, "configuraSecciones");
    }

    private void ingresarGestor() throws IOException {
        App.setRoot(null, "gestorActa");
    }

    @FXML
    private void ingresarInicializador() throws IOException {
        App.setRoot(null, "configurationDoc");
    }

}
