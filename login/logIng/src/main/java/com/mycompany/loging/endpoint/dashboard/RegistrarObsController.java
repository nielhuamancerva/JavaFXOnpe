/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.loging.endpoint.dashboard;

import com.mycompany.loging.App;
import com.mycompany.loging.score.util.constanst.VariableGlobales;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author RDeLaCruz
 */
public class RegistrarObsController implements Initializable {


    @FXML
    ImageView observacionesActa, codigoBarra;

    public RegistrarObsController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {


        Image imgObservacionesActa = new Image(VariableGlobales.lecturaActasEnMemoria.get("observaciones"));
        observacionesActa.setImage(imgObservacionesActa);

        Image imgCodigoBarra = new Image(VariableGlobales.lecturaActasEnMemoria.get("codigoBarra"));
        codigoBarra.setImage(imgCodigoBarra);
    }

    @FXML
    private void registrarFma() throws IOException{
        App.setRoot(null, "registrarFirma");
//            System.out.println("hola");
    }

    @FXML
    private void regresaActasV() throws IOException{
         App.setRoot(null, "leerActasVotos");
    }

}
