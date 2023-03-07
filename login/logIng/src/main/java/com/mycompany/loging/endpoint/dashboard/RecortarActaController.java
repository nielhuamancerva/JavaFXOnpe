/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.loging.endpoint.dashboard;

import com.google.zxing.NotFoundException;
import com.mycompany.loging.score.util.VariableGlobales;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * FXML Controller class
 *
 * @author LMedina
 */
public class RecortarActaController implements Initializable {
    
    @FXML
    Label lbMensaje;
    
    @FXML
    ImageView imgViewActa;
    
    @FXML
    AnchorPane imgAnchorPane;
    
    double x1=0,y1=0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lbMensaje.setText("Mensaje Para Recortar Acta");
        try {
            TifToPng( VariableGlobales.lecturaActasEnMemoria.get("pathTesseract"), VariableGlobales.lecturaActasEnMemoria.get("path"), VariableGlobales.lecturaActasEnMemoria.get("fileName"));
            Image img = new Image(VariableGlobales.lecturaActasEnMemoria.get("TifToPng"));
            imgViewActa.setImage(img);
        } catch (Exception e) {
            System.err.println("Excepcion de tipo :"+e);
        }
        
        //eventos para seleccionar un area a recortar
         //
        imgViewActa.setOnMousePressed(event -> {
            x1 = event.getX();
            y1 = event.getY();
            Circle circle = new Circle(x1, y1, 10, Color.RED);
            imgAnchorPane.getChildren().add(circle);
            System.out.println("x1:"+x1+"| y1:"+y1);
        });
        
        imgViewActa.setOnMouseReleased(event -> {
            double x2 = event.getX();
            double y2 = event.getY();
            double Ancho=x2-x1;
            double Alto=y2-y1;
            Circle circle = new Circle(x2, y2, 10, Color.RED);
            Rectangle rect = new Rectangle(Ancho,Alto);
            rect.setX(x1);
            rect.setY(y1);
            rect.setStroke(Color.BLUE);
            rect.setFill(Color.TRANSPARENT);
            imgAnchorPane.getChildren().addAll(rect,circle);
            System.out.println("x2:"+x2+"| y2:"+y2);
           
        });
   }   
    
    private void TifToPng(String pathTesseract, String path, String nombre) throws TesseractException, IOException, NotFoundException, Exception{
        File imageFile = new File(path + nombre);
        Tesseract tc = new Tesseract();
        
        //Configurar Tesseract
        tc.setTessVariable("user_defined_dpi", "70");
        tc.setDatapath(pathTesseract);
        tc.setTessVariable("tessedit_char_whitelist", "0123456789");
        //fin configuracion
        
        BufferedImage image = ImageIO.read(imageFile);
        
        String nombreArchivo = imageFile.getName();
        int dotIndex = nombreArchivo.lastIndexOf(".");
        String nombreSinExtension = nombreArchivo.substring(0, dotIndex);
        
        File archivoTifToPng = new File(path + "TifToPng-" + nombreSinExtension + ".png");
        ImageIO.write(image, "png", archivoTifToPng);
        VariableGlobales.lecturaActasEnMemoria.put("TifToPng", archivoTifToPng.toURI().toString()); 
    }
    
}
