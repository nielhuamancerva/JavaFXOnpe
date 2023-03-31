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
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class RegistrarFirmaController implements Initializable {

    private DropShadowE dropShadowE;
    boolean firmoP, firmoS, firmoT;
    private final NegocioService negocioService;
    private boolean stButton1, stButton11, stButton2, stButton22, stButton3, stButton33 = false;
    private boolean siButton1, siButton2, siButton3, noButton1, noButton2, noButton3 = false;
    private ImageView[] ImageViewTrueOrFalse = new ImageView[3];

    @FXML
    ImageView firma1, firma2, firma3;
    @FXML
    private Button btnSiPresi, btnNoPresi, btnSiSecre, btnSiTercer, btnNoSecre, btnNoTercer;

    @FXML
    VBox buttones;

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
                    Integer.parseInt(VariableGlobales.configuracionActa.get("5" + "Xo")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("5" + "Yo")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("5" + "Ancho")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("5" + "Alto")));
            firmoS = negocioService.readAndCutsignature(
                    "FI2-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png",
                    Integer.parseInt(VariableGlobales.configuracionActa.get("6" + "Xo")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("6" + "Yo")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("6" + "Ancho")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("6" + "Alto")));
            firmoT = negocioService.readAndCutsignature(
                    "FI3-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png",
                    Integer.parseInt(VariableGlobales.configuracionActa.get("7" + "Xo")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("7" + "Yo")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("7" + "Ancho")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("7" + "Alto")));

            btnSiPresi.getStyleClass().add(firmoP ? "boton-active" : "");
            btnNoPresi.getStyleClass().add(!firmoP ? "boton-activeN" : "");
            btnSiSecre.getStyleClass().add(firmoP ? "boton-active" : "");
            btnNoSecre.getStyleClass().add(!firmoP ? "boton-activeN" : "");
            btnSiTercer.getStyleClass().add(firmoP ? "boton-active" : "");
            btnNoTercer.getStyleClass().add(!firmoP ? "boton-activeN" : "");

            firma1.setImage(CreateObject.image(VariableGlobales.lecturaActasEnMemoria.get("FI1-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png")));
            firma2.setImage(CreateObject.image(VariableGlobales.lecturaActasEnMemoria.get("FI2-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png")));
            firma3.setImage(CreateObject.image(VariableGlobales.lecturaActasEnMemoria.get("FI3-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png")));

            btnVerificaTransmision.setDisable(true);
            for (int i = 0; i < 3; i++) {
                ImageViewTrueOrFalse[i] = new ImageView();
                Image imageCreate = new Image(getClass().getResource("/imagenes/ico.cancelar.png").toExternalForm());
                ImageViewTrueOrFalse[i].setImage(imageCreate);
                ImageViewTrueOrFalse[i].setFitWidth(38);
                ImageViewTrueOrFalse[i].setFitHeight(38);
                ImageViewTrueOrFalse[i].setVisible(true);
                buttones.setMargin(ImageViewTrueOrFalse[i], new Insets(10, 0, 80, 0));
                buttones.getChildren().addAll(ImageViewTrueOrFalse[i]);

            }

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

        ActivarBoton(btn1);
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

    private void ActivarBoton(Button btn1) {
        if (btn1.getId().equals("btnSiPresi")) {
            siButton1 = true;
            Image imageCreate = new Image(getClass().getResource("/imagenes/ico.check.png").toExternalForm());
            ImageViewTrueOrFalse[0].setImage(imageCreate);
            ImageViewTrueOrFalse[0].setVisible(true);
        } else if (btn1.getId().equals("btnNoPresi")) {
            siButton1 = true;
            Image imageCreate = new Image(getClass().getResource("/imagenes/ico.check.png").toExternalForm());
            ImageViewTrueOrFalse[0].setImage(imageCreate);
            ImageViewTrueOrFalse[0].setVisible(true);

        } else if (btn1.getId().equals("btnSiSecre")) {
            ImageViewTrueOrFalse[1].setVisible(true);
            Image imageCreate = new Image(getClass().getResource("/imagenes/ico.check.png").toExternalForm());
            ImageViewTrueOrFalse[1].setImage(imageCreate);
            ImageViewTrueOrFalse[1].setVisible(true);
            siButton2 = true;
        } else if (btn1.getId().equals("btnNoSecre")) {
            siButton2 = true;
        } else if (btn1.getId().equals("btnSiTercer")) {
            ImageViewTrueOrFalse[2].setVisible(true);
            Image imageCreate = new Image(getClass().getResource("/imagenes/ico.check.png").toExternalForm());
            ImageViewTrueOrFalse[2].setImage(imageCreate);
            ImageViewTrueOrFalse[2].setVisible(true);
            siButton2 = true;
            siButton3 = true;
        } else if (btn1.getId().equals("btnNoTercer")) {
            siButton3 = true;
        }

        if (siButton1 && siButton2 && siButton3) {
            btnVerificaTransmision.setDisable(false);
        } else {
            btnVerificaTransmision.setDisable(true);
        }
    }
}
