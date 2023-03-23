/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.loging.endpoint.dashboard;

import com.mycompany.loging.App;
import com.mycompany.loging.score.negocio.NegocioServiceImpl;
import com.mycompany.loging.score.negocio.service.NegocioService;
import com.mycompany.loging.score.util.constanst.VariableGlobales;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author RDeLaCruz
 */
public class ConfiguraActaController implements Initializable {

    private File fileSeleccionado;
    private final NegocioService negocioService;

    public ConfiguraActaController() {
        this.negocioService = new NegocioServiceImpl();
    }

    @FXML
    private Button btnRegresar, btnBoton1, btnBoton2, btnBoton3, btnBoton4, btnBoton8, btnBoton7, btnBoton6, btnBoton5, btnCargar, btnProcesar, btnAdd1, btnAdd2, btnAdd3, btnAdd4, btnAdd5, btnAdd6, btnAdd7, btnAdd8, btnDelete1, btnDelete2, btnDelete3, btnDelete4, btnDelete5, btnDelete6, btnDelete7, btnDelete8;

    @FXML
    Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9;
    @FXML
    private Label lbArchivosEncontrados;

    @FXML
    ScrollPane scrollPaneActa;

    @FXML
    ImageView imgViewActa;
    private double imgX = 0.0, imgY = 0.0, imgX2 = 0.0, imgY2 = 0.0, imgAncho, imgAlto;
    boolean bandImgLimpia;

    /**
     * Initializes the controller class.
     */
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

    private void updateLabel(Button button, Button addButton, Button deleteButton) {

        button.setDisable(true);
        addButton.setDisable(true);
        deleteButton.setDisable(true);
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

    private void actionDelete(Label label1, Button btnButon1, Button addButton) {
        label1.setText("");
        btnButon1.setDisable(false);
        addButton.setDisable(true);
        activarEentoImgView(true, true);

    }

    @FXML
    private void regresaMenu() throws IOException {
        App.setRoot(null, "iniciarConfiguracion");

    }

    @FXML
    private void ActionBoton1(ActionEvent event) {

        updateLabel(btnBoton1, btnAdd1, btnDelete1);
        seterarEventosImageview("codigoBarraCoordena", lbl1, imgViewActa, scrollPaneActa, btnAdd1, btnDelete1, lbl2);
        activarEentoImgView(false, false);

    }

    @FXML
    private void ActionBoton2(ActionEvent event) {

        updateLabel(btnBoton2, btnAdd2, btnDelete2);
        seterarEventosImageview("horaInicio", lbl2, imgViewActa, scrollPaneActa, btnAdd2, btnDelete2, lbl3);
        activarEentoImgView(false, false);
    }

    @FXML
    private void ActionBoton3(ActionEvent event) {

        updateLabel(btnBoton3, btnAdd3, btnDelete3);
        seterarEventosImageview("horaFin", lbl3, imgViewActa, scrollPaneActa, btnAdd3, btnDelete3, lbl4);
        activarEentoImgView(false, false);

    }

    @FXML
    private void ActionBoton4(ActionEvent event) {

        updateLabel(btnBoton4, btnAdd4, btnDelete4);
        seterarEventosImageview("regionOrganizaciones", lbl4, imgViewActa, scrollPaneActa, btnAdd4, btnDelete4, lbl5);
        activarEentoImgView(false, false);
    }

    @FXML
    private void ActionBoton5(ActionEvent event) {

        updateLabel(btnBoton5, btnAdd5, btnDelete5);
        seterarEventosImageview("regionObservaciones", lbl5, imgViewActa, scrollPaneActa, btnAdd5, btnDelete5, lbl6);
        activarEentoImgView(false, false);

    }

    @FXML
    private void ActionBoton6(ActionEvent event) {

        updateLabel(btnBoton6, btnAdd6, btnDelete6);
        seterarEventosImageview("Firma1", lbl6, imgViewActa, scrollPaneActa, btnAdd6, btnDelete6, lbl7);
        activarEentoImgView(false, false);

    }

    @FXML
    private void ActionBoton7(ActionEvent event) {

        updateLabel(btnBoton7, btnAdd7, btnDelete7);
        seterarEventosImageview("Firma2", lbl7, imgViewActa, scrollPaneActa, btnAdd7, btnDelete7, lbl8);
        activarEentoImgView(false, false);

    }

    @FXML
    private void ActionBoton8(ActionEvent event) {

        updateLabel(btnBoton8, btnAdd8, btnDelete8);
        seterarEventosImageview("Firma3", lbl8, imgViewActa, scrollPaneActa, btnAdd8, btnDelete8, lbl9);
        activarEentoImgView(false, false);
    }

    @FXML
    private void elegirFichero() throws IOException {

        FileChooser fileChoiser = new FileChooser();
        fileChoiser.setTitle("Elegir Actas");
        fileSeleccionado = fileChoiser.showOpenDialog(null);
        lbArchivosEncontrados.setText(negocioService.uploadFileOnMemory(fileSeleccionado));

        Image img = new Image("file:" + VariableGlobales.lecturaActasEnMemoria.get("fileNamePathOriginal"));// nota poner el file para poner la imagen
        //Image img = new Image(fileSeleccionado.toURI().toString());
        imgViewActa.setImage(img);
        scrollPaneActa.setContent(imgViewActa);

        encuadrarActa();

        btnBoton1.setDisable(false);
        btnCargar.setDisable(true);
        btnAdd1.setVisible(true);
        btnAdd1.setDisable(true);
        btnDelete1.setVisible(true);
        btnDelete1.setDisable(true);

    }

    @FXML
    private void actionAdd1(ActionEvent event) {

        //evento para dehabilitar la seleccion de puntos
        //desactivar eventos
        activarEentoImgView(true, true);
        actionAddM(lbl2, btnBoton1, btnAdd1, btnBoton2, btnAdd2, btnAdd2, btnDelete2);
        btnBoton2.setDisable(false);

    }

    @FXML
    private void actionAdd2(ActionEvent event) {

        actionAddM(lbl3, btnBoton2, btnAdd2, btnBoton3, btnAdd3, btnAdd3, btnDelete3);
        activarEentoImgView(true, true);
        btnBoton3.setDisable(false);

    }

    @FXML
    private void actionAdd3(ActionEvent event) {
        actionAddM(lbl4, btnBoton3, btnAdd3, btnBoton4, btnAdd4, btnAdd4, btnDelete4);
        activarEentoImgView(true, true);
        btnBoton4.setDisable(false);

    }

    @FXML
    private void actionAdd4(ActionEvent event) {

        activarEentoImgView(true, true);
        actionAddM(lbl5, btnBoton4, btnAdd4, btnBoton5, btnAdd5, btnAdd5, btnDelete5);
        btnBoton5.setDisable(false);

    }

    @FXML
    private void actionAdd5(ActionEvent event) {
        actionAddM(lbl6, btnBoton5, btnAdd5, btnBoton6, btnAdd6, btnAdd6, btnDelete6);

        activarEentoImgView(true, true);
        btnBoton6.setDisable(false);

    }

    @FXML
    private void actionAdd6(ActionEvent event) {
        actionAddM(lbl7, btnBoton6, btnAdd6, btnBoton7, btnAdd7, btnAdd7, btnDelete7);

        activarEentoImgView(true, true);
        btnBoton7.setDisable(false);

    }

    @FXML
    private void actionAdd7(ActionEvent event) {

        actionAddM(lbl8, btnBoton7, btnAdd7, btnBoton8, btnAdd8, btnAdd8, btnDelete8);
        activarEentoImgView(true, true);
        btnBoton8.setDisable(false);

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
    private void funcionProcesar() throws IOException {
        //
        VariableGlobales.lecturaActasEnMemoria.put("lecturaPrimera", "SI");
        App.setRoot(null, "leerActas");
    }

    private void seterarEventosImageview(String valorConfig, Label label, ImageView imgV, ScrollPane spA, Button btnAdd, Button btnDelete, Label label2) {
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

                    VariableGlobales.configuracionActa.put(valorConfig + "Xo", String.valueOf(Math.round(event.getX())));
                    VariableGlobales.configuracionActa.put(valorConfig + "Yo", String.valueOf(Math.round(event.getY())));
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

            label.setText(VariableGlobales.lecturaActasEnMemoria.get(valorConfig + "Xo"));
            //dibujando el rectangulo sobre la imagen

            //seteando imagen limpia para ser graficada
            Image img = new Image("file:" + VariableGlobales.lecturaActasEnMemoria.get("fileNamePathOriginal"));// nota poner el file para poner la imagen
            imgV.setImage(img);

            gc.drawImage(imgV.getImage(), 0, 0);
            gc.setFill(Color.color(0, 0, 0, 0.5));
            //gc.setStroke(Color.RED);
            //gc.setLineWidth(10);
            gc.strokeRect(imgX, imgY, imgAncho, imgAlto);
            gc.fillRect(imgX, imgY, imgAncho, imgAlto);
            //Recreando la imagen
            WritableImage ImW = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
            PixelWriter gcIw = ImW.getPixelWriter();
            canvas.snapshot(null, ImW);
            imgV.setImage(ImW);
            spA.setContent(imgV);
            bandImgLimpia = true;

        });

        imgV.setOnMouseReleased(event -> {
            //seteando imagen limpia para ser graficada
            Image img = new Image("file:" + VariableGlobales.lecturaActasEnMemoria.get("fileNamePathOriginal"));// nota poner el file para poner la imagen
            imgV.setImage(img);
            //
            imgX2 = event.getX();
            imgY2 = event.getY();
            VariableGlobales.configuracionActa.put(valorConfig + "Xf", String.valueOf(Math.round(event.getX())));
            VariableGlobales.configuracionActa.put(valorConfig + "Yf", String.valueOf(Math.round(event.getY())));
            imgAncho = imgX2 - imgX;
            imgAlto = imgY2 - imgY;

            VariableGlobales.configuracionActa.put(valorConfig + "Ancho", String.valueOf(Math.round(imgAncho)));
            VariableGlobales.configuracionActa.put(valorConfig + "Alto", String.valueOf(Math.round(imgAlto)));

            label.setText(VariableGlobales.configuracionActa.get(valorConfig + "Xo") + ","
                    + VariableGlobales.configuracionActa.get(valorConfig + "Yo") + ","
                    + VariableGlobales.configuracionActa.get(valorConfig + "Ancho") + ","
                    + VariableGlobales.configuracionActa.get(valorConfig + "Alto"));

            //dibujando el rectangulo sobre la imagen
            Canvas canvas = new Canvas(imgV.getImage().getWidth(), imgV.getImage().getHeight());// capura el alto y ancho de la acta scaneada
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.drawImage(imgV.getImage(), 0, 0);
            gc.setStroke(Color.RED);
            gc.setLineWidth(10);
            gc.strokeRect(imgX, imgY, imgAncho, imgAlto);
            //Recreando la imagen
            WritableImage ImW = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
            PixelWriter gcIw = ImW.getPixelWriter();
            canvas.snapshot(null, ImW);
            imgV.setImage(ImW);
            spA.setContent(imgV);

            if (label2.getText() != "") {
                btnAdd.setDisable(true);
            } else {
                btnAdd.setDisable(false);
            }
            btnDelete.setDisable(false);

            //bandera cambia de estado
            bandImgLimpia = true;
//           
//            scrollPaneActa.setContent();
//            System.out.println("fin x2:" + imgX2 + "||" + "fin y2:" + imgY2);
        });
    }

    private void imgLimpia() {
        Image img = new Image("file:" + VariableGlobales.lecturaActasEnMemoria.get("fileNamePathOriginal"));// nota poner el file para poner la imagen
        //Image img = new Image(fileSeleccionado.toURI().toString());
        imgViewActa.setImage(img);
        scrollPaneActa.setContent(imgViewActa);
    }

    private void activarEentoImgView(boolean ingview, boolean limpiarImg) {
        imgViewActa.setDisable(ingview);
        if (limpiarImg) {
            imgLimpia();
        }
    }

    private void encuadrarActa() {
        //algoritmo para reducir la imagen sin perderlas coordenadas
        int iteraciones = (int) Math.ceil(imgViewActa.getImage().getHeight() / scrollPaneActa.getHeight());
        System.out.println("iteraciones a reducir" + iteraciones);

        double scale = imgViewActa.getScaleX();
        while (iteraciones > 0) {
            imgViewActa.setScaleX(scale / 5);
            imgViewActa.setScaleY(scale / 5);
            iteraciones--;
        }

        scrollPaneActa.setVvalue(0.5);
        scrollPaneActa.setHvalue(0.5);
//        System.out.println("vista scr x:"+scrollPaneActa.getViewportBounds().getWidth());
//        System.out.println("Tamaño del scr x:"+scrollPaneActa.getContent().getBoundsInLocal().getHeight());
//        System.out.println("Tamaño de scroll:"+scrollPaneActa.getHvalue());

        //finalgoritmo
    }

    @FXML
    private void ActionDeleteEvent8(ActionEvent event) {
        actionDelete(lbl8, btnBoton8, btnAdd8);

    }

    @FXML
    private void ActionDeleteEvent7(ActionEvent event) {
        actionDelete(lbl7, btnBoton7, btnAdd7);

    }

    @FXML
    private void ActionDeleteEvent6(ActionEvent event) {
        actionDelete(lbl6, btnBoton6, btnAdd6);

    }

    @FXML
    private void ActionDeleteEvent5(ActionEvent event) {

        actionDelete(lbl5, btnBoton5, btnAdd5);

//        lbl5.setVisible(false);
//        btnBoton5.setVisible(false);
//        btnAdd5.setVisible(false);
//        btnDelete5.setVisible(false);
//        btnBoton4.setDisable(false);
//        activarEentoImgView(false, false);
//        lbl3.setText(".....");
//        lbl4.setText(".....");
    }

    @FXML
    private void ActionDeleteEvent3(ActionEvent event) {

        actionDelete(lbl3, btnBoton3, btnAdd3);

//        lbl3.setVisible(false);
//        btnBoton3.setVisible(false);
//        btnAdd3.setVisible(false);
//        btnDelete3.setVisible(false);
//        btnBoton2.setDisable(false);
//        activarEentoImgView(false, false);
//        lbl2.setText(".....");
//        lbl3.setText(".....");
    }

    @FXML
    private void ActionDeleteEvent4(ActionEvent event) {

        actionDelete(lbl4, btnBoton4, btnAdd4);

//        lbl4.setVisible(false);
//        btnBoton4.setVisible(false);
//        btnAdd4.setVisible(false);
//        btnDelete4.setVisible(false);
//        btnBoton3.setDisable(false);
//        activarEentoImgView(false, false);
//        lbl3.setText(".....");
//        lbl4.setText(".....");
    }

    @FXML
    private void ActionDeleteEvent2(ActionEvent event) {
        actionDelete(lbl2, btnBoton2, btnAdd2);

//        lbl2.setVisible(false);
//        btnBoton2.setVisible(false);
//        btnAdd2.setVisible(false);
//        btnDelete2.setVisible(false);
//        btnBoton1.setDisable(false);
//        activarEentoImgView(false, false);
//        lbl2.setText(".....");
//        lbl1.setText(".....");
    }

    @FXML
    private void ActionDeleteEvent1(ActionEvent event) {

        actionDelete(lbl1, btnBoton1, btnAdd1);

//        lbl1.setText(".....");
//        btnBoton1.setDisable(false);
//        btnAdd1.setDisable(true);
//        activarEentoImgView(true, true);
    }

}
