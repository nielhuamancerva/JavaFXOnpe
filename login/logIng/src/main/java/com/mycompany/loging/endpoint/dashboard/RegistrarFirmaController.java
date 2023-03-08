/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.loging.endpoint.dashboard;

import com.mycompany.loging.App;


import static com.mycompany.loging.endpoint.dashboard.LeerActasController.acta;
import com.mycompany.loging.score.Repository.FactoryServiciosExternos;
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

    private FactoryServiciosExternos factoryservices;

    @FXML
    ImageView firma1, firma2, firma3;
    @FXML
    private Button btnSiPresi, btnSiSecre, btnSiTercer, btnNoPresi, btnNoSecre, btnNoTercer;

    @FXML
    private Button btnVerificaTransmision;
    @FXML
    private Button btnRegresarObs;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dropShadowE.setTabEffect(btnVerificaTransmision);
        dropShadowE.setTabEffect(btnRegresarObs);
        dropShadowE.setTabEffect(btnSiPresi);
        dropShadowE.setTabEffect(btnSiSecre);
        dropShadowE.setTabEffect(btnSiTercer);
        dropShadowE.setTabEffect(btnNoPresi);
        dropShadowE.setTabEffect(btnNoSecre);
        dropShadowE.setTabEffect(btnNoTercer);

        factoryservices = FactoryServiciosExternos.getInstance();
        try {
            factoryservices.Tess4jServiceImpl().validarFirma("FI1-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png", 150, 3150, 500, 300);
            factoryservices.Tess4jServiceImpl().validarFirma("FI2-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png", 804, 3150, 500, 300);
            factoryservices.Tess4jServiceImpl().validarFirma("FI3-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png", 1458, 3150, 500, 300);

            Image imgfirma1 = new Image(VariableGlobales.lecturaActasEnMemoria.get("FI1-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png"));
            firma1.setImage(imgfirma1);

            Image imgfirma2 = new Image(VariableGlobales.lecturaActasEnMemoria.get("FI2-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png"));
            firma2.setImage(imgfirma2);

            Image imgfirma3 = new Image(VariableGlobales.lecturaActasEnMemoria.get("FI3-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png"));
            firma3.setImage(imgfirma3);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //constructor

    public RegistrarFirmaController() {
        this.dropShadowE = new DropShadowE();
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
    private void verificaTransmite() throws IOException {
        App.setRoot(null, "transmisionRabbit");
    }

}
