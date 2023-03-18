package com.mycompany.loging.endpoint.dashboard;

import com.mycompany.loging.App;
import com.mycompany.loging.score.negocio.NegocioServiceImpl;
import com.mycompany.loging.score.negocio.service.NegocioService;
import com.mycompany.loging.score.util.CreateObject;
import com.mycompany.loging.score.util.DropShadowE;
import com.mycompany.loging.score.util.constanst.VariableGlobales;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class RegistrarFirmaController implements Initializable {

    private DropShadowE dropShadowE;
    boolean firmoP,firmoS,firmoT;
    private final NegocioService negocioService;
    private boolean stButton1, stButton11, stButton2, stButton22, stButton3, stButton33 = false;

    @FXML
    ImageView firma1, firma2, firma3;
    @FXML
    private Button btnSiPresi, btnNoPresi, btnSiSecre, btnSiTercer, btnNoSecre, btnNoTercer;

    @FXML
    private Button btnVerificaTransmision, btnRegresarObs;

    public RegistrarFirmaController() {
        this.dropShadowE = new DropShadowE();
        this.negocioService = new NegocioServiceImpl();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dropShadowE.setTabEffect(btnVerificaTransmision);
        dropShadowE.setTabEffect(btnRegresarObs);
        try {
            firmoP = negocioService.readAndCutsignature("FI1-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png", 175, 4520, 780, 480);
            firmoS = negocioService.readAndCutsignature("FI2-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png", 1010, 4520, 780, 480);
            firmoT = negocioService.readAndCutsignature("FI3-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png", 1840, 4520, 780, 480);

            btnSiPresi.getStyleClass().add(firmoP ? "boton-active" : "");
            btnNoPresi.getStyleClass().add(!firmoP ? "boton-activeN" : "");
            btnSiSecre.getStyleClass().add(firmoP ? "boton-active" : "");
            btnNoSecre.getStyleClass().add(!firmoP ? "boton-activeN" : "");
            btnSiTercer.getStyleClass().add(firmoP ? "boton-active" : "");
            btnNoTercer.getStyleClass().add(!firmoP ? "boton-activeN" : "");

            firma1.setImage(CreateObject.image(VariableGlobales.lecturaActasEnMemoria.get("FI1-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png")));
            firma2.setImage(CreateObject.image(VariableGlobales.lecturaActasEnMemoria.get("FI2-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png")));
            firma3.setImage(CreateObject.image(VariableGlobales.lecturaActasEnMemoria.get("FI3-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void regresarActaVoto() throws IOException {
        App.setRoot(null, "registrarObs");
    }

    @FXML
    private void verificaTransmite() throws IOException {
        VariableGlobales.actasLeida.setFirma1(String.valueOf(firmoP));
        VariableGlobales.actasLeida.setFirma2(String.valueOf(firmoS));
        VariableGlobales.actasLeida.setFirma3(String.valueOf(firmoT));
        App.setRoot(null, "transmisionRabbit");
    }

    private void cambiarEstadoBoton(Button botonPresi, Button otroBotonPresi, boolean estado1) {

        botonPresi.setStyle(estado1 ? "-fx-background-color: #2ECC71;" : "");
        otroBotonPresi.setStyle("");

    }

    private void ActionFirmoSi(ActionEvent event) {

//        estadoBotton = !estadoBotton;
//        if (estadoBotton) {
//            btnSiPresi.setStyle("-fx-background-color: #2ECC71;");
//            btnNoPresi.setStyle("");
//            btnNoPresi.getStyleClass().remove("boton-active");
//        } else {
//            btnSiPresi.setStyle("");
//            btnSiPresi.getStyleClass().remove("boton-active");
//        }
    }

    private void ActionFirmoNo(ActionEvent event) {

//        estadoBottonN = !estadoBottonN;
//        System.out.println(estadoBottonN);
//
//        if (estadoBottonN) {
//            btnNoPresi.setStyle("-fx-background-color: #2ECC71;");
//            btnSiPresi.setStyle("");
//            btnSiPresi.getStyleClass().remove("boton-active");
//        } else {
//            btnNoPresi.setStyle("");
//            btnNoPresi.getStyleClass().remove("boton-active");
//        }
    }

    @FXML
    private void ActionFirmoSiP(ActionEvent event) {

        stButton1 = !stButton1;
        cambiarEstadoBoton(btnSiPresi, btnNoPresi, stButton1);
    }

    @FXML
    private void ActionFirmoSiS(ActionEvent event) {

        stButton2 = !stButton2;
        cambiarEstadoBoton(btnSiSecre, btnNoSecre, stButton2);

    }

    @FXML
    private void ActionFirmoSiT(ActionEvent event) {

        stButton3 = !stButton3;
        cambiarEstadoBoton(btnSiTercer, btnNoTercer, stButton3);

    }

    @FXML
    private void ActionFirmoNoP(ActionEvent event) {
        stButton11 = !stButton11;
        cambiarEstadoBoton(btnNoPresi, btnSiPresi, stButton11);
    }

    @FXML
    private void ActionFirmoNoS(ActionEvent event) {
        stButton22 = !stButton22;
        cambiarEstadoBoton(btnNoSecre, btnSiSecre, stButton22);
    }

    @FXML
    private void ActionFirmoNoT(ActionEvent event) {
        stButton33 = !stButton33;
        cambiarEstadoBoton(btnNoTercer, btnSiTercer, stButton33);
    }

}
