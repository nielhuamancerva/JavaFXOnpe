/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package onpe.com.pe.gestorconfiguracionactas.endpoint;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.geometry.Insets;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import onpe.com.pe.gestorconfiguracionactas.App;
import onpe.com.pe.gestorconfiguracionactas.core.business.Impl.BusinessServiceImpl;
import onpe.com.pe.gestorconfiguracionactas.core.util.VariableGlobales;
import onpe.com.pe.gestorconfiguracionactas.core.business.BusinessService;
import onpe.com.pe.gestorconfiguracionactas.core.model.Setting;
import static onpe.com.pe.gestorconfiguracionactas.core.util.VariableGlobales.arrayNombresModulos;

/**
 * FXML Controller class
 *
 * @author NHuaman
 */
public class ConfigurarActaController implements Initializable {

    private Setting setting = new Setting();
    private double imgX = 0.0, imgY = 0.0, imgX2 = 0.0, imgY2 = 0.0, imgAncho, imgAlto;
    boolean bandImgLimpia;
    private File fileSeleccionado;
    private Image img;
    private final BusinessService businessService;

    public ConfigurarActaController() {
        this.businessService = new BusinessServiceImpl();
    }
    @FXML
    VBox conteninerSettingButton, conteninerSettingButtonAdd, conteninerSettingButtonDelete, conteninerSettingTrueOrFalse;
    @FXML
    AnchorPane buttonSetting;

    @FXML
    private Button btnRegresar, btnAddTitle, btnCargar, btnProcesar;

    @FXML
    Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9;
    @FXML
    private Label lbArchivosEncontrados;

    @FXML
    ScrollPane scrollPaneActa;

    @FXML
    ImageView imgViewActa;

    @FXML
    TextField textFieldEleccion;

    private ImageView[] ImageViewTrueOrFalse = new ImageView[arrayNombresModulos.length+1];
    private Button[] buttonEventConfi = new Button[arrayNombresModulos.length+1];
    private Button[] buttonEventAdd = new Button[arrayNombresModulos.length+1];
    private Button[] buttonEventDelete = new Button[arrayNombresModulos.length+1];

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnProcesar.setDisable(true);
        int i = 0;
        String[] array = {"Codigo QR", "Hora Inicio", "Hora Fin", "Region", "Observaciones", "Firma 1", "Firma 2", "Firma 3"};

        for (String str : arrayNombresModulos) {
            buttonEventConfi[i] = new Button(str);
            buttonEventConfi[i].setId(String.valueOf(i));
            buttonEventConfi[i].getStylesheets().add(getClass().getResource("/onpe/com/pe/styles/Style.css").toExternalForm());
            buttonEventConfi[i].getStyleClass().add("button-initializa");
            conteninerSettingButton.getChildren().addAll(buttonEventConfi[i]);
            conteninerSettingButton.setMargin(buttonEventConfi[i], new Insets(10, 0, 0, 0));

            buttonEventAdd[i] = new Button("+");
            buttonEventAdd[i].setId("buttonAdd" + i);
            buttonEventAdd[i].getStylesheets().add(getClass().getResource("/onpe/com/pe/styles/Style.css").toExternalForm());
            buttonEventAdd[i].getStyleClass().add("button-initializa-button-add");
            conteninerSettingButtonAdd.setMargin(buttonEventAdd[i], new Insets(10, 0, 0, 0));
            conteninerSettingButtonAdd.getChildren().addAll(buttonEventAdd[i]);

            buttonEventDelete[i] = new Button("-");
            buttonEventDelete[i].setId("buttonDelete" + i);
            buttonEventDelete[i].getStylesheets().add(getClass().getResource("/onpe/com/pe/styles/Style.css").toExternalForm());
            buttonEventDelete[i].getStyleClass().add("button-initializa-button-delete");
            conteninerSettingButtonDelete.setMargin(buttonEventDelete[i], new Insets(10, 0, 0, 0));
            conteninerSettingButtonDelete.getChildren().addAll(buttonEventDelete[i]);

            ImageViewTrueOrFalse[i] = new ImageView();
            Image imageCreate = new Image(getClass().getResource("/onpe/com/pe/image/ico.cancelar.png").toExternalForm());
            ImageViewTrueOrFalse[i].setImage(imageCreate);
            ImageViewTrueOrFalse[i].setFitWidth(41);
            ImageViewTrueOrFalse[i].setFitHeight(44);
            conteninerSettingTrueOrFalse.setMargin(ImageViewTrueOrFalse[i], new Insets(10, 0, 0, 0));
            conteninerSettingTrueOrFalse.getChildren().addAll(ImageViewTrueOrFalse[i]);

            ImageViewTrueOrFalse[i].setVisible(false);
            buttonEventConfi[0].setDisable(false);
            buttonEventConfi[i].setVisible(false);
            buttonEventAdd[i].setVisible(false);
            buttonEventDelete[i].setVisible(false);
            i++;
        }

        int u = 0;
        for (String str : arrayNombresModulos) {
            Button btevents = buttonEventConfi[u];

            Button btAdds = buttonEventAdd[u];
            Button btDeletes = buttonEventDelete[u];
            ImageView View = ImageViewTrueOrFalse[u];

            buttonEventConfi[u].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("ingresnado al evento" + btevents.getText());
                    btevents.setDisable(true);

                    //manda un string  para guardar las coordenads del codigo de barras el hashmap y poder recuperarlas
                    seterarEventosImageview(btevents.getId(), imgViewActa, scrollPaneActa, btAdds, btDeletes, View);
                    //Enviar un string mas para guardar los datos en variables globales
                    activarEentoImgView(false, false);
                    //enviar una variable para guardar los datos del QR

                }
            });

            if (u < 7) {
                Button bteventNexts = buttonEventConfi[u + 1];
                ImageView Viewnext = ImageViewTrueOrFalse[u + 1];

                buttonEventAdd[u].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        btAdds.setDisable(true);
                        Image imageCreate = new Image(getClass().getResource("/onpe/com/pe/image/ico.cancelar.png").toExternalForm());
                        Viewnext.setImage(imageCreate);
                        Viewnext.setVisible(true);
                        bteventNexts.setVisible(true);
                        activarEentoImgView(true, true);

                    }
                });
            }

            buttonEventDelete[u].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("ingresnado al evento eliminar" + btevents.getId());
                    actionDelete(btevents, View);
                }
            });

            u++;
        }

        imgViewActa.setOnScroll(event -> {// este evento ya esta cargado para la imagen
            if (event.isControlDown()) {
                double delta = event.getDeltaY();
                double scale = imgViewActa.getScaleX();
                if (delta > 0) {
                    imgViewActa.setScaleX(scale * 1.1);
                    imgViewActa.setScaleY(scale * 1.1);
                } else {
                    imgViewActa.setScaleX(scale / 1.1);
                    imgViewActa.setScaleY(scale / 1.1);
                }
                scrollPaneActa.setVvalue(0.5);
                scrollPaneActa.setHvalue(0.5);

            }

        });
        //BANDERA PARA Limpiar la imagen
        bandImgLimpia = false;

        
         for (int restar = 0; restar <= buttonEventConfi.length - 1; restar++) {
            if (VariableGlobales.configuracionActa.get(restar + "Xo") != null) {
               

            }
        }
        
        
        //HACER UNA FUNCION PARA VERIFICAR Y CARGAR LOS BOTONES QUE YA ESTAN SETEADOS
        if (VariableGlobales.configuracionActa.get("confirmarActa") != null) {
            if (VariableGlobales.configuracionActa.get("codigoBarraCoordenaXo") != null) {
//                ico_canc1.setVisible(false);
//                ico_check1.setVisible(true);
//                actionAddM(lbl2, btnBoton1, btnAdd1, btnBoton2, btnAdd2, btnAdd2, btnDelete2);
//                btnAdd1.setVisible(true);
//                btnDelete1.setVisible(true);
//                btnDelete2.setDisable(false);
            }
            if (VariableGlobales.configuracionActa.get("horaInicioXo") != null) {
//                ico_canc2.setVisible(false);
//                ico_check2.setVisible(true);
//                actionAddM(lbl3, btnBoton2, btnAdd2, btnBoton3, btnAdd3, btnAdd3, btnDelete3);
            }
            if (VariableGlobales.configuracionActa.get("horaFinXo") != null) {
//                ico_canc3.setVisible(false);
//                ico_check3.setVisible(true);
//                actionAddM(lbl4, btnBoton3, btnAdd3, btnBoton4, btnAdd4, btnAdd4, btnDelete4);
            }
            if (VariableGlobales.configuracionActa.get("regionOrganizacionesXo") != null) {
//                ico_canc4.setVisible(false);
//                ico_check4.setVisible(true);
//                actionAddM(lbl5, btnBoton4, btnAdd4, btnBoton5, btnAdd5, btnAdd5, btnDelete5);
            }
            if (VariableGlobales.configuracionActa.get("regionObservacionesXo") != null) {
//                ico_canc5.setVisible(false);
//                ico_check5.setVisible(true);
//                actionAddM(lbl6, btnBoton5, btnAdd5, btnBoton6, btnAdd6, btnAdd6, btnDelete6);
            }
            if (VariableGlobales.configuracionActa.get("Firma1Xo") != null) {
//                ico_canc6.setVisible(false);
//                ico_check6.setVisible(true);
//                actionAddM(lbl7, btnBoton6, btnAdd6, btnBoton7, btnAdd7, btnAdd7, btnDelete7);
            }
            if (VariableGlobales.configuracionActa.get("Firma2Xo") != null) {
//                ico_canc7.setVisible(false);
//                ico_check7.setVisible(true);
//                actionAddM(lbl8, btnBoton7, btnAdd7, btnBoton8, btnAdd8, btnAdd8, btnDelete8);
            }
            if (VariableGlobales.configuracionActa.get("Firma3Xo") != null) {
//                ico_canc8.setVisible(false);
//                ico_check8.setVisible(true);

                activarEentoImgView(true, true);
//                btnBoton8.setDisable(true);
//                btnAdd8.setDisable(true);
                btnProcesar.setDisable(false);
                System.out.println("hice click");
            }
            
            //habilitando boton cargar documento y boton guardar 
            btnCargar.setDisable(true);
            btnProcesar.setDisable(false);
            // cargando acta
            img = new Image("file:" + VariableGlobales.lecturaActasEnMemoria.get("fileNamePathOriginal"));// nota poner el file para poner la imagen
            imgViewActa.setImage(img);
            scrollPaneActa.setContent(imgViewActa);
            // encuadrando acta
            if ((int) imgViewActa.getImage().getHeight() > 4500) {
                encuadrarActa(3);
            } else if ((int) imgViewActa.getImage().getHeight() < 3600) {
                encuadrarActa(2);
                System.out.println("posisicon:" + imgViewActa.getImage().getHeight());

            }

        }

    }

    @FXML
    private void elegirFichero() throws IOException {

        FileChooser fileChoiser = new FileChooser();
        fileChoiser.setTitle("Elegir Actas");
        fileSeleccionado = fileChoiser.showOpenDialog(null);
        lbArchivosEncontrados.setText(businessService.uploadFileOnMemory(fileSeleccionado));

        img = new Image("file:" + VariableGlobales.lecturaActasEnMemoria.get("fileNamePathOriginal"));// nota poner el file para poner la imagen
        //Image img = new Image(fileSeleccionado.toURI().toString());
        imgViewActa.setImage(img);

        //verifica si el acta
        transformarScrollPane();

        //scrollPaneActa.setTranslateY();
        //set bounds para posicion
        scrollPaneActa.setContent(imgViewActa);

        if ((int) imgViewActa.getImage().getHeight() > 4500) {
            encuadrarActa(3);
        } else if ((int) imgViewActa.getImage().getHeight() < 3600) {
            encuadrarActa(2);
            System.out.println("posisicon:" + imgViewActa.getImage().getHeight());
        }
      

    }

    @FXML
    private void actionAddTitle(ActionEvent event) throws Exception {


       seterarEventosImageview("100", imgViewActa, scrollPaneActa, new Button(),  new Button(), new ImageView());

        activarEentoImgView(false, false);

    }

    @FXML
    private void regresaMenu() throws IOException {
        App.setRoot(null, "inicioMenu");

        //VariableGlobales.configuracionActa.remove("confirmarActa");
        VariableGlobales.configuracionActa.clear();

    }

    @FXML
    private void funcionProcesar() throws IOException, Exception {

        //activarEentoImgView(false, false);
        setting.setId_setting(UUID.randomUUID().toString());
        setting.setName(textFieldEleccion.getText());
        setting.setStatusSetting("0");
        Gson gson = new Gson();
        setting.setSetting(gson.toJson(VariableGlobales.configuracionActa));
        businessService.saveSetting(setting);
        VariableGlobales.configuracionActa.remove("confirmarActa");// eliminar el datoa temporal cuando se de guardar
        //App.setRoot(null, "configurarActa");
        System.out.println("hola");

        //habriendo confirmacion
        //Dialog dialog = new TextInputDialog("aqui va texto");
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        //dialog.setTitle("Desea continuar");
        dialog.setHeaderText("Los Datos Fueron Guardados Correctamente");
        dialog.setContentText("¿Desea Salir?");
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.get() == ButtonType.OK) {
            App.setRoot(null, "inicioMenu");
        }

    }

    @FXML // funcion luis
    private void verificarRegiones() throws IOException {
        App.setRoot(null, "confirmarRegionesActa");
    }

    public void transformarScrollPane() {
        double anchoImg = scrollPaneActa.getWidth();
        double altoImg = scrollPaneActa.getHeight();

        //Point2D pos = new scrollPaneActa.lo
        if (imgViewActa.getImage().getWidth() > imgViewActa.getImage().getHeight()) {
            scrollPaneActa.setPrefHeight(anchoImg);
            scrollPaneActa.setPrefWidth(altoImg);
//            System.out.println("vertical bounds:"+scrollPaneActa.getBoundsInLocal().getWidth()+"||"+scrollPaneActa.getBoundsInLocal().getHeight());
        }

    }

    private void seterarEventosImageview(String valorConfig, ImageView imgV, ScrollPane spA, Button btnAdd, Button btnDelete, ImageView img1check) {
        //

        imgV.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.SECONDARY) {// esto permite el arrastre de la imagen
                    spA.setPannable(true);
                } else {
                    spA.setPannable(false);
                }

                if (event.getButton() == MouseButton.PRIMARY) {
                    imgX = event.getX();
                    imgY = event.getY();

                    if (bandImgLimpia) {
                        imgLimpia();
                    }

                    Canvas canvas = new Canvas(imgV.getImage().getWidth(), imgV.getImage().getHeight());// capura el alto y ancho de la acta scaneada
                    GraphicsContext gc = canvas.getGraphicsContext2D();
                    gc.drawImage(imgV.getImage(), 0, 0);

                    gc.setFill(Color.RED);
                    gc.setLineWidth(10);
                    gc.fillOval(imgX, imgY, 10, 10);
                    WritableImage ImW = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
                    PixelWriter gcIw = ImW.getPixelWriter();
                    canvas.snapshot(null, ImW);
                    imgV.setImage(ImW);
                    spA.setContent(imgV);
                    System.out.println("inicio x:" + imgX + "||" + "inicio y:" + imgY);
                }
            }
        });

        imgV.setOnMouseDragged(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                Canvas canvas = new Canvas(imgV.getImage().getWidth(), imgV.getImage().getHeight());// capura el alto y ancho de la acta scaneada
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                imgX2 = event.getX();
                imgY2 = event.getY();
                VariableGlobales.lecturaActasEnMemoria.put(valorConfig + "Xf", String.valueOf(event.getX()));
                VariableGlobales.lecturaActasEnMemoria.put(valorConfig + "Yf", String.valueOf(event.getY()));
                imgAncho = imgX2 - imgX;
                imgAlto = imgY2 - imgY;

                VariableGlobales.lecturaActasEnMemoria.put(valorConfig + "Ancho", String.valueOf(imgAncho));
                VariableGlobales.lecturaActasEnMemoria.put(valorConfig + "Alto", String.valueOf(imgAlto));

                imgV.setImage(img);

                double maxX = Math.max(imgX, imgX2);
                double maxY = Math.max(imgY, imgY2);
                double minX = Math.min(imgX, imgX2);
                double minY = Math.min(imgY, imgY2);
                //
                double imgAncho2 = maxX - minX;
                double imgAlto2 = maxY - minY;
                ////

                gc.drawImage(imgV.getImage(), 0, 0);
                gc.setFill(Color.color(0, 0, 0, 0.5));

                gc.strokeRect(minX, minY, imgAncho2, imgAlto2);
                gc.fillRect(minX, minY, imgAncho2, imgAlto2);
                //Recreando la imagen
                WritableImage ImW = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
                PixelWriter gcIw = ImW.getPixelWriter();
                canvas.snapshot(null, ImW);
                imgV.setImage(ImW);
                spA.setContent(imgV);
                bandImgLimpia = true;
            }

        });

        imgV.setOnMouseReleased(event -> {

            if (event.getButton() == MouseButton.PRIMARY) {

                imgV.setImage(img);
                //
                imgX2 = event.getX();
                imgY2 = event.getY();

                double maxX = Math.max(imgX, imgX2);
                double maxY = Math.max(imgY, imgY2);
                double minX = Math.min(imgX, imgX2);
                double minY = Math.min(imgY, imgY2);
                //
                double imgAncho2 = maxX - minX;
                double imgAlto2 = maxY - minY;
                ////

                VariableGlobales.configuracionActa.put(valorConfig + "Xo", String.valueOf(Math.round(minX)));
                VariableGlobales.configuracionActa.put(valorConfig + "Yo", String.valueOf(Math.round(minY)));

                VariableGlobales.configuracionActa.put(valorConfig + "Ancho", String.valueOf(Math.round(imgAncho2)));
                VariableGlobales.configuracionActa.put(valorConfig + "Alto", String.valueOf(Math.round(imgAlto2)));

                //dibujando el rectangulo sobre la imagen
                Canvas canvas = new Canvas(imgV.getImage().getWidth(), imgV.getImage().getHeight());// capura el alto y ancho de la acta scaneada
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.drawImage(imgV.getImage(), 0, 0);
                gc.setStroke(Color.RED);
                gc.setLineWidth(10);
                //gc.strokeRect(imgX, imgY, imgAncho, imgAlto);
                gc.strokeRect(minX, minY, imgAncho2, imgAlto2);
                //Recreando la imagen
                WritableImage ImW = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
                PixelWriter gcIw = ImW.getPixelWriter();
                canvas.snapshot(null, ImW);
                imgV.setImage(ImW);
                spA.setContent(imgV);

                if (VariableGlobales.configuracionActa.get(valorConfig + "Xo").equals("")) {

                    btnAdd.setDisable(true);
                    img1check.setVisible(false);

//                    img2cancel.setVisible(true);
//                } else {
//                    btnAdd.setDisable(false);
//                    img1check.setVisible(true);
//                    img2cancel.setVisible(false);
//=======
                    btnAdd.setVisible(true);
                    btnAdd.setDisable(true);
                    img1check.setVisible(false);
//                    img2cancel.setVisible(true);

                } else {
                         System.out.println(Integer.parseInt(valorConfig) + 1);
                                   System.out.println(String.valueOf(Integer.parseInt(valorConfig) + 1));
                                   System.out.println(VariableGlobales.configuracionActa.get(String.valueOf(Integer.parseInt(valorConfig) + 1)+ "Xo"));

                    
                    btnDelete.setVisible(true);
                    btnAdd.setVisible(true);
              
                    img1check.setVisible(true);
                    Image imageCreate = new Image(getClass().getResource("/onpe/com/pe/image/ico.check.png").toExternalForm());
                    img1check.setImage(imageCreate);
                    if(VariableGlobales.configuracionActa.get(String.valueOf(Integer.parseInt(valorConfig) + 1)+ "Xo") != null){
                          btnAdd.setDisable(true);
                    }else
                    {
                              btnAdd.setDisable(false);
                    }
                }
                     System.out.println(valorConfig);
                if (buttonEventConfi[buttonEventConfi.length-2].getId().equals(valorConfig)) {
                    btnProcesar.setDisable(false);
                    btnAdd.setVisible(false);
                }
                btnDelete.setDisable(false);

                //bandera cambia de estado
                bandImgLimpia = true;


                if(valorConfig.equals("100")){
                    try {
                        textFieldEleccion.setText(businessService.readTitleActa(
                                Integer.parseInt(VariableGlobales.configuracionActa.get("100Xo")),
                                Integer.parseInt(VariableGlobales.configuracionActa.get("100Yo")),
                                Integer.parseInt(VariableGlobales.configuracionActa.get("100Ancho")),
                                Integer.parseInt(VariableGlobales.configuracionActa.get("100Alto"))));
                              buttonEventConfi[0].setVisible(true);
                    } catch (Exception ex) {
                        Logger.getLogger(ConfigurarActaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        });
    }

    private void activarEentoImgView(boolean ingview, boolean limpiarImg) {
        imgViewActa.setDisable(ingview);
        if (limpiarImg) {
            imgLimpia();
        }
    }

    private void imgLimpia() {
        //Image img = new Image("file:" + VariableGlobales.lecturaActasEnMemoria.get("fileNamePathOriginal"));// nota poner el file para poner la imagen
        //Image img = new Image(fileSeleccionado.toURI().toString());
        imgViewActa.setImage(img);
        scrollPaneActa.setContent(imgViewActa);
    }

    private void actionDelete(Button nombreButton, ImageView imgcheck) {
        System.out.println("eliminando"+nombreButton.getId());
          System.out.println(VariableGlobales.configuracionActa.get(nombreButton.getId()+ "Xo"));
      VariableGlobales.configuracionActa.put(nombreButton.getId()+ "Xo", null);
        Image imageCreate = new Image(getClass().getResource("/onpe/com/pe/image/ico.cancelar.png").toExternalForm());
        imgcheck.setImage(imageCreate);
        activarEentoImgView(true, true);
        nombreButton.setDisable(false);
        btnProcesar.setDisable(true);
        
    }

    private void encuadrarActa(int tipoHoja) {
        //algoritmo para reducir la imagen sin perderlas coordenadas
        int iteraciones = (int) Math.ceil(imgViewActa.getImage().getHeight() / scrollPaneActa.getHeight());
        System.out.println("iteraciones a reducir" + iteraciones);

        double scale = imgViewActa.getScaleX();

        if (tipoHoja == 3) {

            imgViewActa.setScaleX(scale / 8);
            imgViewActa.setScaleY(scale / 8);
        } else if (tipoHoja == 2) {

            imgViewActa.setScaleX(scale / 5.6);
            imgViewActa.setScaleY(scale / 5.6);

        }

        scrollPaneActa.setVvalue(0.5);
        scrollPaneActa.setHvalue(0.5);
    }

}
