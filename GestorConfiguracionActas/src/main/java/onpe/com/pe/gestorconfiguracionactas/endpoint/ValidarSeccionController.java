/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package onpe.com.pe.gestorconfiguracionactas.endpoint;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import onpe.com.pe.gestorconfiguracionactas.App;
import onpe.com.pe.gestorconfiguracionactas.core.business.BusinessService;
import onpe.com.pe.gestorconfiguracionactas.core.business.Impl.BusinessServiceImpl;
import onpe.com.pe.gestorconfiguracionactas.core.model.Setting;
import onpe.com.pe.gestorconfiguracionactas.core.util.VariableGlobales;

/**
 * FXML Controller class
 *
 * @author rdela
 */
public class ValidarSeccionController implements Initializable {

    private final BusinessService businessService;
    @FXML
    private ScrollPane scrollPaneActa;
    @FXML
    private ImageView imgViewActa;
    @FXML
    private Button btnRegresar;
    @FXML
    private Label lbActaValidar;

    private Image img;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnContinuar;
    @FXML
    private Label numVotoPreferencial;
    @FXML
    private Label etiquetaVotoRev;

    private int numEs = 0;
    private double escalaLo;
    /**
     * Initializes the controller class.
     */
    public ValidarSeccionController() {
        this.businessService = new BusinessServiceImpl();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        lbActaValidar.setText(VariableGlobales.identificaActa.get("nombreActaSeleccion"));
        System.out.println("DATO:" + VariableGlobales.identificaActa.get("nombreSeleccion"));

        //algoritmo para pintar las regiones del acta recuperar el array de setting e iterarlo y por cada iteracion pintar la region indicada
        img = new Image("file:" + VariableGlobales.lecturaActasEnMemoria.get("fileNamePathOriginal"));// nota poner el file para poner la imagen
        //System.out.println("valor de la variable global:" + VariableGlobales.lecturaActasEnMemoria.get("fileNamePathOriginal"));

        imgViewActa.setImage(img);
        // pintando regiones
        try {
            int i = 0;
            Canvas canvas = new Canvas(imgViewActa.getImage().getWidth(), imgViewActa.getImage().getHeight());// capura el alto y ancho de la acta scaneada
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.drawImage(imgViewActa.getImage(), 0, 0);

            for (Setting item : businessService.findAllSettingOnlyEleccion()) {

                if (item.getId_setting().equals(VariableGlobales.identificaActa.get("idSectionActaSeleccion"))) {
                    Gson gson = new Gson();
                    String[] arreglo = gson.fromJson(item.getSetting(), String[].class);
                    for (var configuracion : arreglo) {
                        System.out.println("DATOS DE ARRAYS:::::" + configuracion);
                        gc.setStroke(Color.RED);
                        gc.setLineWidth(10);
                        //gc.strokeRect(imgX, imgY, imgAncho, imgAlto);
                        System.out.println("variables vacias o nel :" + VariableGlobales.coordenadasActa.get(configuracion + "Xo"));
                        gc.strokeRect(Double.parseDouble(VariableGlobales.coordenadasActa.get(configuracion + "Xo")), Double.parseDouble(VariableGlobales.coordenadasActa.get(configuracion + "Yo")), Double.parseDouble(VariableGlobales.coordenadasActa.get(configuracion + "Ancho")), Double.parseDouble(VariableGlobales.coordenadasActa.get(configuracion + "Alto")));
                    }
                }
            }
            WritableImage ImW = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
            PixelWriter gcIw = ImW.getPixelWriter();
            canvas.snapshot(null, ImW);
            imgViewActa.setImage(ImW);
            scrollPaneActa.setContent(imgViewActa);

        } catch (Exception e) {
            System.out.println("la exepcion:::::::::" + e);
        }
        if (numEs == 0) {
            escalaLo = imgViewActa.getScaleX();
            numEs = 1;
        }
        if ((int) imgViewActa.getImage().getHeight() > 4500) {
            encuadrarActa(3, escalaLo);
        } else if ((int) imgViewActa.getImage().getHeight() < 3600) {
            encuadrarActa(2, escalaLo);
            System.out.println("posisicon:" + imgViewActa.getImage().getHeight());
        }

    }

    @FXML
    private void actionFinaliza(ActionEvent event) throws IOException {

        Alert diagConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        diagConfirmacion.setTitle("CONFIRMAR");
        diagConfirmacion.setHeaderText("Desea Guardar los cambios");
        diagConfirmacion.setContentText("¿Estás seguro de continuar?");

        diagConfirmacion.showAndWait().ifPresent(respuesta -> {

            if (respuesta == ButtonType.OK) {
                try {
                    System.out.println("datos en Globales=" + VariableGlobales.coordenadasActa.toString());
                    System.out.println("datos en Globales=" + VariableGlobales.identificaActa.get("idSectionActaSeleccion"));
                    businessService.uploadSections(VariableGlobales.identificaActa.get("idSectionActaSeleccion"), VariableGlobales.coordenadasActa.toString());// actualiza en base de datos ESTO VA A CONFIRMAR
                    App.setRoot(null, "inicioMenu");
                } catch (Exception e) {
                    System.out.println("datos:" + e);
                }
                
            }
        });

    }

    @FXML
    private void actionRegresar() throws IOException {

        App.setRoot(null, "configuraSecciones");
    }

    //DATOS DE ACTA
    private void encuadrarActa(int tipoHoja, double escala) {
        //algoritmo para reducir la imagen sin perderlas coordenadas
        int iteraciones = (int) Math.ceil(imgViewActa.getImage().getHeight() / scrollPaneActa.getHeight());
        System.out.println("iteraciones a reducir" + iteraciones);

        if (tipoHoja == 3) {

            imgViewActa.setScaleX(escala / 2.5);
            imgViewActa.setScaleY(escala / 2.5);
        } else if (tipoHoja == 2) {

            imgViewActa.setScaleX(escala / 2);
            imgViewActa.setScaleY(escala / 2);

        }

        scrollPaneActa.setVvalue(0.5);
        scrollPaneActa.setHvalue(0.5);
    }

}
