package onpe.com.pe.transmisionscore.dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import onpe.com.pe.transmisionscore.App;

public class InicioMenuController implements Initializable {

    @FXML
    private Button btnTransmision;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ingresarTransmision() throws IOException {
        App.setRoot(null, "transmisionMq");

    }

    @FXML
    private void ingresarSeguimientoActa()throws  IOException{
         App.setRoot(null, "seguimientoActa");
    }

    @FXML
    private void ingresarSeguimientoTransmision() throws  IOException{
         App.setRoot(null, "seguimientoTransmision");
    }

}
