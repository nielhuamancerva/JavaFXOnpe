package com.mycompany.loging.endpoint.dashboard;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mycompany.loging.App;
import com.mycompany.loging.score.negocio.NegocioServiceImpl;
import com.mycompany.loging.score.negocio.service.NegocioService;
import com.mycompany.loging.score.util.DropShadowE;
import com.mycompany.loging.score.util.constanst.VariableGlobales;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import com.mycompany.loging.score.util.CreateObject;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class TransmisionRabbitController implements Initializable {

    private final NegocioService negocioService;
    private static final String QUEUE_NAME = "cola_niel"; //nombre de la cola
    private DropShadowE dropShadowE;

    private static final String ALGORITMO = "AES";
    private static final byte[] CLAVE_SECRETA = "EstaEsUnaClaveSecreta".getBytes();
    
    @FXML
    ImageView observacionesActa, codigoBarra;
   
    
    private Label lblTipoActa;
    @FXML
    private Button btnTransmitir;
    @FXML
    private Button btnRegresar;
    
    public TransmisionRabbitController() {
        this.negocioService = new NegocioServiceImpl();
//        this.dropShadowE = new DropShadowE();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        dropShadowE.setTabEffect(btnTransmitir);
//        dropShadowE.setTabEffect(btnRegresar);

        try {
                negocioService.readAndCutObservations(
                Integer.parseInt(VariableGlobales.configuracionActa.get("4" + "Xo")),
                Integer.parseInt(VariableGlobales.configuracionActa.get("4" + "Yo")),
                Integer.parseInt(VariableGlobales.configuracionActa.get("4" + "Ancho")),
                Integer.parseInt(VariableGlobales.configuracionActa.get("4" + "Alto"))
            );
                lblTipoActa.setText(VariableGlobales.lecturaActasEnMemoria.get("tipoActa"));
        } catch (Exception e) {
        }
        observacionesActa.setImage(CreateObject.image(VariableGlobales.lecturaActasEnMemoria.get("observaciones")));
        codigoBarra.setImage(CreateObject.image(VariableGlobales.lecturaActasEnMemoria.get("codigoBarra")));
    }
    
    public String toJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
    
    @FXML
    private void transmitir() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.16.89.225");
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setVirtualHost("/");
//        factory.setHost("localhost");
//        factory.setUsername("guest");
//        factory.setPassword("guest");
//        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //Gson gson = new Gson();
        //String json = 
        //cifrar(gson.toJson(negocioService.uploadActaReadOnMemory(VariableGlobales.actasLeida)));
        String jsonString = null;
	ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	jsonString = (cifrar(mapper.writeValueAsString(negocioService.uploadActaReadOnMemory(VariableGlobales.actasLeida))));
        channel.basicPublish("", QUEUE_NAME, null, jsonString.getBytes("UTF-8"));

        channel.close();
        connection.close();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmación de transmisión");
        alert.setHeaderText("¡Transmisión exitosa!");
        alert.setContentText("Se ha enviado correctamente la transmisión.");
        alert.showAndWait();
    }

    public String cifrar(String texto) throws Exception {
        String ALGORITHM = "AES";
        String KEY = "mySecretKey12345";
        SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedValue = cipher.doFinal(texto.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedValue);
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

    @FXML
    private void regresarFirmas() throws IOException {
        App.setRoot(null, "registrarObs");

    }
    private void regresaActasV() throws IOException {
        App.setRoot(null, "leerActasVotos");
    }
}
