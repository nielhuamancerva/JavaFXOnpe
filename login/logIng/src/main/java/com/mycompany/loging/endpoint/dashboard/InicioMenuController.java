
package com.mycompany.loging.endpoint.dashboard;

import com.mycompany.loging.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class InicioMenuController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void switchToTransmision() throws IOException {
        App.setRoot(null, "verificaFirmas");

    }

    @FXML
    private void ingresarSeguimientoActa() throws IOException{
        App.setRoot(null, "seguimientoActa");
        
    }
    
}
