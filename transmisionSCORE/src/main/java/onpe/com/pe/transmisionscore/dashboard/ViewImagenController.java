/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package onpe.com.pe.transmisionscore.dashboard;

import java.io.File;
import java.io.IOException;
import static java.lang.Thread.State.NEW;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import onpe.com.pe.transmisionscore.App;
import onpe.com.pe.transmisionscore.core.util.constanst.VariableGlobales;

/**
 * FXML Controller class
 *
 * @author RDeLaCruz
 */
public class ViewImagenController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label numVotoPreferencial;
    @FXML
    private Label etiquetaVotoRev;
    @FXML
    private ImageView imageDocumentos;

    private String url;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        File file = new File("file:D:\\carpetaInvalido\\" + VariableGlobales.viewImage + ".png");

        File file = new File(VariableGlobales.viewImage);
        Image img = new Image(file.getPath());
        System.out.println("DATOS DE IMAGEN::::::::" + img.getUrl());
        imageDocumentos.setImage(img);

//        Image defaultImage = new Image("file:D/carpe/01700187O0101.png");
//        ImageView imageDocumentos = new ImageView(defaultImage);
    }

    @FXML
    private void actionSalir() throws IOException {
        App.setRoot(null, "seguimientoTransmision");
    }

}
