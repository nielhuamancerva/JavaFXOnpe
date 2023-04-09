package com.mycompany.loging.endpoint.dashboard;

import com.google.gson.Gson;
import com.mycompany.loging.App;
import com.mycompany.loging.score.model.Modules;
import com.mycompany.loging.score.negocio.NegocioServiceImpl;
import com.mycompany.loging.score.negocio.service.NegocioService;
import com.mycompany.loging.score.util.CreateObject;
import com.mycompany.loging.score.util.constanst.VariableGlobals;
import static com.mycompany.loging.score.util.constanst.VariableGlobals.list;
import static com.mycompany.loging.score.util.constanst.VariableGlobals.listModules;
import static com.mycompany.loging.score.util.constanst.VariableGlobals.nombreDelArchivoProcesado;
import static com.mycompany.loging.score.util.constanst.VariableGlobals.viewLoad;
import com.mycompany.loging.score.util.mapper.Mappers;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        for (Modules module : listModules) {
            switch (module.getTypeModule()) {
                case "Observaciones": {
                    try {
                        negocioService.readAndCutObservations(
                                Mappers.transformaTointerger(module.getCoordinatesXo()),
                                Mappers.transformaTointerger(module.getCoordinatesYo()),
                                Mappers.transformaTointerger(module.getCoordinatesWigth()),
                                Mappers.transformaTointerger(module.getCoordinatesHeigth()));
                        lblTipoActa.setText(nombreDelArchivoProcesado);
                        observacionesActa.setImage(CreateObject.image(VariableGlobals.lecturaActasEnMemoria.get("observaciones")));
                    } catch (Exception ex) {
                        Logger.getLogger(RegistrarObsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                default: {
                    try {
                        App.setRoot(null, "transmisionRabbit");
                    } catch (IOException ex) {
                        Logger.getLogger(RegistrarObsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

            }
        }

    }

    @FXML
    private void regresaActasV() throws IOException {
//        App.setRoot(null, "leerActasVotos");
        VariableGlobals.viewPosition--;
        App.setRoot(null, VariableGlobals.viewOrder.get(viewLoad.get(VariableGlobals.viewPosition)));
    }

    @FXML
    private void transmitir() throws IOException {
//        App.setRoot(null, "transmisionRabbit");
        App.setRoot(null, VariableGlobals.viewOrder.get(viewLoad.get(VariableGlobals.viewPosition++)));
    }

}
