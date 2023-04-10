/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package onpe.com.pe.transmisionscore.dashboard;

import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Envelope;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.util.Duration;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import onpe.com.pe.transmisionscore.App;
import onpe.com.pe.transmisionscore.core.model.Transmision;
import onpe.com.pe.transmisionscore.core.repository.FactoryServices;

/**
 * FXML Controller class
 *
 * @author RDeLaCruz
 */
public class TransmisionMqController implements Initializable {

    @FXML
    private Button btnRecepcionar;
    @FXML
    private ProgressBar progressBarRx;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnRecepcionar1;
    @FXML
    private ProgressBar progressBarRx1;
    @FXML
    private Label lblMensaje;
    @FXML
    private TextArea txtAreaResultado;

    private String textoInicial = "";

    private boolean isPressed = false;

    private final static String EXCHANGE_NAME = "SCORE_TRANSMISION";
    private final static String[] QUEUE_NAMES = {"cola_niel", "cola_rodrigo", "cola_luis", "cola_ricardo"};
    private FactoryServices FactoryService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        progressBarRx.setStyle("-fx-accent: #78F93C;");
    }

    @FXML
    private void btnRecepcionar_OnAction(ActionEvent event) throws IOException, TimeoutException {

        if (isPressed) {
            lblMensaje.setText("Servidor Rabbit en Stop");
            btnRecepcionar.getStyleClass().add("btn_txrx_azul");
            btnRecepcionar.getStyleClass().remove("claseNueva");
            isPressed = false;
        } else {
            lblMensaje.setText("Iniciando Rabbit......");
            btnRecepcionar.getStyleClass().remove("btn_txrx_azul");
            btnRecepcionar.getStyleClass().add("claseNueva");
            isPressed = true;

        }

//        // Definir el formato deseado
//        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//        // Obtener la fecha y hora actual
//        LocalDateTime fechaHoraActual = LocalDateTime.now();
//        // Formatear la fecha y hora actual con el formato deseado
//        String fechaHoraFormateada = fechaHoraActual.format(formato);
        System.out.println(" inicio de rabbit ");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        com.rabbitmq.client.Connection connection = factory.newConnection();
        com.rabbitmq.client.Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

//        for (String queueName : QUEUE_NAMES) {
//            channel.queueDeclare(queueName, false, false, false, null);
//            channel.queueBind(queueName, EXCHANGE_NAME, queueName);
//            System.out.println(" [*] Waiting for messages on queue '" + queueName + "'");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {

            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            Platform.runLater(() -> {
                // aquí puede colocar el código necesario para procesar el mensaje en la interfaz de usuario de JavaFX
                String decryptString = null;
                try {
                    decryptString = decrypt(message);
                } catch (Exception ex) {
                    Logger.getLogger(TransmisionMqController.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(" body cola descriptado '" + decryptString + "'");
                Gson gson = new Gson();
                Transmision persona = gson.fromJson(decryptString, Transmision.class);
                Transmision persona1 = gson.fromJson(decryptString, Transmision.class);
                persona1.getBody().getImagen().setImagen("");

                System.out.println("Persona: " +gson.toJson(persona));
//                System.out.println("body" + gson.toJson(persona.getBody()));
                FactoryService = FactoryServices.getInstance();
                try {
                    Connection conn = FactoryService.ServicePostgreSQL().conexionPostgreSQL();
                    Statement stmt = conn.createStatement();

                    Random random = new Random();

                    String sql = "INSERT INTO tramasrecibidas (ncodtrama, strama, dfechahora,nestado,filebase64) VALUES (?, ?, ?,?,?)";
                    PreparedStatement statement = conn.prepareStatement(sql);

                    System.out.println(persona1.getBody().getActa());
                    statement.setInt(1, random.nextInt(1000));
                    statement.setString(2, persona1.getBody().getActa());
                    statement.setDate(3, Date.valueOf(LocalDate.now()));
                    
                    if (persona1.getBody().getFirma1().equals("true") && persona1.getBody().getFirma2().equals("true")
                            && persona1.getBody().getFirma3().equals("true")) {
//                        System.out.println(persona1.getBody().getFirma1() + "\n");    
//                        System.out.println(persona1.getBody().getFirma2() + "\n");    
//                        System.out.println(persona1.getBody().getFirma3() + "\n");    
                        statement.setInt(4, 1);
                    } else {
                        statement.setInt(4, 0);
//                         System.out.println(persona1.getBody().getFirma1() + "\n");    
//                        System.out.println(persona1.getBody().getFirma2() + "\n");    
//                        System.out.println(persona1.getBody().getFirma3() + "\n"); 

                    }

                    statement.setObject(5, persona.getBody().getImagen().getImagen());

                    statement.executeUpdate();

//                    txtAreaResultado.setText("Se recibio la cola el codigo de barra:"+ persona1.getBody().getActa() + " con fecha y hora " + LocalDateTime.now());
                    txtAreaResultado.appendText("Se recibio la cola con el codigo de barra:" + persona1.getBody().getActa() + " con fecha y hora " + LocalDateTime.now() + "\n");

                } catch (SQLException ex) {
                    Logger.getLogger(TransmisionMqController.class.getName()).log(Level.SEVERE, null, ex);
                }

                byte[] byteArray = Base64.getDecoder().decode(persona.getBody().getImagen().getImagen());

                ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
                BufferedImage image;
                try {
                    if (persona1.getBody().getFirma1().equals("true") && persona1.getBody().getFirma2().equals("true")
                            && persona1.getBody().getFirma3().equals("true")) {
                        image = ImageIO.read(bis);
                        File ff = new File("D:\\carpetaValido\\" + persona1.getBody().getActa() + ".png");
                        ImageIO.write(image, "png", ff);
                        
                    } else {
                        image = ImageIO.read(bis);
                        File ff = new File("D:\\carpetaInvalido\\" + persona1.getBody().getActa() + ".png");
                        ImageIO.write(image, "png", ff);
                    }

                } catch (IOException ex) {
                    Logger.getLogger(TransmisionMqController.class.getName()).log(Level.SEVERE, null, ex);
                }

                updateProgressBar();

            });

        };
//            DefaultConsumer consumer = new DefaultConsumer(channel) {
//                @Override
//                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                    String message = new String(body, "UTF-8");
//                    System.out.println(" ingreso de mensaje '" + message + "'");
//
//                    String decryptString = null;
//                    try {
//                        decryptString = decrypt(message);
//                    } catch (Exception ex) {
//                        Logger.getLogger(TransmisionMqController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    System.out.println(" body cola descriptado '" + decryptString + "'");
//                    Gson gson = new Gson();
//                    Transmision persona = gson.fromJson(decryptString, Transmision.class);
//                    Transmision persona1 = gson.fromJson(decryptString, Transmision.class);
//                    persona1.getBody().getImagen().setImagen("");
//                    System.out.println("body" + gson.toJson(persona.getBody()));
//
//                    FactoryService = FactoryServices.getInstance();
//                    try {
//                        Connection conn = FactoryService.ServicePostgreSQL().conexionPostgreSQL();
//                        Statement stmt = conn.createStatement();
//
//                        String sql = "INSERT INTO tramasrecibidas (ncodtrama, strama, dfechahora,nestado,filebase64) VALUES (?, ?, ?,?,?)";
//                        PreparedStatement statement = conn.prepareStatement(sql);
//
//                        //JsonObject jsonObject = gson.fromJson(persona.getBody().getImagen().getImagen(), JsonObject.class);
//                        System.out.println(persona1.getBody().getActa());
//
//                        statement.setString(2, persona1.getBody().getActa());
//                        statement.setDate(3, Date.valueOf(LocalDate.now()));
//                        statement.setInt(4, 1);
//
//                        statement.setObject(5, persona.getBody().getImagen().getImagen());
//                        //statement.setObject(5, jsonObject, Types.OTHER);
//                        //statement.setObject(5, persona1.getBody().getActa());
//
//                        statement.executeUpdate();
//                    } catch (SQLException ex) {
//                        Logger.getLogger(TransmisionMqController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                    byte[] byteArray = Base64.getDecoder().decode(persona.getBody().getImagen().getImagen());
//
//                    ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
//                    BufferedImage image = ImageIO.read(bis);
//
//                    File ff = new File("D:\\carpe\\decode.png");
//                    ImageIO.write(image, "png", ff);
//                }
//            };
//            channel.basicConsume(queueName, true, consumer);

        channel.basicConsume("cola_niel", true, deliverCallback, consumerTag -> {
        });
//        }
    }

    @FXML
    private void regresarInicio() throws IOException {
        App.setRoot(null, "inicioMenu");
    }

    public String decrypt(String encryptedValue) throws Exception {
        String ALGORITHM = "AES";
        String KEY = "mySecretKey12345";
        SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedValue = cipher.doFinal(Base64.getDecoder().decode(encryptedValue));
        return new String(decryptedValue, StandardCharsets.UTF_8);
    }

    private void updateProgressBar() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, ActionEvent -> progressBarRx.setProgress(0)),
                new KeyFrame(Duration.millis(500), ActionEvent -> progressBarRx.setProgress(1)),
                new KeyFrame(Duration.millis(2000), ActionEvent -> progressBarRx.setProgress(0))
        );
        timeline.setCycleCount(1);
        timeline.play();
    }

//    private void updateProgressBar() {
//        Timeline timeline = new Timeline(
//                new KeyFrame(Duration.ZERO, ActionEvent -> (progressBarRx.progressPropertyz(), 0)),
//        new KeyFrame(Duration.millis(500), new ActionEvent(progressBarRx.progressProperty(), 1))
//        ,
//                new KeyFrame(Duration.millis(2000), new ActionEvent(progressBarRx.progressProperty(), 0))
//        );
//
//        timeline.setCycleCount(1);
//        timeline.play();
//    }
}
