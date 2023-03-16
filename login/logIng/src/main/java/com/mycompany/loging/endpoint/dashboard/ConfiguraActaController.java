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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
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
    private Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8;
    @FXML
    private Label lbArchivosEncontrados;

    @FXML
    ScrollPane scrollPaneActa;

    @FXML
    ImageView imgViewActa;
    private double imgX = 0.0, imgY = 0.0, imgX2 = 0.0, imgY2 = 0.0, imgAncho, imgAlto;

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

        imgViewActa.setOnScroll(event -> {
            double delta = event.getDeltaY();
            double scale = imgViewActa.getScaleX();
            if (delta > 0) {
                imgViewActa.setScaleX(scale * 1.1);
                imgViewActa.setScaleY(scale * 1.1);
            } else {
                imgViewActa.setScaleX(scale / 1.1);
                imgViewActa.setScaleY(scale / 1.1);
            }
        });
        imgViewActa.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {// esto permite el arrastre de la imagen
                scrollPaneActa.setPannable(true);
            } else {
                scrollPaneActa.setPannable(false);
            }

            if (event.getButton() == MouseButton.PRIMARY) {
                imgX = event.getX();
                imgY = event.getY();
                Circle circle = new Circle(imgX, imgY, 10, Color.RED);
                Pane overlayPane = new Pane(circle);
                overlayPane.setMouseTransparent(true);
                scrollPaneActa.setContent(new StackPane(imgViewActa, overlayPane));
                System.out.println("inicio x:" + imgX + "||" + "inicio y:" + imgY);
            }
        });
        imgViewActa.setOnMouseReleased(event -> {
            imgX2 = event.getX();
            imgY2 = event.getY();
            imgAncho = imgX2 - imgX;
            imgAlto = imgY2 - imgY;
            Circle circle = new Circle(imgX2, imgY2, 10, Color.RED);
            Rectangle rect = new Rectangle(imgAncho, imgAlto);
            rect.setX(imgX);
            rect.setY(imgY);
            rect.setStroke(Color.BLUE);
            rect.setFill(Color.TRANSPARENT);
            Pane overlayPane = new Pane(circle, rect);
            overlayPane.setMouseTransparent(true);
            scrollPaneActa.setContent(new StackPane(imgViewActa, overlayPane));
            System.out.println("fin x2:" + imgX2 + "||" + "fin y2:" + imgY2);
        });

    }

    private void updateLabel(Button button, Label label, Button addButton, Button deleteButton, String text) {
        label.setText(text);
        button.setDisable(true);
        addButton.setDisable(false);
        deleteButton.setDisable(false);
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

    @FXML
    private void regresaMenu() throws IOException {
        App.setRoot(null, "iniciarConfiguracion");

    }

    @FXML
    private void ActionBoton1(ActionEvent event) {

        updateLabel(btnBoton1, lbl1, btnAdd1, btnDelete1, "iX:x182.0 || iY: 50.0 \nfX:426.0 || fY:181.0");

//        btnBoton1.setDisable(true);
//        btnAdd1.setDisable(false);
//        btnDelete1.setDisable(false);
//
//        lbl1.setText("iX:x182.0 || iY: 50.0 \nfX:426.0 || fY:181.0");
//
//        System.out.println("click boton");
    }

    @FXML
    private void ActionBoton2(ActionEvent event) {

        updateLabel(btnBoton2, lbl2, btnAdd2, btnDelete2, "iX:x182.0 || iY: 50.0 \nfX:426.0 || fY:181.0");

//        btnBoton2.setDisable(true);
//        btnAdd2.setDisable(false);
//        btnDelete2.setDisable(false);
//
//        lbl2.setText("iX:x182.0 || iY: 50.0 \nfX:426.0 || fY:181.0");
//
//        System.out.println("click boton");
    }

    @FXML
    private void ActionBoton3(ActionEvent event) {

        updateLabel(btnBoton3, lbl3, btnAdd3, btnDelete3, "iX:x182.0 || iY: 50.0 \nfX:426.0 || fY:181.0");

//        btnBoton3.setDisable(true);
//        btnAdd3.setDisable(false);
//        btnDelete3.setDisable(false);
//
//        lbl3.setText("iX:x182.0 || iY: 50.0 \nfX:426.0 || fY:181.0");
//
//        System.out.println("click boton");
    }

    @FXML
    private void ActionBoton4(ActionEvent event) {

        updateLabel(btnBoton4, lbl4, btnAdd4, btnDelete4, "iX:x182.0 || iY: 50.0 \nfX:426.0 || fY:181.0");

//        btnBoton4.setDisable(true);
//        btnAdd4.setDisable(false);
//        btnDelete4.setDisable(false);
//
//        lbl4.setText("iX:x182.0 || iY: 50.0 \nfX:426.0 || fY:181.0");
//
//        System.out.println("click boton");
    }

    @FXML
    private void ActionBoton5(ActionEvent event) {

        updateLabel(btnBoton5, lbl5, btnAdd5, btnDelete5, "iX:x182.0 || iY: 50.0 \nfX:426.0 || fY:181.0");

//        btnBoton5.setDisable(true);
//        btnAdd5.setDisable(false);
//        btnDelete5.setDisable(false);
//
//        lbl5.setText("iX:x182.0 || iY: 50.0 \nfX:426.0 || fY:181.0");
//
//        System.out.println("click boton");
    }

    @FXML
    private void ActionBoton6(ActionEvent event) {

        updateLabel(btnBoton6, lbl6, btnAdd6, btnDelete6, "iX:x182.0 || iY: 50.0 \nfX:426.0 || fY:181.0");

//        btnBoton6.setDisable(true);
//        btnAdd6.setDisable(false);
//        btnDelete6.setDisable(false);
//
//        lbl6.setText("iX:x182.0 || iY: 50.0 \nfX:426.0 || fY:181.0");
//
//        System.out.println("click boton");
    }

    @FXML
    private void ActionBoton7(ActionEvent event) {

        updateLabel(btnBoton7, lbl7, btnAdd7, btnDelete7, "iX:x182.0 || iY: 50.0 \nfX:426.0 || fY:181.0");

//        btnBoton7.setDisable(true);
//        btnAdd7.setDisable(false);
//        btnDelete7.setDisable(false);
//
//        lbl7.setText("iX:x182.0 || iY: 50.0 \nfX:426.0 || fY:181.0");
//
//        System.out.println("click boton");
    }

    @FXML
    private void ActionBoton8(ActionEvent event) {

        updateLabel(btnBoton8, lbl8, btnAdd8, btnDelete8, "iX:x182.0 || iY: 50.0 \nfX:426.0 || fY:181.0");

//        btnBoton8.setDisable(true);
//        btnAdd8.setDisable(false);
//        btnDelete8.setDisable(false);
//
//        lbl8.setText("iX:x182.0 || iY: 50.0 \nfX:426.0 || fY:181.0");
//
//        System.out.println("click boton");
    }

    @FXML
    private void elegirFichero() throws IOException {

        FileChooser fileChoiser = new FileChooser();
        fileChoiser.setTitle("Elegir Actas");
        fileSeleccionado = fileChoiser.showOpenDialog(null);
        VariableGlobales.lecturaActasEnMemoria.put("lecturaPrimera", "SI");
        lbArchivosEncontrados.setText(negocioService.uploadFileOnMemory(fileSeleccionado));
        btnBoton1.setDisable(false);
        btnCargar.setDisable(true);
        btnAdd1.setVisible(true);
        btnAdd1.setDisable(true);
        btnDelete1.setVisible(true);
        btnDelete1.setDisable(true);

    }

    @FXML
    private void actionAdd1(ActionEvent event) {

        actionAddM(lbl2, btnBoton1, btnAdd1, btnBoton2, btnAdd2, btnAdd2, btnDelete2);

//        lbl2.setVisible(true);
//        btnBoton1.setDisable(true);
//        btnAdd1.setDisable(true);
//        btnBoton2.setVisible(true);
//        btnAdd2.setVisible(true);
//        btnAdd2.setDisable(true);
//        btnDelete2.setVisible(true);
//
//        System.out.println("hice click");
    }

    @FXML
    private void actionAdd2(ActionEvent event) {

        actionAddM(lbl3, btnBoton2, btnAdd2, btnBoton3, btnAdd3, btnAdd3, btnDelete3);

//        lbl3.setVisible(true);
//        btnBoton2.setDisable(true);
//        btnAdd2.setDisable(true);
//        btnBoton3.setVisible(true);
//        btnAdd3.setDisable(true);
//        btnAdd3.setVisible(true);
//        btnDelete3.setVisible(true);
//
//        System.out.println("hice click");
    }

    @FXML
    private void actionAdd3(ActionEvent event) {
        actionAddM(lbl4, btnBoton3, btnAdd3, btnBoton4, btnAdd4, btnAdd4, btnDelete4);

//        lbl4.setVisible(true);
//        btnBoton3.setDisable(true);
//        btnAdd3.setDisable(true);
//        btnBoton4.setVisible(true);
//        btnAdd4.setDisable(true);
//        btnAdd4.setVisible(true);
//        btnDelete4.setVisible(true);
//
//        System.out.println("hice click");
    }

    @FXML
    private void actionAdd4(ActionEvent event) {

        actionAddM(lbl5, btnBoton4, btnAdd4, btnBoton5, btnAdd5, btnAdd5, btnDelete5);

//        lbl5.setVisible(true);
//        btnBoton4.setDisable(true);
//        btnAdd4.setDisable(true);
//        btnBoton5.setVisible(true);
//        btnAdd5.setVisible(true);
//        btnAdd5.setDisable(true);
//
//        btnDelete5.setVisible(true);
//
//        System.out.println("hice click");
    }

    @FXML
    private void actionAdd5(ActionEvent event) {
        actionAddM(lbl6, btnBoton5, btnAdd5, btnBoton6, btnAdd6, btnAdd6, btnDelete6);

//        lbl6.setVisible(true);
//        btnBoton5.setDisable(true);
//        btnAdd5.setDisable(true);
//        btnBoton6.setVisible(true);
//        btnAdd6.setVisible(true);
//        btnAdd6.setDisable(true);
//        btnDelete6.setVisible(true);
//
//        System.out.println("hice click");
    }

    @FXML
    private void actionAdd6(ActionEvent event) {
        actionAddM(lbl7, btnBoton6, btnAdd6, btnBoton7, btnAdd7, btnAdd7, btnDelete7);

//        lbl7.setVisible(true);
//        btnBoton6.setDisable(true);
//        btnAdd6.setDisable(true);
//        btnBoton7.setVisible(true);
//        btnAdd7.setVisible(true);
//        btnAdd7.setDisable(true);
//
//        btnDelete7.setVisible(true);
//
//        System.out.println("hice click");
    }

    @FXML
    private void actionAdd7(ActionEvent event) {

        actionAddM(lbl8, btnBoton7, btnAdd7, btnBoton8, btnAdd8, btnAdd8, btnDelete8);

//        lbl8.setVisible(true);
//        btnBoton7.setDisable(true);
//        btnAdd7.setDisable(true);
//        btnBoton8.setVisible(true);
//        btnAdd8.setVisible(true);
//        btnAdd8.setDisable(true);
//
//        btnDelete8.setVisible(true);
//
//        System.out.println("hice click");
    }

    @FXML
    private void actionAdd8(ActionEvent event) {

        btnBoton8.setDisable(true);
        btnAdd8.setDisable(true);
        btnProcesar.setDisable(false);
        System.out.println("hice click");
    }

}
