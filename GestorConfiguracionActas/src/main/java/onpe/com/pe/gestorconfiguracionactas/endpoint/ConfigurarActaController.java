/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package onpe.com.pe.gestorconfiguracionactas.endpoint;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import onpe.com.pe.gestorconfiguracionactas.App;
import onpe.com.pe.gestorconfiguracionactas.core.business.Impl.BusinessServiceImpl;
import onpe.com.pe.gestorconfiguracionactas.core.util.VariableGlobales;
import onpe.com.pe.gestorconfiguracionactas.core.business.BusinessService;
import onpe.com.pe.gestorconfiguracionactas.core.model.Setting;

/**
 * FXML Controller class
 *
 * @author NHuaman
 */
public class ConfigurarActaController implements Initializable {

    private Setting setting = new Setting();
    private double imgX = 0.0, imgY = 0.0, imgX2 = 0.0, imgY2 = 0.0, imgAncho, imgAlto;
    boolean bandImgLimpia;
    private File fileSeleccionado;
    private Image img;
    private final BusinessService businessService;

    public ConfigurarActaController() {
        this.businessService = new BusinessServiceImpl();
    }

    @FXML
    private Button btnRegresar, btnAddTitle, btnBoton1, btnBoton2, btnBoton3, btnBoton4, btnBoton8,
            btnBoton7, btnBoton6, btnBoton5, btnCargar, btnProcesar, btnAdd1, btnAdd2,
            btnAdd3, btnAdd4, btnAdd5, btnAdd6, btnAdd7, btnAdd8, btnDelete1, btnDelete2,
            btnDelete3, btnDelete4, btnDelete5, btnDelete6, btnDelete7, btnDelete8;

    @FXML
    Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9;
    @FXML
    private Label lbArchivosEncontrados;

    @FXML
    ScrollPane scrollPaneActa;

    @FXML
    ImageView imgViewActa, ico_check1, ico_canc1, ico_check2, ico_canc2, ico_canc3, ico_canc4, ico_canc5, ico_canc6,
            ico_canc7, ico_canc8, ico_check3, ico_check4, ico_check5, ico_check6, ico_check7, ico_check8;

    @FXML
    TextField textFieldEleccion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnBoton1.setDisable(true);
        btnAdd1.setVisible(false);
        btnBoton2.setVisible(false);
        btnAdd2.setVisible(false);
        btnBoton3.setVisible(false);
        btnAdd3.setVisible(false);
        btnBoton4.setVisible(false);
        btnAdd4.setVisible(false);
        btnBoton5.setVisible(false);
        btnAdd5.setVisible(false);
        btnBoton6.setVisible(false);
        btnAdd6.setVisible(false);
        btnBoton7.setVisible(false);
        btnAdd7.setVisible(false);
        btnBoton8.setVisible(false);
        btnAdd8.setVisible(false);
        btnProcesar.setDisable(true);

        btnDelete1.setVisible(false);
        btnDelete2.setVisible(false);
        btnDelete2.setDisable(true);
        btnDelete3.setVisible(false);
        btnDelete4.setVisible(false);
        btnDelete5.setVisible(false);
        btnDelete6.setVisible(false);
        btnDelete7.setVisible(false);
        btnDelete8.setVisible(false);

        lbl2.setVisible(false);
        lbl3.setVisible(false);
        lbl4.setVisible(false);
        lbl5.setVisible(false);
        lbl6.setVisible(false);
        lbl7.setVisible(false);
        lbl8.setVisible(false);

        imgViewActa.setOnScroll(event -> {// este evento ya esta cargado para la imagen
            if (event.isControlDown()) {
                double delta = event.getDeltaY();
                double scale = imgViewActa.getScaleX();
                if (delta > 0) {
                    imgViewActa.setScaleX(scale * 1.1);
                    imgViewActa.setScaleY(scale * 1.1);
                } else {
                    imgViewActa.setScaleX(scale / 1.1);
                    imgViewActa.setScaleY(scale / 1.1);
                }
                scrollPaneActa.setVvalue(0.5);
                scrollPaneActa.setHvalue(0.5);

            }

        });
        //BANDERA PARA Limpiar la imagen
        bandImgLimpia = false;
    }

    @FXML
    private void elegirFichero() throws IOException {

        FileChooser fileChoiser = new FileChooser();
        fileChoiser.setTitle("Elegir Actas");
        fileSeleccionado = fileChoiser.showOpenDialog(null);
        lbArchivosEncontrados.setText(businessService.uploadFileOnMemory(fileSeleccionado));

        img = new Image("file:" + VariableGlobales.lecturaActasEnMemoria.get("fileNamePathOriginal"));// nota poner el file para poner la imagen
        //Image img = new Image(fileSeleccionado.toURI().toString());
        imgViewActa.setImage(img);
        scrollPaneActa.setContent(imgViewActa);

        if ((int) imgViewActa.getImage().getHeight() > 4500) {
            encuadrarActa(3);
        } else if ((int) imgViewActa.getImage().getHeight() < 3600) {
            encuadrarActa(2);
        }
        btnBoton1.setDisable(false);
        btnCargar.setDisable(true);
        btnAdd1.setVisible(true);
        btnAdd1.setDisable(true);
        btnDelete1.setVisible(true);
        btnDelete1.setDisable(true);
        ico_canc1.setVisible(true);

    }

    @FXML
    private void actionAddTitle(ActionEvent event) throws Exception {

       // updateLabel(btnBoton1, btnAdd1, btnDelete1);
        //manda un string  para guardar las coordenads del codigo de barras el hashmap y poder recuperarlas
        seterarEventosImageview("title", imgViewActa, scrollPaneActa, btnAdd1, btnDelete1, ico_canc1, ico_check1);
        //Enviar un string mas para guardar los datos en variables globales
        activarEentoImgView(false, false);
        //enviar una variable para guardar los datos del QR
        textFieldEleccion.setText(businessService.readTitleActa(
                Integer.parseInt(VariableGlobales.configuracionActa.get("titleXo")),
                Integer.parseInt(VariableGlobales.configuracionActa.get("titleYo")),
                Integer.parseInt(VariableGlobales.configuracionActa.get("titleAncho")),
                Integer.parseInt(VariableGlobales.configuracionActa.get("titleAlto"))));
    }

    @FXML
    private void ActionBoton1(ActionEvent event) {

        updateLabel(btnBoton1, btnAdd1, btnDelete1);
        //manda un string  para guardar las coordenads del codigo de barras el hashmap y poder recuperarlas
        seterarEventosImageview("codigoBarraCoordena", imgViewActa, scrollPaneActa, btnAdd1, btnDelete1, ico_check1,ico_canc1);
        //Enviar un string mas para guardar los datos en variables globales
        activarEentoImgView(false, false);
        //enviar una variable para guardar los datos del QR

    }

    @FXML
    private void ActionBoton2(ActionEvent event) {

        updateLabel(btnBoton2, btnAdd2, btnDelete2);
        seterarEventosImageview("horaInicio", imgViewActa, scrollPaneActa, btnAdd2, btnDelete2,ico_check2, ico_canc2 );
        activarEentoImgView(false, false);

    }

    @FXML
    private void ActionBoton3(ActionEvent event) {

        updateLabel(btnBoton3, btnAdd3, btnDelete3);
        seterarEventosImageview("horaFin", imgViewActa, scrollPaneActa, btnAdd3, btnDelete3,ico_check3, ico_canc3);
        activarEentoImgView(false, false);
    }

    @FXML
    private void ActionBoton4(ActionEvent event) {

        updateLabel(btnBoton4, btnAdd4, btnDelete4);
        seterarEventosImageview("regionOrganizaciones", imgViewActa, scrollPaneActa, btnAdd4, btnDelete4,ico_check4, ico_canc4);
        activarEentoImgView(false, false);
    }

    @FXML
    private void ActionBoton5(ActionEvent event) {

        updateLabel(btnBoton5, btnAdd5, btnDelete5);
        seterarEventosImageview("regionObservaciones", imgViewActa, scrollPaneActa, btnAdd5, btnDelete5,ico_check5, ico_canc5);
        activarEentoImgView(false, false);
    }

    @FXML
    private void ActionBoton6(ActionEvent event) {

        updateLabel(btnBoton6, btnAdd6, btnDelete6);
        seterarEventosImageview("Firma1", imgViewActa, scrollPaneActa, btnAdd6, btnDelete6,ico_check6, ico_canc6);
        activarEentoImgView(false, false);
    }

    @FXML
    private void ActionBoton7(ActionEvent event) {

        updateLabel(btnBoton7, btnAdd7, btnDelete7);
        seterarEventosImageview("Firma2", imgViewActa, scrollPaneActa, btnAdd7, btnDelete7,ico_check7, ico_canc7);
        activarEentoImgView(false, false);
    }

    @FXML
    private void ActionBoton8(ActionEvent event) {

        updateLabel(btnBoton8, btnAdd8, btnDelete8);
        seterarEventosImageview("Firma3", imgViewActa, scrollPaneActa, btnAdd8, btnDelete8,ico_check8, ico_canc8);
        activarEentoImgView(false, false);
    }

    @FXML
    private void actionAdd1(ActionEvent event) {

        activarEentoImgView(true, true);
        actionAddM(lbl2, btnBoton1, btnAdd1, btnBoton2, btnAdd2, btnAdd2, btnDelete2);
        btnBoton2.setDisable(false);
        ico_canc2.setVisible(true);
    }

    @FXML
    private void actionAdd2(ActionEvent event) {

        actionAddM(lbl3, btnBoton2, btnAdd2, btnBoton3, btnAdd3, btnAdd3, btnDelete3);
        activarEentoImgView(true, true);
        btnBoton3.setDisable(false);
        ico_canc3.setVisible(true);

    }

    @FXML
    private void actionAdd3(ActionEvent event) {
        actionAddM(lbl4, btnBoton3, btnAdd3, btnBoton4, btnAdd4, btnAdd4, btnDelete4);
        activarEentoImgView(true, true);
        btnBoton4.setDisable(false);
        ico_canc4.setVisible(true);

    }

    @FXML
    private void actionAdd4(ActionEvent event) {

        activarEentoImgView(true, true);
        actionAddM(lbl5, btnBoton4, btnAdd4, btnBoton5, btnAdd5, btnAdd5, btnDelete5);
        btnBoton5.setDisable(false);
        ico_canc5.setVisible(true);

    }

    @FXML
    private void actionAdd5(ActionEvent event) {
        actionAddM(lbl6, btnBoton5, btnAdd5, btnBoton6, btnAdd6, btnAdd6, btnDelete6);

        activarEentoImgView(true, true);
        btnBoton6.setDisable(false);
        ico_canc6.setVisible(true);

    }

    @FXML
    private void actionAdd6(ActionEvent event) {
        actionAddM(lbl7, btnBoton6, btnAdd6, btnBoton7, btnAdd7, btnAdd7, btnDelete7);

        activarEentoImgView(true, true);
        btnBoton7.setDisable(false);
        ico_canc7.setVisible(true);

    }

    @FXML
    private void actionAdd7(ActionEvent event) {

        actionAddM(lbl8, btnBoton7, btnAdd7, btnBoton8, btnAdd8, btnAdd8, btnDelete8);
        activarEentoImgView(true, true);
        btnBoton8.setDisable(false);
        ico_canc8.setVisible(true);

    }

    @FXML
    private void actionAdd8(ActionEvent event) {

        activarEentoImgView(true, true);
        btnBoton8.setDisable(true);
        btnAdd8.setDisable(true);
        btnProcesar.setDisable(false);
        System.out.println("hice click");
    }

    @FXML
    private void ActionDeleteEvent8(ActionEvent event) {
        
        actionDelete("Firma3Xo", btnBoton8, btnAdd8, ico_check8, ico_canc8);
    }

    @FXML
    private void ActionDeleteEvent7(ActionEvent event) {
        actionDelete("Firma2Xo", btnBoton7, btnAdd7, ico_check7, ico_canc7);
    }

    @FXML
    private void ActionDeleteEvent6(ActionEvent event) {
        actionDelete("Firma1Xo", btnBoton6, btnAdd6, ico_check6, ico_canc6);
    }

    @FXML
    private void ActionDeleteEvent5(ActionEvent event) {
        actionDelete("regionObservacionesXo", btnBoton5, btnAdd5, ico_check5, ico_canc5);
    }

    @FXML
    private void ActionDeleteEvent4(ActionEvent event) {
        actionDelete("regionOrganizacionesXo", btnBoton4, btnAdd4, ico_check4, ico_canc4);
    }

    @FXML
    private void ActionDeleteEvent3(ActionEvent event) {
        actionDelete("horaFinXo", btnBoton3, btnAdd3, ico_check3, ico_canc3);
    }

    @FXML
    private void ActionDeleteEvent2(ActionEvent event) {
        actionDelete("horaInicioXo", btnBoton2, btnAdd2, ico_check2, ico_canc2);
    }

    @FXML
    private void ActionDeleteEvent1(ActionEvent event) {
        actionDelete("codigoBarraCoordenaXo",btnBoton1, btnAdd1, ico_check1, ico_canc1);
    }

    @FXML
    private void regresaMenu() throws IOException {
        App.setRoot(null, "inicioMenu");

    }

    @FXML
    private void funcionProcesar() throws IOException, Exception {

        //activarEentoImgView(false, false);
        setting.setId_setting(UUID.randomUUID().toString());
        setting.setName(textFieldEleccion.getText());
        setting.setStatusSetting("0");
        Gson gson = new Gson();
        setting.setSetting(gson.toJson(VariableGlobales.configuracionActa));
        businessService.saveSetting(setting);
        App.setRoot(null, "configurarActa");
    }

    @FXML // funcion luis
    private void verificarRegiones() throws IOException {
        App.setRoot(null, "confirmarRegionesActa");
    }

    private void updateLabel(Button button, Button addButton, Button deleteButton) {

        button.setDisable(true);
        addButton.setDisable(true);
        deleteButton.setDisable(true);
    }

    private void seterarEventosImageview(String valorConfig, ImageView imgV, ScrollPane spA, Button btnAdd, Button btnDelete, ImageView img1check, ImageView img2cancel) {
        //

        imgV.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.SECONDARY) {// esto permite el arrastre de la imagen
                    spA.setPannable(true);
                } else {
                    spA.setPannable(false);
                }

                if (event.getButton() == MouseButton.PRIMARY) {
                    imgX = event.getX();
                    imgY = event.getY();

                    if (bandImgLimpia) {
                        imgLimpia();
                    }

                    Canvas canvas = new Canvas(imgV.getImage().getWidth(), imgV.getImage().getHeight());// capura el alto y ancho de la acta scaneada
                    GraphicsContext gc = canvas.getGraphicsContext2D();
                    gc.drawImage(imgV.getImage(), 0, 0);

                    gc.setFill(Color.RED);
                    gc.setLineWidth(10);
                    gc.fillOval(imgX, imgY, 10, 10);
                    WritableImage ImW = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
                    PixelWriter gcIw = ImW.getPixelWriter();
                    canvas.snapshot(null, ImW);
                    imgV.setImage(ImW);
                    spA.setContent(imgV);
                    System.out.println("inicio x:" + imgX + "||" + "inicio y:" + imgY);
                }
            }
        });

        imgV.setOnMouseDragged(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                Canvas canvas = new Canvas(imgV.getImage().getWidth(), imgV.getImage().getHeight());// capura el alto y ancho de la acta scaneada
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                imgX2 = event.getX();
                imgY2 = event.getY();
                VariableGlobales.lecturaActasEnMemoria.put(valorConfig + "Xf", String.valueOf(event.getX()));
                VariableGlobales.lecturaActasEnMemoria.put(valorConfig + "Yf", String.valueOf(event.getY()));
                imgAncho = imgX2 - imgX;
                imgAlto = imgY2 - imgY;

                VariableGlobales.lecturaActasEnMemoria.put(valorConfig + "Ancho", String.valueOf(imgAncho));
                VariableGlobales.lecturaActasEnMemoria.put(valorConfig + "Alto", String.valueOf(imgAlto));

                imgV.setImage(img);

                double maxX = Math.max(imgX, imgX2);
                double maxY = Math.max(imgY, imgY2);
                double minX = Math.min(imgX, imgX2);
                double minY = Math.min(imgY, imgY2);
                //
                double imgAncho2 = maxX - minX;
                double imgAlto2 = maxY - minY;
                ////

                gc.drawImage(imgV.getImage(), 0, 0);
                gc.setFill(Color.color(0, 0, 0, 0.5));

                gc.strokeRect(minX, minY, imgAncho2, imgAlto2);
                gc.fillRect(minX, minY, imgAncho2, imgAlto2);
                //Recreando la imagen
                WritableImage ImW = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
                PixelWriter gcIw = ImW.getPixelWriter();
                canvas.snapshot(null, ImW);
                imgV.setImage(ImW);
                spA.setContent(imgV);
                bandImgLimpia = true;
            }

        });

        imgV.setOnMouseReleased(event -> {

            if (event.getButton() == MouseButton.PRIMARY) {

                imgV.setImage(img);
                //
                imgX2 = event.getX();
                imgY2 = event.getY();

                double maxX = Math.max(imgX, imgX2);
                double maxY = Math.max(imgY, imgY2);
                double minX = Math.min(imgX, imgX2);
                double minY = Math.min(imgY, imgY2);
                //
                double imgAncho2 = maxX - minX;
                double imgAlto2 = maxY - minY;
                ////

                VariableGlobales.configuracionActa.put(valorConfig + "Xo", String.valueOf(Math.round(minX)));
                VariableGlobales.configuracionActa.put(valorConfig + "Yo", String.valueOf(Math.round(minY)));

                VariableGlobales.configuracionActa.put(valorConfig + "Ancho", String.valueOf(Math.round(imgAncho2)));
                VariableGlobales.configuracionActa.put(valorConfig + "Alto", String.valueOf(Math.round(imgAlto2)));

                //dibujando el rectangulo sobre la imagen
                Canvas canvas = new Canvas(imgV.getImage().getWidth(), imgV.getImage().getHeight());// capura el alto y ancho de la acta scaneada
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.drawImage(imgV.getImage(), 0, 0);
                gc.setStroke(Color.RED);
                gc.setLineWidth(10);
                //gc.strokeRect(imgX, imgY, imgAncho, imgAlto);
                gc.strokeRect(minX, minY, imgAncho2, imgAlto2);
                //Recreando la imagen
                WritableImage ImW = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
                PixelWriter gcIw = ImW.getPixelWriter();
                canvas.snapshot(null, ImW);
                imgV.setImage(ImW);
                spA.setContent(imgV);

                if (VariableGlobales.configuracionActa.get(valorConfig + "Xo").equals("")) {
                 btnAdd.setDisable(true);
                img1check.setVisible(false);
                img2cancel.setVisible(true);
                    
                } else {
                btnAdd.setDisable(false);
                img1check.setVisible(true);
                img2cancel.setVisible(false);
                }
                btnDelete.setDisable(false);

                //bandera cambia de estado
                bandImgLimpia = true;


//           
//            scrollPaneActa.setContent();
//            System.out.println("fin x2:" + imgX2 + "||" + "fin y2:" + imgY2);
            }

        });
    }

    private void activarEentoImgView(boolean ingview, boolean limpiarImg) {
        imgViewActa.setDisable(ingview);
        if (limpiarImg) {
            imgLimpia();
        }
    }

    private void imgLimpia() {
        //Image img = new Image("file:" + VariableGlobales.lecturaActasEnMemoria.get("fileNamePathOriginal"));// nota poner el file para poner la imagen
        //Image img = new Image(fileSeleccionado.toURI().toString());
        imgViewActa.setImage(img);
        scrollPaneActa.setContent(imgViewActa);
    }

    private void actionDelete(String nombreButton, Button btnButon1, Button addButton, ImageView img1, ImageView img2) {
       VariableGlobales.configuracionActa.put(nombreButton,"");
        btnButon1.setDisable(false);
        addButton.setDisable(true);
        activarEentoImgView(true, true);
        img1.setVisible(false);
        img2.setVisible(true);
    }

    private void actionAddM(Label label, Button btnButon, Button addButton, Button btnButon2, Button addButton2, Button addButton21, Button deleteButton) {

        label.setVisible(true);
        btnButon.setDisable(true);
        addButton.setDisable(true);
        btnButon2.setVisible(true);
        addButton2.setVisible(true);
        addButton21.setDisable(true);
        deleteButton.setVisible(true);
    }

    private void encuadrarActa(int tipoHoja) {
        //algoritmo para reducir la imagen sin perderlas coordenadas
        int iteraciones = (int) Math.ceil(imgViewActa.getImage().getHeight() / scrollPaneActa.getHeight());
        System.out.println("iteraciones a reducir" + iteraciones);

        double scale = imgViewActa.getScaleX();

        if (tipoHoja == 3) {
            while (iteraciones > 0) {
                imgViewActa.setScaleX(scale / 8);
                imgViewActa.setScaleY(scale / 8);
                iteraciones--;
            }
        } else if (tipoHoja == 2) {
            while (iteraciones + 2 > 0) {
                imgViewActa.setScaleX(scale / 5);
                imgViewActa.setScaleY(scale / 5);
                iteraciones--;
            }
        }

        scrollPaneActa.setVvalue(0.5);
        scrollPaneActa.setHvalue(0.5);
    }
}
