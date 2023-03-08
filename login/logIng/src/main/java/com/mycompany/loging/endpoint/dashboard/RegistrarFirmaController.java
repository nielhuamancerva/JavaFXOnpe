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
    boolean firmoP;
    boolean firmoS;
    boolean firmoT;
    private FactoryServiciosExternos factoryservices;

    @FXML
    ImageView firma1, firma2, firma3;
    @FXML
    private Button btnSiPresi, btnSiSecre, btnSiTercer, btnNoPresi, btnNoSecre, btnNoTercer, btnVerificaTransmision, btnRegresarObs;

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
            firmoP = factoryservices.Tess4jServiceImpl().validarFirma("FI1-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png", 150, 3150, 500, 300);
            firmoS = factoryservices.Tess4jServiceImpl().validarFirma("FI2-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png", 804, 3150, 500, 300);
            firmoT = factoryservices.Tess4jServiceImpl().validarFirma("FI3-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png", 1458, 3150, 500, 300);

            System.out.println(firmoP);
            System.out.println(firmoS);
            System.out.println(firmoT);
            btnSiPresi.setStyle("-fx-background-color: " + (firmoP ? "#2ECC71" : "") + ";");
            btnNoPresi.setStyle("-fx-background-color: " + (!firmoP ? "" : "") + ";");
            btnSiSecre.setStyle("-fx-background-color: " + (firmoS ? "#2ECC71" : "") + ";");
            btnNoSecre.setStyle("-fx-background-color: " + (!firmoS ? "" : "") + ";");
            btnSiTercer.setStyle("-fx-background-color: " + (firmoT ? "#2ECC71" : "") + ";");
            btnNoTercer.setStyle("-fx-background-color: " + (!firmoT ? "" : "") + ";");

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

        btnSiPresi.setStyle("-fx-background-color: " + (firmoP ? "#2ECC71" : "") + ";");
        btnNoPresi.setStyle("-fx-background-color: " + (!firmoP ? "" : "") + ";");
    }

    @FXML
    private void siFirmoSecre(ActionEvent event) {

        btnSiSecre.setStyle("-fx-background-color: " + (firmoS ? "#2ECC71" : "") + ";");
        btnNoSecre.setStyle("-fx-background-color: " + (!firmoS ? "" : "") + ";");

    }

    @FXML
    private void siFirmoTercer(ActionEvent event) {

        btnSiTercer.setStyle("-fx-background-color: " + (firmoT ? "#2ECC71" : "") + ";");
        btnNoTercer.setStyle("-fx-background-color: " + (!firmoT ? "" : "") + ";");

    }

    @FXML
    private void noFirmoPresi(ActionEvent event) {

        btnNoPresi.setStyle("-fx-background-color: " + (firmoP ? "#2ECC71" : "") + ";");
        btnSiPresi.setStyle("-fx-background-color: " + (!firmoP ? "" : "") + ";");

    }

    @FXML
    private void noFirmoSecre(ActionEvent event) {

        btnNoSecre.setStyle("-fx-background-color: " + (firmoS ? "#2ECC71" : "") + ";");
        btnSiSecre.setStyle("-fx-background-color: " + (!firmoS ? "" : "") + ";");

    }

    @FXML
    private void noFirmoTercer(ActionEvent event) {

        btnNoTercer.setStyle("-fx-background-color: " + (firmoT ? "#2ECC71" : "") + ";");
        btnSiTercer.setStyle("-fx-background-color: " + (!firmoT ? "" : "") + ";");

    }

    @FXML
    private void verificaTransmite() throws IOException {
        App.setRoot(null, "transmisionRabbit");
    }

}
