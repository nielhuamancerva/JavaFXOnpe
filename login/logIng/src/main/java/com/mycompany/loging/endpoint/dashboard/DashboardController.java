package com.mycompany.loging.endpoint.dashboard;

import com.mycompany.loging.App;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class DashboardController {

    @FXML
    private void switchToTransmision() throws IOException {
        //App.setRoot("primary");
        App.setRoot(null, "cargarActas");

    }

    @FXML
    private void switchToConfig() throws IOException {
        App.setRoot(null, "iniciarConfiguracion");

    }
}
