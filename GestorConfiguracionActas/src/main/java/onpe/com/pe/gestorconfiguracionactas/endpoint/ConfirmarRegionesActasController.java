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
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import onpe.com.pe.gestorconfiguracionactas.App;

import onpe.com.pe.gestorconfiguracionactas.core.util.VariableGlobales;

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

    @FXML
    ImageView imgViewActaMarcada;
    
     //cancelando
    @FXML
    public void botonCancelar() throws IOException {
        App.setRoot(null, "configurarActa");
    }

    @FXML
    private void confirmarRegion() throws IOException {
        //EN ESTA FUNCION SE VERFICARAN LAS REGIONES PINTADAS Y SI NO TIENE VALOR NO SE PINTA PARA EVITAR LA EXCEPCION
        VariableGlobales.configuracionActa.put("confirmarActa","1");
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

        if (VariableGlobales.configuracionActa.get("codigoBarraCoordenaXo") != null) {
            gc.strokeRect(Integer.parseInt(VariableGlobales.configuracionActa.get("codigoBarraCoordenaXo")), Integer.parseInt(VariableGlobales.configuracionActa.get("codigoBarraCoordenaYo")), Integer.parseInt(VariableGlobales.configuracionActa.get("codigoBarraCoordenaAncho")), Integer.parseInt(VariableGlobales.configuracionActa.get("codigoBarraCoordenaAlto")));
            gc.fillRect(Integer.parseInt(VariableGlobales.configuracionActa.get("codigoBarraCoordenaXo")), Integer.parseInt(VariableGlobales.configuracionActa.get("codigoBarraCoordenaYo")), Integer.parseInt(VariableGlobales.configuracionActa.get("codigoBarraCoordenaAncho")), Integer.parseInt(VariableGlobales.configuracionActa.get("codigoBarraCoordenaAlto")));
        }
        if (VariableGlobales.configuracionActa.get("horaInicioXo") != null) {
            gc.strokeRect(Integer.parseInt(VariableGlobales.configuracionActa.get("horaInicioXo")), Integer.parseInt(VariableGlobales.configuracionActa.get("horaInicioYo")), Integer.parseInt(VariableGlobales.configuracionActa.get("horaInicioAncho")), Integer.parseInt(VariableGlobales.configuracionActa.get("horaInicioAlto")));
            gc.fillRect(Integer.parseInt(VariableGlobales.configuracionActa.get("horaInicioXo")), Integer.parseInt(VariableGlobales.configuracionActa.get("horaInicioYo")), Integer.parseInt(VariableGlobales.configuracionActa.get("horaInicioAncho")), Integer.parseInt(VariableGlobales.configuracionActa.get("horaInicioAlto")));
        }
        if (VariableGlobales.configuracionActa.get("horaFinXo") != null) {
            gc.strokeRect(Integer.parseInt(VariableGlobales.configuracionActa.get("horaFinXo")), Integer.parseInt(VariableGlobales.configuracionActa.get("horaFinYo")), Integer.parseInt(VariableGlobales.configuracionActa.get("horaFinAncho")), Integer.parseInt(VariableGlobales.configuracionActa.get("horaFinAlto")));
            gc.fillRect(Integer.parseInt(VariableGlobales.configuracionActa.get("horaFinXo")), Integer.parseInt(VariableGlobales.configuracionActa.get("horaFinYo")), Integer.parseInt(VariableGlobales.configuracionActa.get("horaFinAncho")), Integer.parseInt(VariableGlobales.configuracionActa.get("horaFinAlto")));
        }
        if (VariableGlobales.configuracionActa.get("regionOrganizacionesXo") != null) {
            gc.strokeRect(Integer.parseInt(VariableGlobales.configuracionActa.get("regionOrganizacionesXo")), Integer.parseInt(VariableGlobales.configuracionActa.get("regionOrganizacionesYo")), Integer.parseInt(VariableGlobales.configuracionActa.get("regionOrganizacionesAncho")), Integer.parseInt(VariableGlobales.configuracionActa.get("regionOrganizacionesAlto")));
            gc.fillRect(Integer.parseInt(VariableGlobales.configuracionActa.get("regionOrganizacionesXo")), Integer.parseInt(VariableGlobales.configuracionActa.get("regionOrganizacionesYo")), Integer.parseInt(VariableGlobales.configuracionActa.get("regionOrganizacionesAncho")), Integer.parseInt(VariableGlobales.configuracionActa.get("regionOrganizacionesAlto")));
        }
        if (VariableGlobales.configuracionActa.get("regionObservacionesXo") != null) {
            gc.strokeRect(Integer.parseInt(VariableGlobales.configuracionActa.get("regionObservacionesXo")), Integer.parseInt(VariableGlobales.configuracionActa.get("regionObservacionesYo")), Integer.parseInt(VariableGlobales.configuracionActa.get("regionObservacionesAncho")), Integer.parseInt(VariableGlobales.configuracionActa.get("regionObservacionesAlto")));
            gc.fillRect(Integer.parseInt(VariableGlobales.configuracionActa.get("regionObservacionesXo")), Integer.parseInt(VariableGlobales.configuracionActa.get("regionObservacionesYo")), Integer.parseInt(VariableGlobales.configuracionActa.get("regionObservacionesAncho")), Integer.parseInt(VariableGlobales.configuracionActa.get("regionObservacionesAlto")));
        }
        if (VariableGlobales.configuracionActa.get("Firma1Xo") != null) {
            gc.strokeRect(Integer.parseInt(VariableGlobales.configuracionActa.get("Firma1Xo")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma1Yo")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma1Ancho")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma1Alto")));
            gc.fillRect(Integer.parseInt(VariableGlobales.configuracionActa.get("Firma1Xo")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma1Yo")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma1Ancho")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma1Alto")));
        }
        if (VariableGlobales.configuracionActa.get("Firma2Xo") != null) {
            gc.strokeRect(Integer.parseInt(VariableGlobales.configuracionActa.get("Firma2Xo")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma2Yo")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma2Ancho")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma2Alto")));
            gc.fillRect(Integer.parseInt(VariableGlobales.configuracionActa.get("Firma2Xo")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma2Yo")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma2Ancho")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma2Alto")));
        }
        if (VariableGlobales.configuracionActa.get("Firma3Xo") != null) {
            gc.strokeRect(Integer.parseInt(VariableGlobales.configuracionActa.get("Firma3Xo")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma3Yo")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma3Ancho")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma3Alto")));
            gc.fillRect(Integer.parseInt(VariableGlobales.configuracionActa.get("Firma3Xo")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma3Yo")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma3Ancho")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma3Alto")));
        }

//        gc.strokeRect(Integer.parseInt(VariableGlobales.configuracionActa.get("codigoBarraCoordenaXo")), Integer.parseInt(VariableGlobales.configuracionActa.get("codigoBarraCoordenaYo")), Integer.parseInt(VariableGlobales.configuracionActa.get("codigoBarraCoordenaAncho")), Integer.parseInt(VariableGlobales.configuracionActa.get("codigoBarraCoordenaAlto")));
//        gc.strokeRect(Integer.parseInt(VariableGlobales.configuracionActa.get("horaInicioXo")), Integer.parseInt(VariableGlobales.configuracionActa.get("horaInicioYo")), Integer.parseInt(VariableGlobales.configuracionActa.get("horaInicioAncho")), Integer.parseInt(VariableGlobales.configuracionActa.get("horaInicioAlto")));
//        gc.strokeRect(Integer.parseInt(VariableGlobales.configuracionActa.get("horaFinXo")), Integer.parseInt(VariableGlobales.configuracionActa.get("horaFinYo")), Integer.parseInt(VariableGlobales.configuracionActa.get("horaFinAncho")), Integer.parseInt(VariableGlobales.configuracionActa.get("horaFinAlto")));
//        gc.strokeRect(Integer.parseInt(VariableGlobales.configuracionActa.get("regionOrganizacionesXo")), Integer.parseInt(VariableGlobales.configuracionActa.get("regionOrganizacionesYo")), Integer.parseInt(VariableGlobales.configuracionActa.get("regionOrganizacionesAncho")), Integer.parseInt(VariableGlobales.configuracionActa.get("regionOrganizacionesAlto")));
//        gc.strokeRect(Integer.parseInt(VariableGlobales.configuracionActa.get("regionObservacionesXo")), Integer.parseInt(VariableGlobales.configuracionActa.get("regionObservacionesYo")), Integer.parseInt(VariableGlobales.configuracionActa.get("regionObservacionesAncho")), Integer.parseInt(VariableGlobales.configuracionActa.get("regionObservacionesAlto")));
//        gc.strokeRect(Integer.parseInt(VariableGlobales.configuracionActa.get("Firma1Xo")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma1Yo")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma1Ancho")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma1Alto")));
//        gc.strokeRect(Integer.parseInt(VariableGlobales.configuracionActa.get("Firma2Xo")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma2Yo")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma2Ancho")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma2Alto")));
//        gc.strokeRect(Integer.parseInt(VariableGlobales.configuracionActa.get("Firma3Xo")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma3Yo")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma3Ancho")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma3Alto")));
        //rellenando los rectangulos con transparente
//        gc.fillRect(Integer.parseInt(VariableGlobales.configuracionActa.get("codigoBarraCoordenaXo")), Integer.parseInt(VariableGlobales.configuracionActa.get("codigoBarraCoordenaYo")), Integer.parseInt(VariableGlobales.configuracionActa.get("codigoBarraCoordenaAncho")), Integer.parseInt(VariableGlobales.configuracionActa.get("codigoBarraCoordenaAlto")));
//        gc.fillRect(Integer.parseInt(VariableGlobales.configuracionActa.get("horaInicioXo")), Integer.parseInt(VariableGlobales.configuracionActa.get("horaInicioYo")), Integer.parseInt(VariableGlobales.configuracionActa.get("horaInicioAncho")), Integer.parseInt(VariableGlobales.configuracionActa.get("horaInicioAlto")));
//        gc.fillRect(Integer.parseInt(VariableGlobales.configuracionActa.get("horaFinXo")), Integer.parseInt(VariableGlobales.configuracionActa.get("horaFinYo")), Integer.parseInt(VariableGlobales.configuracionActa.get("horaFinAncho")), Integer.parseInt(VariableGlobales.configuracionActa.get("horaFinAlto")));
//        gc.fillRect(Integer.parseInt(VariableGlobales.configuracionActa.get("regionOrganizacionesXo")), Integer.parseInt(VariableGlobales.configuracionActa.get("regionOrganizacionesYo")), Integer.parseInt(VariableGlobales.configuracionActa.get("regionOrganizacionesAncho")), Integer.parseInt(VariableGlobales.configuracionActa.get("regionOrganizacionesAlto")));
//        gc.fillRect(Integer.parseInt(VariableGlobales.configuracionActa.get("regionObservacionesXo")), Integer.parseInt(VariableGlobales.configuracionActa.get("regionObservacionesYo")), Integer.parseInt(VariableGlobales.configuracionActa.get("regionObservacionesAncho")), Integer.parseInt(VariableGlobales.configuracionActa.get("regionObservacionesAlto")));
//        gc.fillRect(Integer.parseInt(VariableGlobales.configuracionActa.get("Firma1Xo")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma1Yo")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma1Ancho")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma1Alto")));
//        gc.fillRect(Integer.parseInt(VariableGlobales.configuracionActa.get("Firma2Xo")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma2Yo")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma2Ancho")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma2Alto")));
//        gc.fillRect(Integer.parseInt(VariableGlobales.configuracionActa.get("Firma3Xo")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma3Yo")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma3Ancho")), Integer.parseInt(VariableGlobales.configuracionActa.get("Firma3Alto")));
        // carganado texto por regiones
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 80));
        if (VariableGlobales.configuracionActa.get("codigoBarraCoordenaXo") != null) gc.fillText("Codigo de Barras", Integer.parseInt(VariableGlobales.configuracionActa.get("codigoBarraCoordenaXo")) + 100, Integer.parseInt(VariableGlobales.configuracionActa.get("codigoBarraCoordenaYo")) + 130);
        if (VariableGlobales.configuracionActa.get("horaInicioXo") != null) gc.fillText("Hora Inicio", Integer.parseInt(VariableGlobales.configuracionActa.get("horaInicioXo")) + 60, Integer.parseInt(VariableGlobales.configuracionActa.get("horaInicioYo")) + 100);
        if (VariableGlobales.configuracionActa.get("horaFinXo") != null) gc.fillText("Hora Fin", Integer.parseInt(VariableGlobales.configuracionActa.get("horaFinXo")) + 60, Integer.parseInt(VariableGlobales.configuracionActa.get("horaFinYo")) + 100);
        if (VariableGlobales.configuracionActa.get("regionOrganizacionesXo") != null) gc.fillText("Region Organizaciones", Integer.parseInt(VariableGlobales.configuracionActa.get("regionOrganizacionesXo")) + 700, Integer.parseInt(VariableGlobales.configuracionActa.get("regionOrganizacionesYo")) + 100);
        if (VariableGlobales.configuracionActa.get("regionObservacionesXo") != null) gc.fillText("Region Observaciones", Integer.parseInt(VariableGlobales.configuracionActa.get("regionObservacionesXo")) + 700, Integer.parseInt(VariableGlobales.configuracionActa.get("regionObservacionesYo")) + 100);
        if (VariableGlobales.configuracionActa.get("Firma1Xo") != null) gc.fillText("Firma 1", Integer.parseInt(VariableGlobales.configuracionActa.get("Firma1Xo")) + 150, Integer.parseInt(VariableGlobales.configuracionActa.get("Firma1Yo")) + 130);
        if (VariableGlobales.configuracionActa.get("Firma2Xo") != null) gc.fillText("Firma 2", Integer.parseInt(VariableGlobales.configuracionActa.get("Firma2Xo")) + 150, Integer.parseInt(VariableGlobales.configuracionActa.get("Firma2Yo")) + 130);
        if (VariableGlobales.configuracionActa.get("Firma3Xo") != null) gc.fillText("Firma 3", Integer.parseInt(VariableGlobales.configuracionActa.get("Firma3Xo")) + 150, Integer.parseInt(VariableGlobales.configuracionActa.get("Firma3Yo")) + 130);
////        
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
