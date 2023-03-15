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
    private Button btnRegresar, btnBoton1, btnBoton2, btnBoton3, btnBoton4, btnBoton8, btnBoton7, btnBoton6, btnBoton5, btnCargar;

    @FXML
    private Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8;
    @FXML
    private Label lbArchivosEncontrados;
    
    @FXML
    ScrollPane scrollPaneActa;
    
    @FXML
    ImageView imgViewActa;
    private double imgX=0.0,imgY=0.0,imgX2=0.0, imgY2=0.0, imgAncho,imgAlto;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        VBox grupoBotones = new VBox();
        grupoBotones.getChildren().addAll(btnBoton1, btnBoton2, btnBoton3, btnBoton4, btnBoton5, btnBoton6, btnBoton7, btnBoton8);
        grupoBotones.setVisible(false);

        VBox grupoLabel = new VBox();
        grupoLabel.getChildren().addAll(lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8);
        grupoLabel.setVisible(false);
        
        
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
                System.out.println("inicio x:"+imgX+"||"+"inicio y:"+imgY);
            }
        });
        imgViewActa.setOnMouseReleased(event ->{
            imgX2=event.getX();
            imgY2=event.getY();
            imgAncho=imgX2-imgX;
            imgAlto=imgY2-imgY;
            Circle circle = new Circle(imgX2, imgY2, 10, Color.RED);
            Rectangle rect = new Rectangle(imgAncho,imgAlto);
            rect.setX(imgX);
            rect.setY(imgY);
            rect.setStroke(Color.BLUE);
            rect.setFill(Color.TRANSPARENT);
            Pane overlayPane = new Pane(circle,rect);
            overlayPane.setMouseTransparent(true);
            scrollPaneActa.setContent(new StackPane(imgViewActa,overlayPane));
            System.out.println("fin x2:"+imgX2+"||"+"fin y2:"+imgY2);
        });

    }

    @FXML
    private void regresaMenu() throws IOException {
        App.setRoot(null, "iniciarConfiguracion");

    }

    @FXML
    private void ActionBoton1(ActionEvent event) {
    }

    @FXML
    private void ActionBoton2(ActionEvent event) {
    }

    @FXML
    private void ActionBoton3(ActionEvent event) {
    }

    @FXML
    private void ActionBoton4(ActionEvent event) {
    }

    @FXML
    private void ActionBoton8(ActionEvent event) {
    }

    @FXML
    private void ActionBoton7(ActionEvent event) {
    }

    @FXML
    private void ActionBoton6(ActionEvent event) {
    }

    @FXML
    private void ActionBoton5(ActionEvent event) {
    }

    @FXML
    private void elegirFichero() throws IOException {

        FileChooser fileChoiser = new FileChooser();
        fileChoiser.setTitle("Elegir Actas");
        fileSeleccionado = fileChoiser.showOpenDialog(null);
        VariableGlobales.lecturaActasEnMemoria.put("lecturaPrimera", "SI");
        lbArchivosEncontrados.setText(negocioService.uploadFileOnMemory(fileSeleccionado));
        
    }

}
