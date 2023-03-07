/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.loging.endpoint.dashboard;

import com.mycompany.loging.App;
import com.mycompany.loging.score.util.DropShadowE;
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
public class RegistrarFirmaController implements Initializable {
    
    private DropShadowE dropShadowE;

    @FXML
    ImageView firma1, firma2, firma3;
    @FXML
    private Button btnSiPresi;
    @FXML
    private Button btnSiSecre;
    @FXML
    private Button btnSiTercer;
    @FXML
    private Button btnNoPresi;
    @FXML
    private Button btnNoSecre;
    @FXML
    private Button btnNoTercer;
    @FXML
    private Button btnSalir;
    @FXML
    private Button btnSiguiente;

    //constructor
    public RegistrarFirmaController() {
        this.dropShadowE = new DropShadowE();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        dropShadowE.setTabEffect(btnSiguiente);
        dropShadowE.setTabEffect(btnSalir);
        dropShadowE.setTabEffect(btnSiPresi);
        dropShadowE.setTabEffect(btnSiSecre);
        dropShadowE.setTabEffect(btnSiTercer);
        dropShadowE.setTabEffect(btnNoPresi);
        dropShadowE.setTabEffect(btnNoSecre);
        dropShadowE.setTabEffect(btnNoTercer);
        
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

    @FXML
    private void siFirmoPresi(ActionEvent event) {
        boolean firmoP = true;
        btnSiPresi.setStyle("-fx-background-color: " + (firmoP ? "#2ECC71" : "") + ";");
        btnNoPresi.setStyle("-fx-background-color: " + (!firmoP ? "" : "") + ";");
    }

    @FXML
    private void siFirmoSecre(ActionEvent event) {

        boolean firmoS = true;
        btnSiSecre.setStyle("-fx-background-color: " + (firmoS ? "#2ECC71" : "") + ";");
        btnNoSecre.setStyle("-fx-background-color: " + (!firmoS ? "" : "") + ";");

    }

    @FXML
    private void siFirmoTercer(ActionEvent event) {

        boolean firmoT = true;
        btnSiTercer.setStyle("-fx-background-color: " + (firmoT ? "#2ECC71" : "") + ";");
        btnNoTercer.setStyle("-fx-background-color: " + (!firmoT ? "" : "") + ";");

    }

    @FXML
    private void noFirmoPresi(ActionEvent event) {

        boolean firmoP = true;
        btnNoPresi.setStyle("-fx-background-color: " + (firmoP ? "#2ECC71" : "") + ";");
        btnSiPresi.setStyle("-fx-background-color: " + (!firmoP ? "" : "") + ";");

    }

    @FXML
    private void noFirmoSecre(ActionEvent event) {

        boolean firmoS = true;
        btnNoSecre.setStyle("-fx-background-color: " + (firmoS ? "#2ECC71" : "") + ";");
        btnSiSecre.setStyle("-fx-background-color: " + (!firmoS ? "" : "") + ";");

    }

    @FXML
    private void noFirmoTercer(ActionEvent event) {
        boolean firmoT = true;
        btnNoTercer.setStyle("-fx-background-color: " + (firmoT ? "#2ECC71" : "") + ";");
        btnSiTercer.setStyle("-fx-background-color: " + (!firmoT ? "" : "") + ";");

    }

    @FXML
    private void verificaTransmite()throws IOException{
         App.setRoot(null, "transmisionRabbit");
    }

}
