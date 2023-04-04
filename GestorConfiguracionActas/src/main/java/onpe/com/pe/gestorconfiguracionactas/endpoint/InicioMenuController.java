package onpe.com.pe.gestorconfiguracionactas.endpoint;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import onpe.com.pe.gestorconfiguracionactas.App;

public class InicioMenuController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void ingresarConfiguracion() throws IOException {
        App.setRoot(null, "configuraSecciones");
    }

    @FXML
    private void ingresarGestor() throws IOException {
        App.setRoot(null, "gestorActa");
    }

    @FXML
    private void ingresarInicializador() throws IOException {
        App.setRoot(null, "configurationDoc");
    }

}
