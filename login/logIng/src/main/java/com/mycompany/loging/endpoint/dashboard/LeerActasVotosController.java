/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.loging.endpoint.dashboard;

import com.mycompany.loging.App;
import com.mycompany.loging.score.util.VariableGlobales;
import java.io.File;
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
 * @author LMedina
 */
public class LeerActasVotosController implements Initializable {

    @FXML
    ImageView imagenVotos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Image img = new Image(VariableGlobales.lecturaActasEnMemoria.get("votos"));

        imagenVotos.setImage(img);
    }

    @FXML
    private void regresarLeerActas() throws IOException {
        App.setRoot(null, "leerActas");

    }

    @FXML
    private void registrarObs() throws IOException {
        App.setRoot(null, "registrarObs");

    }

}
