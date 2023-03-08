/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.loging.endpoint.dashboard;

import com.google.zxing.NotFoundException;
import com.mycompany.loging.score.util.VariablesGlobalesR;
import com.mycompany.loging.score.util.constanst.VariableGlobales;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
    ImageView imgViewScroll;
    @FXML
    ImageView imageViewRecorte;
    
    @FXML
    ScrollPane imgScrollPane;
    @FXML
    AnchorPane imgAnchorPane;
    
    @FXML
    AnchorPane imgRecorteAnchorPane;    
    
    //img scroll
    double imgX=0,imgY=0,imgX2=0,imgY2=0,imgAncho=0,imgAlto=0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lbMensaje.setText("Mensaje Para Recortar Acta");
        try {
            TifToPng( VariableGlobales.lecturaActasEnMemoria.get("pathTesseract"), VariableGlobales.lecturaActasEnMemoria.get("fileNamePath"), VariableGlobales.lecturaActasEnMemoria.get("fileName"));
            Image img = new Image(VariableGlobales.lecturaActasEnMemoria.get("TifToPng"));
            imgViewScroll.setImage(img);
        } catch (Exception e) {
            System.err.println("Excepcion de tipo :"+e);
        }
        
        //eventos para seleccionar un area a recortar      
        //++++
        imgViewScroll.setOnMousePressed(event ->{
            imgX=event.getX();
            imgY=event.getY();
            Circle circle = new Circle(imgX, imgY, 10, Color.RED);
            Pane overlayPane = new Pane(circle);
            overlayPane.setMouseTransparent(true);
            imgScrollPane.setContent(new StackPane(imgViewScroll,overlayPane));
            
        });
        imgViewScroll.setOnMouseReleased(event ->{
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
            imgScrollPane.setContent(new StackPane(imgViewScroll,overlayPane));
            
            //cargando recorte
            try {
                cargarRecorte(VariableGlobales.lecturaActasEnMemoria.get("pathTesseract"), VariableGlobales.lecturaActasEnMemoria.get("fileNamePath"), VariableGlobales.lecturaActasEnMemoria.get("fileName"));
                Image imgRec = new Image(VariableGlobales.lecturaActasEnMemoria.get("codigoBarraRecorte"));
                imageViewRecorte.setImage(imgRec);
            
            } catch (Exception e) {
                System.out.println("Excepcion :"+e);
            }
        });
        //+++++
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
    
    private void cargarRecorte(String pathTesseract, String path, String nombre) throws TesseractException, IOException, NotFoundException, Exception{
        File imageFile = new File(path + nombre);
        Tesseract tc = new Tesseract();

        //Configurar Tesseract
        tc.setTessVariable("user_defined_dpi", "70");
        tc.setDatapath(pathTesseract);
        tc.setTessVariable("tessedit_char_whitelist", "0123456789");
        
        //recorte del codigo de barras
        BufferedImage image = ImageIO.read(imageFile);
        BufferedImage imageCodBarrasRecorte = image.getSubimage(Double.valueOf(Math.ceil(imgX)).intValue(), Double.valueOf(Math.ceil(imgY)).intValue(), Double.valueOf(Math.ceil(imgAncho)).intValue(), Double.valueOf(Math.ceil(imgAlto)).intValue()); // los dos primeros parametros son el punto de origenluego sigue el ancho y alto
        
        String nombreArchivo = imageFile.getName();
        int dotIndex = nombreArchivo.lastIndexOf(".");
        String nombreSinExtension = nombreArchivo.substring(0, dotIndex);
        
        File archivoRecorteCodBarras = new File(path + "BAR-RECORTE-" + nombreSinExtension + ".png");
        ImageIO.write(imageCodBarrasRecorte, "png", archivoRecorteCodBarras);
        VariableGlobales.lecturaActasEnMemoria.put("codigoBarraRecorte", archivoRecorteCodBarras.toURI().toString());
        
    }
    
}
