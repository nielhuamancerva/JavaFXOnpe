package com.mycompany.loging.endpoint.dashboard;

import com.google.gson.Gson;
import com.mycompany.loging.App;
import com.mycompany.loging.score.negocio.NegocioServiceImpl;
import com.mycompany.loging.score.negocio.service.NegocioService;
import com.mycompany.loging.score.util.CreateObject;
import com.mycompany.loging.score.util.constanst.VariableGlobals;
import static com.mycompany.loging.score.util.constanst.VariableGlobals.list;
import static com.mycompany.loging.score.util.constanst.VariableGlobals.nombreDelArchivoProcesado;
import com.mycompany.loging.score.util.mapper.Mappers;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class RegistrarObsController implements Initializable {

    private final NegocioService negocioService;

    @FXML
    ImageView observacionesActa;
    @FXML
    TextArea textObservaciones;
    @FXML
    private Label lblTipoActa;

    public RegistrarObsController() {
        this.negocioService = new NegocioServiceImpl();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (!VariableGlobals.lecturaActasEnMemoria.get("observaciones").equals("")) {
            lblTipoActa.setText(nombreDelArchivoProcesado);
            observacionesActa.setImage(CreateObject.image(VariableGlobals.lecturaActasEnMemoria.get("observaciones")));
        }
    }

    @FXML
    private void regresaActasV() throws IOException {
        App.setRoot(null, "leerActasVotos");
    }

    @FXML
    private void transmitir() throws IOException {
        App.setRoot(null, "transmisionRabbit");
    }

}
