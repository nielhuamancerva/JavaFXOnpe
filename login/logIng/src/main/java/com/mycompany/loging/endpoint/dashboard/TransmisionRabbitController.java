package com.mycompany.loging.endpoint.dashboard;

import com.mycompany.loging.App;
import com.mycompany.loging.score.util.DropShadowE;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class TransmisionRabbitController implements Initializable {
    private static final String QUEUE_NAME = "primer_score"; //nombre de la cola
    private DropShadowE dropShadowE;
    @FXML
    private Button btnTransmitir;
    @FXML
    private Button btnRegresar;
    public TransmisionRabbitController() {
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
        factory.setHost("172.16.89.225");
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "primer mensaje score XD";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + message + "'");
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
