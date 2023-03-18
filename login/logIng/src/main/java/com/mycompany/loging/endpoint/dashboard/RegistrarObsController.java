package com.mycompany.loging.endpoint.dashboard;

import com.mycompany.loging.App;
import com.mycompany.loging.score.negocio.NegocioServiceImpl;
import com.mycompany.loging.score.negocio.service.NegocioService;
import com.mycompany.loging.score.util.CreateObject;
import com.mycompany.loging.score.util.constanst.VariableGlobales;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class RegistrarObsController implements Initializable {

    private final NegocioService negocioService;
    
    @FXML
    ImageView observacionesActa, codigoBarra;

    @FXML
    TextArea textObservaciones;

    public RegistrarObsController() {
        this.negocioService = new NegocioServiceImpl();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            negocioService.readAndCutObservations(290, 3865, 2360, 350);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        observacionesActa.setImage(CreateObject.image(VariableGlobales.lecturaActasEnMemoria.get("observaciones")));
        codigoBarra.setImage(CreateObject.image(VariableGlobales.lecturaActasEnMemoria.get("codigoBarra")));
    }

    @FXML
    private void registrarFma() throws IOException {
        VariableGlobales.actasLeida.setObservaciones(textObservaciones.getText());
        App.setRoot(null, "registrarFirma");
    }

    @FXML
    private void regresaActasV() throws IOException {
        App.setRoot(null, "leerActasVotos");
    }

}
