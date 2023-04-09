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
import com.mycompany.loging.score.model.Modules;
import com.mycompany.loging.score.model.Setting;
import com.mycompany.loging.score.negocio.NegocioServiceImpl;
import com.mycompany.loging.score.negocio.service.NegocioService;
import com.mycompany.loging.score.util.CreateObject;
import com.mycompany.loging.score.util.constanst.VariableGlobals;
import static com.mycompany.loging.score.util.constanst.VariableGlobals.list;
import static com.mycompany.loging.score.util.constanst.VariableGlobals.listModules;
import static com.mycompany.loging.score.util.constanst.VariableGlobals.nombreDelArchivoProcesado;
import com.mycompany.loging.score.util.mapper.Mappers;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private Button btnAdd;

    public VerificaFirmasController() {
        this.negocioService = new NegocioServiceImpl();
    }

    private File fileSeleccionado;
    @FXML
    private ComboBox<String> cboDocumentos;
    @FXML
    private Label lbVaDistrito, lbVaprovincia, lbVaDepartamento;
    @FXML
    private ImageView imagenCodigoBarra, imagenHoraFin, imagenHoraInicio, firma1, firma2, firma3;
    VBox buttones;
    @FXML
    private TextField txtHoraInicio, txtHoraInicio1;
    @FXML
    private Button btnSiPresi, btnNoPresi, btnNoSecre, btnSiSecre, btnSiTercer, btnNoTercer, btnRegresar, btnContinuar;
    @FXML
    private VBox ContainerHora, ContainerVboxFirma;
    @FXML
    private AnchorPane ModuleFirma;
    @FXML
    Label lbArchivosEncontrados;

    Image img;

    ObservableList<Setting> itemList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            itemList = negocioService.finAllSetting();
            ObservableList<String> ii = FXCollections.observableArrayList(itemList.stream().map(r -> r.getName()).collect(Collectors.toList()));

            cboDocumentos.setItems(ii);

        } catch (Exception e) {
        }
    }

    @FXML
    private void selecionarActa(ActionEvent event) {
        cboDocumentos.getValue();

        String variable = itemList.stream().filter(j -> j.getName().equals(cboDocumentos.getValue())).map(r -> r.getSetting()).collect(Collectors.toList()).get(0);
//        variable = variable.replace(" ", "");
        Gson gson = new Gson();

        listModules = gson.fromJson(variable, Modules[].class);
        itemList.forEach(r -> {
            if (r.getName().equals(cboDocumentos.getValue())) {
                try {
                    negocioService.findAllSectionsOnCorrdinates(r.getId_setting());

                } catch (Exception ex) {
                    Logger.getLogger(VerificaFirmasController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );

    }

    @FXML
    private void horaIniciohandleOnKeyPressed(KeyEvent event) {
    }

    @FXML
    private void ActionFirmoSiP(ActionEvent event) {
        cambiaStBtn(btnSiPresi, btnNoPresi);
    }

    @FXML
    private void ActionFirmoNoP(ActionEvent event) {
        cambiaStBtn(btnNoPresi, btnSiPresi);
    }

    @FXML
    private void actionRegresar() throws IOException {
        App.setRoot(null, "inicioMenu");
    }

    @FXML
    private void actionContinuar() throws IOException {
        VariableGlobals.actasLeida.setFirma1(String.valueOf(firmoP));
        VariableGlobals.actasLeida.setFirma2(String.valueOf(firmoS));
        VariableGlobals.actasLeida.setFirma3(String.valueOf(firmoT));
        App.setRoot(null, "leerActasVotos");
    }

    @FXML
    private void elegirFichero() throws Exception, IOException {
        nombreDelArchivoProcesado = "";
        FileChooser fileChoiser = new FileChooser();
        fileChoiser.setTitle("Elegir Actas");
        fileSeleccionado = fileChoiser.showOpenDialog(null);

        lbArchivosEncontrados.setText(negocioService.uploadFileOnMemory(fileSeleccionado));
        nombreDelArchivoProcesado = cboDocumentos.getValue();
        for (Modules module : listModules) {

            switch (module.getTypeModule()) {
                case "Codigo Barra":
                    negocioService.readAndCutBarcode(Mappers.transformaTointerger(module.getCoordinatesXo()),
                            Mappers.transformaTointerger(module.getCoordinatesYo()),
                            Mappers.transformaTointerger(module.getCoordinatesWigth()),
                            Mappers.transformaTointerger(module.getCoordinatesHeigth()));
                    imagenCodigoBarra.setImage(CreateObject.image(VariableGlobals.lecturaActasEnMemoria.get("codigoBarra")));

                    VariableGlobals.actasLeida = negocioService.finByCodigoBarra(VariableGlobals.lecturaActasEnMemoria.get("codigoBarraResponse"));

                    lbVaDepartamento.setText(VariableGlobals.actasLeida.getDepartamento());
                    lbVaprovincia.setText(VariableGlobals.actasLeida.getProvincia());
                    lbVaDistrito.setText(VariableGlobals.actasLeida.getDistrito());

                    break;
                case "Hora":
                    negocioService.readAndCutHoraInicio(module.getNameModule(),
                            Mappers.transformaTointerger(module.getCoordinatesXo()),
                            Mappers.transformaTointerger(module.getCoordinatesYo()),
                            Mappers.transformaTointerger(module.getCoordinatesWigth()),
                            Mappers.transformaTointerger(module.getCoordinatesHeigth()));

                    Label labelAnchorHora = new Label();
                    labelAnchorHora.setText(module.getNameModule());
                    labelAnchorHora.getStylesheets().add(getClass().getResource("/css/local.css").toExternalForm());
                    labelAnchorHora.getStyleClass().add("fielset-title");
                    labelAnchorHora.getStyleClass().add("apBlanco");
                    labelAnchorHora.setLayoutX(25);
                    labelAnchorHora.setLayoutY(-35);

                    TextField textFieldAnchorPane = new TextField();
                    textFieldAnchorPane.setLayoutX(275);
                    textFieldAnchorPane.setLayoutY(20);
                    textFieldAnchorPane.setPrefHeight(55);
                    textFieldAnchorPane.setPrefWidth(82);
                    textFieldAnchorPane.getStylesheets().add(getClass().getResource("/css/local.css").toExternalForm());
                    textFieldAnchorPane.getStyleClass().add("search-text");
                    textFieldAnchorPane.setStyle("-fx-border-color:black");

                    ImageView imageViewAnchorPane = new ImageView();
                    imageViewAnchorPane.setFitHeight(70);
                    imageViewAnchorPane.setFitWidth(246);
                    imageViewAnchorPane.setLayoutX(14);
                    imageViewAnchorPane.setLayoutY(15);
                    imageViewAnchorPane.setImage(CreateObject.image(VariableGlobals.lecturaActasEnMemoria.get(module.getNameModule())));

                    AnchorPane anchorHora = new AnchorPane();
                    anchorHora.setPrefWidth(361);
                    anchorHora.setPrefHeight(88);
                    anchorHora.getStylesheets().add(getClass().getResource("/css/local.css").toExternalForm());
                    anchorHora.getStyleClass().add("fielset");
                    anchorHora.getChildren().addAll(labelAnchorHora, imageViewAnchorPane, textFieldAnchorPane);
                    ContainerHora.getChildren().addAll(anchorHora);
                    ContainerHora.setMargin(anchorHora, new Insets(50, 0, 0, 0));
                    break;
                case "Regiones":
                    negocioService.readAndCutOrganizationsPolitical(
                            Mappers.transformaTointerger(module.getCoordinatesXo()),
                            Mappers.transformaTointerger(module.getCoordinatesYo()),
                            Mappers.transformaTointerger(module.getCoordinatesWigth()),
                            Mappers.transformaTointerger(module.getCoordinatesHeigth()));

                    break;
                case "Observaciones":
                    negocioService.readAndCutObservations(
                            Mappers.transformaTointerger(module.getCoordinatesXo()),
                            Mappers.transformaTointerger(module.getCoordinatesYo()),
                            Mappers.transformaTointerger(module.getCoordinatesWigth()),
                            Mappers.transformaTointerger(module.getCoordinatesHeigth()));
                    break;
                case "Firma":
                    firmoP = negocioService.readAndCutsignature(module.getNameModule(),
                            Mappers.transformaTointerger(module.getCoordinatesXo()),
                            Mappers.transformaTointerger(module.getCoordinatesYo()),
                            Mappers.transformaTointerger(module.getCoordinatesWigth()),
                            Mappers.transformaTointerger(module.getCoordinatesHeigth()));

                    Label labelAnchorFirma = new Label();
                    labelAnchorFirma.setText("VALIDAR FIRMAS DE ACTA");
                    labelAnchorFirma.getStylesheets().add(getClass().getResource("/css/local.css").toExternalForm());
                    labelAnchorFirma.getStyleClass().add("fielset-title");
                    labelAnchorFirma.getStyleClass().add("apBlanco");
                    labelAnchorFirma.setLayoutX(25);
                    labelAnchorFirma.setLayoutY(-35);

                    ImageView imageViewfirma = new ImageView();
                    imageViewfirma.setImage(CreateObject.image(VariableGlobals.lecturaActasEnMemoria.get(module.getNameModule())));
                    imageViewfirma.setFitHeight(98);
                    imageViewfirma.setFitWidth(249);
                    imageViewfirma.setLayoutX(12);
                    imageViewfirma.setLayoutY(17);

//                    Image imageCreate = new Image(getClass().getResource("/imagenes/ico.cancelar.png").toExternalForm());
//                    imageViewfirma.setImage(imageCreate);
//                    imageViewfirma.setFitWidth(38);
//                    imageViewfirma.setFitHeight(38);
//                    imageViewfirma.setVisible(true);
                    Button buttonSiFirma = new Button("SI FIRMO");
                    buttonSiFirma.setPrefWidth(128);
                    buttonSiFirma.setPrefHeight(62);
                    buttonSiFirma.setLayoutX(412);
                    buttonSiFirma.setLayoutY(43);

                    Button buttonNoFirma = new Button("NO FIRMO");
                    buttonNoFirma.setPrefWidth(128);
                    buttonNoFirma.setPrefHeight(62);
                    buttonNoFirma.setLayoutX(273);
                    buttonNoFirma.setLayoutY(43);

                    buttonSiFirma.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            buttonSiFirma.setStyle("-fx-background-color: #2ECC71;");
                            buttonNoFirma.setStyle("");
                            buttonNoFirma.getStyleClass().remove("boton-active");
                            buttonNoFirma.getStyleClass().remove("boton-activeN");

//                            ActivarBoton(btn1);
                        }
                    });

                    buttonNoFirma.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            buttonNoFirma.setStyle("-fx-background-color: #2ECC71;");
                            buttonSiFirma.setStyle("");
                            buttonSiFirma.getStyleClass().remove("boton-active");
                            buttonSiFirma.getStyleClass().remove("boton-activeN");

//                            ActivarBoton(btn1);
                        }
                    });

                    AnchorPane anchorFirma = new AnchorPane();
                    anchorFirma.setPrefWidth(545);
                    anchorFirma.setPrefHeight(125);
                    anchorFirma.getChildren().addAll(imageViewfirma, buttonSiFirma, buttonNoFirma);

                    ContainerVboxFirma.getChildren().addAll(anchorFirma);
                    ContainerVboxFirma.setMargin(anchorFirma, new Insets(45, 0, 80, 0));

                    ModuleFirma.getChildren().addAll(labelAnchorFirma);
                    ModuleFirma.getStylesheets().add(getClass().getResource("/css/local.css").toExternalForm());
                    ModuleFirma.getStyleClass().add("fielset");

//
//        firmoS = negocioService.readAndCutsignature("FI2-" + VariableGlobals.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png",
//                Mappers.transformaTointerger(VariableGlobals.configuracionActa.get(list.get(6) + "Xo")),
//                Mappers.transformaTointerger(VariableGlobals.configuracionActa.get(list.get(6) + "Yo")),
//                Mappers.transformaTointerger(VariableGlobals.configuracionActa.get(list.get(6) + "Ancho")),
//                Mappers.transformaTointerger(VariableGlobals.configuracionActa.get(list.get(6) + "Alto")));
//
//        firmoT = negocioService.readAndCutsignature("FI3-" + VariableGlobals.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png",
//                Mappers.transformaTointerger(VariableGlobals.configuracionActa.get(list.get(7) + "Xo")),
//                Mappers.transformaTointerger(VariableGlobals.configuracionActa.get(list.get(7) + "Yo")),
//                Mappers.transformaTointerger(VariableGlobals.configuracionActa.get(list.get(7) + "Ancho")),
//                Mappers.transformaTointerger(VariableGlobals.configuracionActa.get(list.get(7) + "Alto")));
//
//        btnSiPresi.getStyleClass().add(firmoP ? "boton-active" : "");
//        btnNoPresi.getStyleClass().add(!firmoP ? "boton-activeN" : "");
//        btnSiSecre.getStyleClass().add(firmoP ? "boton-active" : "");
//        btnNoSecre.getStyleClass().add(!firmoP ? "boton-activeN" : "");
//        btnSiTercer.getStyleClass().add(firmoP ? "boton-active" : "");
//        btnNoTercer.getStyleClass().add(!firmoP ? "boton-activeN" : "");
//
//        firma1.setImage(CreateObject.image(VariableGlobals.lecturaActasEnMemoria.get("FI1-" + VariableGlobals.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png")));
//        firma2.setImage(CreateObject.image(VariableGlobals.lecturaActasEnMemoria.get("FI2-" + VariableGlobals.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png")));
//        firma3.setImage(CreateObject.image(VariableGlobals.lecturaActasEnMemoria.get("FI3-" + VariableGlobals.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png")));
//
//
//        img = new Image("file:" + VariableGlobals.lecturaActasEnMemoria.get("fileNamePathOriginal"));// solo se neceita en esta ubiacion la carga del fichero
//        //Image img = new Image(fileSeleccionado.toURI().toString());
//        //imgViewActa.setImage(img);
//        VariableGlobals.lecturaActasEnMemoria.put("tipoActa", cboDocumentos.getValue());
                    break;
                default:
                    // Código a ejecutar si la variable no coincide con ninguno de los valores anteriores
                    break;
            }
        }

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

    @FXML
    private void actionAddConfigura(ActionEvent event) {
    }
}
