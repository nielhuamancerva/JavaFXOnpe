package com.mycompany.loging.endpoint.dashboard;


import com.google.gson.Gson;
import com.mycompany.loging.App;
import com.mycompany.loging.score.negocio.NegocioServiceImpl;
import com.mycompany.loging.score.negocio.service.NegocioService;
import com.mycompany.loging.score.util.CreateObject;
import com.mycompany.loging.score.util.constanst.VariableGlobales;
import static com.mycompany.loging.score.util.constanst.VariableGlobales.list;
import com.mycompany.loging.score.util.mapper.Mapper;
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

    private static final String QUEUE_NAME = "cola_niel";
    private static final String ALGORITMO = "AES";
    private static final byte[] CLAVE_SECRETA = "EstaEsUnaClaveSecreta".getBytes();

    private final NegocioService negocioService;

    @FXML
    ImageView observacionesActa, codigoBarra;
    @FXML
    private Button btnRegresar, btnContinuar, btnultimo;
    @FXML
    TextArea textObservaciones;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label numVotoPreferencial, lblTipoActa;
    @FXML
    private Label etiquetaVotoRev;
    @FXML
    private Button btnSiguiente;

    public RegistrarObsController() {
        this.negocioService = new NegocioServiceImpl();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            negocioService.readAndCutObservations(
                    Mapper.transformaTointerger(VariableGlobales.configuracionActa.get(list.get(4) + "Xo")),
                    Mapper.transformaTointerger(VariableGlobales.configuracionActa.get(list.get(4) + "Yo")),
                    Mapper.transformaTointerger(VariableGlobales.configuracionActa.get(list.get(4) + "Ancho")),
                    Mapper.transformaTointerger(VariableGlobales.configuracionActa.get(list.get(4) + "Alto"))
            );
            lblTipoActa.setText(VariableGlobales.lecturaActasEnMemoria.get("tipoActa"));
            
            //Field field = Button.class.getDeclaredField("defaultButton");
            //field.setAccessible(true);
    
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        observacionesActa.setImage(CreateObject.image(VariableGlobales.lecturaActasEnMemoria.get("observaciones")));
        codigoBarra.setImage(CreateObject.image(VariableGlobales.lecturaActasEnMemoria.get("codigoBarra")));
    }

    @FXML
    private void regresaActasV() throws IOException {
        App.setRoot(null, "leerActasVotos");
    }

    @FXML
    private void ActionFinaliza() throws Exception {
//
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("172.16.89.225");
//        factory.setUsername("admin");
//        factory.setPassword("admin");
//        factory.setVirtualHost("/");
////        factory.setHost("localhost");
////        factory.setUsername("guest");
////        factory.setPassword("guest");
////        factory.setVirtualHost("/");
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//        Gson gson = new Gson();
//        
//        System.out.println(VariableGlobales.actasLeida);
//     String json = cifrar(gson.toJson(negocioService.uploadActaReadOnMemory(VariableGlobales.actasLeida)));
////String json = "niel";
//        channel.basicPublish("", QUEUE_NAME, null, json.getBytes("UTF-8"));
//
//        channel.close();
//        connection.close();
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Confirmación de transmisión");
//        alert.setHeaderText("¡Transmisión exitosa!");
//        alert.setContentText("Se ha enviado correctamente la transmisión.");
//        alert.showAndWait();
//
    }

    @FXML
    private void ultimaaccion() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.16.89.225");
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        
        
        //System.out.println(VariableGlobales.actasLeida);
        
        Gson gson = new Gson();
        String json = cifrar(gson.toJson(negocioService.uploadActaReadOnMemory(VariableGlobales.actasLeida)));
        
        
        channel.basicPublish("", QUEUE_NAME, null, json.getBytes("UTF-8"));

        channel.close();
        connection.close();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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


}
