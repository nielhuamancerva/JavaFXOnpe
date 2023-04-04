/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.loging.endpoint.dashboard;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import static com.mongodb.client.model.Filters.type;
import com.mycompany.loging.App;
import com.mycompany.loging.score.Repository.FactoryServiciosExternos;
import com.mycompany.loging.score.model.Actas;
import com.mycompany.loging.score.model.Setting;
import com.mycompany.loging.score.negocio.NegocioServiceImpl;
import com.mycompany.loging.score.negocio.service.NegocioService;
import com.mycompany.loging.score.util.CreateObject;
import com.mycompany.loging.score.util.constanst.VariableGlobales;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author rdela
 */
public class VerificaFirmasController implements Initializable {

    private final NegocioService negocioService;
    private boolean stButton1, stButton11, stButton2, stButton22, stButton3, stButton33 = false;
    private boolean siButton1, siButton2, siButton3, noButton1, noButton2, noButton3 = false;
    private FactoryServiciosExternos factoryservices;
    private ImageView[] ImageViewTrueOrFalse = new ImageView[3];
    boolean firmoP, firmoS, firmoT;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label numVotoPreferencial;
    @FXML
    private Label etiquetaVotoRev;

    public VerificaFirmasController() {
        this.negocioService = new NegocioServiceImpl();
    }

    private File fileSeleccionado;
    @FXML
    private ComboBox<String> cboDocumentos;
    @FXML
    private Label lbVaDistrito;
    @FXML
    private Label lbVaprovincia;
    @FXML
    private Label lbVaDepartamento;
    @FXML
    private ImageView imagenCodigoBarra, imagenHoraFin, imagenHoraInicio;
    @FXML
    VBox buttones;
    @FXML
    private TextField txtHoraInicio;
    @FXML
    private TextField txtHoraInicio1;
    @FXML
    private ImageView firma1;
    @FXML
    private Button btnSiPresi;
    @FXML
    private Button btnNoPresi;
    @FXML
    private ImageView firma2;
    @FXML
    private Button btnNoSecre;
    @FXML
    private Button btnSiSecre;
    @FXML
    private ImageView firma3;
    @FXML
    private Button btnSiTercer;
    @FXML
    private Button btnNoTercer;
    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnContinuar;
    @FXML

    Label lbArchivosEncontrados;
    ObservableList<String> opciones = FXCollections.observableArrayList( //"ACTA DE ESCRUTINIO",
            //"ACTA DE INSTALACION"
            );
    Image img;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            negocioService.loadSettingActa();
            ObservableList<Setting> itemList = negocioService.finAllSetting();
            for (Setting setting : itemList) {
                opciones.add(setting.getName());
            }

            int indiceSeleccionado = 0;
            cboDocumentos.setValue(opciones.get(indiceSeleccionado));
            cboDocumentos.setItems(opciones);
        } catch (Exception e) {
        }
    }

    @FXML
    private void horaIniciohandleOnKeyPressed(KeyEvent event) {
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

    @FXML
    private void actionRegresar() throws IOException {
        App.setRoot(null, "inicioMenu");
    }

    @FXML
    private void actionContinuar() throws IOException {
        VariableGlobales.actasLeida.setFirma1(String.valueOf(firmoP));
        VariableGlobales.actasLeida.setFirma2(String.valueOf(firmoS));
        VariableGlobales.actasLeida.setFirma3(String.valueOf(firmoT));
        App.setRoot(null, "leerActasVotos");
    }

    @FXML
    private void elegirFichero() throws IOException {
        FileChooser fileChoiser = new FileChooser();
        fileChoiser.setTitle("Elegir Actas");
        fileSeleccionado = fileChoiser.showOpenDialog(null);

        VariableGlobales.lecturaActasEnMemoria.put("lecturaPrimera", "SI");
        lbArchivosEncontrados.setText(negocioService.uploadFileOnMemory(fileSeleccionado));

        try {
            //negocioService.finSettingByname("Acta de Escrutinio 2");
            //negocioService.finAllActas();
            //Setting s =  negocioService.finAllSettingByName("Acta de Escrutinio");;
            //String map = s.getSetting();
            //ObjectMapper mapper = new ObjectMapper();
            //final Gson gson = new Gson();
            //final Properties object = gson.fromJson(map, Properties.class);
            //System.out.println("Name: "+ s.getName()+", setting: "+ s.getSetting());
            //System.out.println(VariableGlobales.configuracionActa.get("0Alto"));
            negocioService.readAndCutBarcode(
                    Integer.parseInt(VariableGlobales.configuracionActa.get("0" + "Xo")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("0" + "Yo")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("0" + "Ancho")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("0" + "Alto")));
            imagenCodigoBarra.setImage(CreateObject.image(VariableGlobales.lecturaActasEnMemoria.get("codigoBarra")));

            negocioService.readAndCutHoraInicio("H1",
                    Integer.parseInt(VariableGlobales.configuracionActa.get("1" + "Xo")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("1" + "Yo")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("1" + "Ancho")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("1" + "Alto")));
            imagenHoraInicio.setImage(CreateObject.image(VariableGlobales.lecturaActasEnMemoria.get("H1")));

            negocioService.readAndCutHoraInicio("H2",
                    Integer.parseInt(VariableGlobales.configuracionActa.get("2Xo")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("2" + "Yo")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("2" + "Ancho")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("2" + "Alto")));
            imagenHoraFin.setImage(CreateObject.image(VariableGlobales.lecturaActasEnMemoria.get("H2")));

            VariableGlobales.actasLeida = negocioService.finByCodigoBarra(
                    VariableGlobales.lecturaActasEnMemoria.get("codigoBarraResponse"));

            lbVaDepartamento.setText(VariableGlobales.actasLeida.getDepartamento());
            lbVaprovincia.setText(VariableGlobales.actasLeida.getProvincia());
            lbVaDistrito.setText(VariableGlobales.actasLeida.getDistrito());

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

            for (int i = 0; i < 3; i++) {
                ImageViewTrueOrFalse[i] = new ImageView();
                Image imageCreate = new Image(getClass().getResource("/imagenes/ico.cancelar.png").toExternalForm());
                ImageViewTrueOrFalse[i].setImage(imageCreate);
                ImageViewTrueOrFalse[i].setFitWidth(38);
                ImageViewTrueOrFalse[i].setFitHeight(38);
                ImageViewTrueOrFalse[i].setVisible(true);
                buttones.setMargin(ImageViewTrueOrFalse[i], new Insets(45, 0, 80, 0));
                buttones.getChildren().addAll(ImageViewTrueOrFalse[i]);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        img = new Image("file:" + VariableGlobales.lecturaActasEnMemoria.get("fileNamePathOriginal"));// solo se neceita en esta ubiacion la carga del fichero
        //Image img = new Image(fileSeleccionado.toURI().toString());
        //imgViewActa.setImage(img);
        VariableGlobales.lecturaActasEnMemoria.put("tipoActa", cboDocumentos.getValue());
    }

    private void cambiaStBtn(Button btn1, Button btn2) {
        btn1.setStyle("-fx-background-color: #2ECC71;");
        btn2.setStyle("");
        btn2.getStyleClass().remove("boton-active");
        btn2.getStyleClass().remove("boton-activeN");

        ActivarBoton(btn1);
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
            ImageViewTrueOrFalse[1].setVisible(true);
            Image imageCreate = new Image(getClass().getResource("/imagenes/ico.check.png").toExternalForm());
            ImageViewTrueOrFalse[1].setImage(imageCreate);
            ImageViewTrueOrFalse[1].setVisible(true);
            siButton2 = true;
        } else if (btn1.getId().equals("btnSiTercer")) {
            ImageViewTrueOrFalse[2].setVisible(true);
            Image imageCreate = new Image(getClass().getResource("/imagenes/ico.check.png").toExternalForm());
            ImageViewTrueOrFalse[2].setImage(imageCreate);
            ImageViewTrueOrFalse[2].setVisible(true);
            //siButton2 = true;
            siButton3 = true;
        } else if (btn1.getId().equals("btnNoTercer")) {
            ImageViewTrueOrFalse[2].setVisible(true);
            Image imageCreate = new Image(getClass().getResource("/imagenes/ico.check.png").toExternalForm());
            ImageViewTrueOrFalse[2].setImage(imageCreate);
            ImageViewTrueOrFalse[2].setVisible(true);
            siButton3 = true;
        }

        if (siButton1 && siButton2 && siButton3) {
            //btnVerificaTransmision.setDisable(false);
        } else {
            //btnVerificaTransmision.setDisable(true);
        }
    }
}
