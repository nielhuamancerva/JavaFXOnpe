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
    boolean firmoP, firmoS, firmoT;
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
            firmoP = negocioService.readAndCutsignature(
                    "FI1-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png",
                    Integer.parseInt(VariableGlobales.configuracionActa.get("1"  + "Xo")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("1" + "Yo")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("1" + "Ancho")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("1" + "Alto")));
            firmoS = negocioService.readAndCutsignature(
                    "FI2-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png",
                    Integer.parseInt(VariableGlobales.configuracionActa.get("1" + "Xo")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("1" + "Yo")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("1" + "Ancho")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("1" + "Alto")));
            firmoT = negocioService.readAndCutsignature(
                    "FI3-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png",
                    Integer.parseInt(VariableGlobales.configuracionActa.get("1" + "Xo")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("1" + "Yo")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("1" + "Ancho")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("1" + "Alto")));

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

    private void cambiaStBtn(Button btn1, Button btn2) {
        btn1.setStyle("-fx-background-color: #2ECC71;");
        btn2.setStyle("");
        btn2.getStyleClass().remove("boton-active");
        btn2.getStyleClass().remove("boton-activeN");

    }


    @FXML
    private void ActionFirmoSiP(ActionEvent event) {

        cambiaStBtn(btnSiPresi, btnNoPresi);
    }

    @FXML
    private void ActionFirmoSiS(ActionEvent event) {
        cambiaStBtn(btnSiSecre, btnNoSecre);
    }

    @FXML
    private void ActionFirmoSiT(ActionEvent event) {
        cambiaStBtn(btnSiTercer, btnNoTercer);
    }

    @FXML
    private void ActionFirmoNoP(ActionEvent event) {
        cambiaStBtn(btnNoPresi, btnSiPresi);
    }

    @FXML
    private void ActionFirmoNoS(ActionEvent event) {
        cambiaStBtn(btnNoSecre, btnSiSecre);
    }

    @FXML
    private void ActionFirmoNoT(ActionEvent event) {
        cambiaStBtn(btnNoTercer, btnSiTercer);
    }

}
