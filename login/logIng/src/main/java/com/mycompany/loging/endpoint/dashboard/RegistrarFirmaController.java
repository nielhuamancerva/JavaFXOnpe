/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.loging.endpoint.dashboard;

import com.mycompany.loging.App;
import com.mycompany.loging.score.util.VariableGlobales;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author RDeLaCruz
 */
public class RegistrarFirmaController implements Initializable {

    @FXML
    ImageView firma1, firma2, firma3;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image imgfirma1 = new Image(VariableGlobales.lecturaActasEnMemoria.get("firma1"));
        firma1.setImage(imgfirma1);

        Image imgfirma2 = new Image(VariableGlobales.lecturaActasEnMemoria.get("firma2"));
        firma2.setImage(imgfirma2);

        Image imgfirma3 = new Image(VariableGlobales.lecturaActasEnMemoria.get("firma3"));
        firma3.setImage(imgfirma3);
    }

    @FXML
    private void regresarActaVoto() throws IOException {
        App.setRoot(null, "registrarObs");
    }

}
