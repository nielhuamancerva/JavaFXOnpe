/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package onpe.com.pe.gestorconfiguracionactas.endpoint;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import onpe.com.pe.gestorconfiguracionactas.App;

import onpe.com.pe.gestorconfiguracionactas.core.util.VariableGlobales;
import static onpe.com.pe.gestorconfiguracionactas.core.util.VariableGlobales.arrayNombresModulos;

/**
 * FXML Controller class
 *
 * @author LMedina
 */
public class ConfirmarRegionesActasController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarRegionesActa();

    }
    private Button[] buttonEventConfi = new Button[arrayNombresModulos.length + 1];
    @FXML
    ImageView imgViewActaMarcada;

    @FXML
    private void confirmarRegion() throws IOException {
        //EN ESTA FUNCION SE VERFICARAN LAS REGIONES PINTADAS Y SI NO TIENE VALOR NO SE PINTA PARA EVITAR LA EXCEPCION
        VariableGlobales.configuracionActa.put("confirmarActa", "1");
        App.setRoot(null, "configurarActa");
    }

    private void cargarRegionesActa() {
        //cargando imagen del acta
        Image img = new Image("file:" + VariableGlobales.lecturaActasEnMemoria.get("fileNamePathOriginal"));// nota poner el file para poner la imagen

        imgViewActaMarcada.setImage(img);

        //dibujando el rectangulo sobre la imagen
        Canvas canvas = new Canvas(imgViewActaMarcada.getImage().getWidth(), imgViewActaMarcada.getImage().getHeight());// capura el alto y ancho de la acta scaneada
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(imgViewActaMarcada.getImage(), 0, 0);
        gc.setFill(Color.color(0, 0, 0, 0.5)); // para el relleno
        gc.setStroke(Color.RED);
        gc.setLineWidth(10);

        for (int u = 0; u <= buttonEventConfi.length - 1; u++) {
            if (VariableGlobales.configuracionActa.get(u + "Xo") != null) {
                gc.strokeRect(Integer.parseInt(VariableGlobales.configuracionActa.get(u + "Xo")),
                        Integer.parseInt(VariableGlobales.configuracionActa.get(u + "Yo")),
                        Integer.parseInt(VariableGlobales.configuracionActa.get(u + "Ancho")),
                        Integer.parseInt(VariableGlobales.configuracionActa.get(u + "Alto")));

                gc.fillRect(Integer.parseInt(VariableGlobales.configuracionActa.get(u + "Xo")),
                        Integer.parseInt(VariableGlobales.configuracionActa.get(u + "Yo")),
                        Integer.parseInt(VariableGlobales.configuracionActa.get(u + "Ancho")),
                        Integer.parseInt(VariableGlobales.configuracionActa.get(u + "Alto")));

            }
        }

        for (int k = 0; k <= arrayNombresModulos.length - 1; k++) {
            if (VariableGlobales.configuracionActa.get(k + "Xo") != null) {
                gc.setFill(Color.GOLD);
                gc.setFont(Font.font("Arial", FontWeight.BOLD, 80));
                
                   gc.fillText(arrayNombresModulos[k], 
                           Integer.parseInt(VariableGlobales.configuracionActa.get(k + "Xo")),
                           Integer.parseInt(VariableGlobales.configuracionActa.get(k + "Yo")));
        

            }
        }

    
        for (Map.Entry<String, String> entry : VariableGlobales.configuracionActa.entrySet()) {
            String clave = entry.getKey();
            String valor = entry.getValue();
            System.out.println(clave + "|||" + valor);// hacer una lista con las regiones y preguntar si el nombre esta alli
//            if(valor != null){
//                gc.strokeRect(Integer.parseInt(VariableGlobales.configuracionActa.get("codigoBarraCoordenaXo")), Integer.parseInt(VariableGlobales.configuracionActa.get("codigoBarraCoordenaYo")), Integer.parseInt(VariableGlobales.configuracionActa.get("codigoBarraCoordenaAncho")), Integer.parseInt(VariableGlobales.configuracionActa.get("codigoBarraCoordenaAlto")));
//            }
        }
        //Recreando la imagen
        WritableImage ImW = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        PixelWriter gcIw = ImW.getPixelWriter();
        canvas.snapshot(null, ImW);
        imgViewActaMarcada.setImage(ImW);
        //spA.setContent(imgViewActaMarcada);

    }

}
