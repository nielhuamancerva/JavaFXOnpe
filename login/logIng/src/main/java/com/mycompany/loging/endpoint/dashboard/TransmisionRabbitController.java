package com.mycompany.loging.endpoint.dashboard;

import com.google.gson.Gson;
import com.mycompany.loging.App;
import com.mycompany.loging.score.Repository.implementacion.ConexionMongoImpl;
import com.mycompany.loging.score.Repository.service.ConexionMongo;
import com.mycompany.loging.score.negocio.NegocioServiceImpl;
import com.mycompany.loging.score.negocio.service.NegocioService;
import com.mycompany.loging.score.util.DropShadowE;
import com.mycompany.loging.score.util.constanst.VariableGlobales;
import static com.mycompany.loging.score.util.constanst.VariableGlobales.lecturaActasEnMemoria;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class TransmisionRabbitController implements Initializable {
    private final NegocioService negocioService;
    private static final String QUEUE_NAME = UUID.randomUUID().toString(); //nombre de la cola
    private DropShadowE dropShadowE;
    @FXML
    private Button btnTransmitir;
    @FXML
    private Button btnRegresar;

    public TransmisionRabbitController() {
        this.negocioService = new NegocioServiceImpl();
        this.dropShadowE = new DropShadowE();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dropShadowE.setTabEffect(btnTransmitir);
        dropShadowE.setTabEffect(btnRegresar);
    }

    @FXML
    private void transmitir() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        Gson gson = new Gson();
        String json = gson.toJson(VariableGlobales.actasLeida);
        negocioService.uploadActaReadOnMemory(VariableGlobales.actasLeida);
        channel.basicPublish("", QUEUE_NAME, null, json.getBytes("UTF-8"));

        channel.close();
        connection.close();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("ok");
        alert.setContentText("Transmision confirmada");
        alert.showAndWait();
    }

    @FXML
    private void regresarFirmas() throws IOException {
        App.setRoot(null, "registrarFirma");

    }

}
