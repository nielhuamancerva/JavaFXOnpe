/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package onpe.com.pe.gestorconfiguracionactas.endpoint;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import onpe.com.pe.gestorconfiguracionactas.App;
import onpe.com.pe.gestorconfiguracionactas.core.business.BusinessService;
import onpe.com.pe.gestorconfiguracionactas.core.business.Impl.BusinessServiceImpl;
import onpe.com.pe.gestorconfiguracionactas.core.model.Setting;
import onpe.com.pe.gestorconfiguracionactas.core.util.VariableGlobales;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author rdela
 */
public class ConfiguraSeccionesController implements Initializable {

    public final Map<String, String> configuracionActa = new HashMap();
    private final BusinessService businessService;
    @FXML
    private ComboBox<String> cboDocumentos;
    @FXML
    private ScrollPane scrollPaneActa;
    @FXML
    private VBox vboxPane;
    @FXML
    private ImageView imgCargarActa;

    @FXML
    private ImageView imgViewActa = new ImageView();
    private File fileSeleccionado;
    private Image img;
    private int escala = 0;
    double imgX;
    double imgY, imgX2, imgY2;
    int numEs;
    double escalaLo;
    @FXML
    private Button btnValidar;
    @FXML
    private Button btnRegresar;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label numVotoPreferencial;
    @FXML
    private Label etiquetaVotoRev;

    /**
     * Initializes the controller class.
     */
    public ConfiguraSeccionesController() {
        this.businessService = new BusinessServiceImpl();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numEs = 0;

        // cargando el acta
        if (VariableGlobales.lecturaActasEnMemoria.get("fileNamePathOriginal") != null) {
            img = new Image("file:" + VariableGlobales.lecturaActasEnMemoria.get("fileNamePathOriginal"));
            imgViewActa.setImage(img);
            scrollPaneActa.setVvalue(0.5);
            scrollPaneActa.setHvalue(0.5);
            scrollPaneActa.setContent(imgViewActa);
            if (numEs == 0) {
                escalaLo = imgViewActa.getScaleX();
                numEs = 1;
            }
            if ((int) imgViewActa.getImage().getHeight() > 4500) {
                encuadrarActa(3, escalaLo);
            } else if ((int) imgViewActa.getImage().getHeight() < 3600) {
                encuadrarActa(2, escalaLo);
                System.out.println("posisicon:" + imgViewActa.getImage().getHeight());
            }
        }

        imgViewActa.setDisable(true);
        try {
            cargarSetings();// para el combobox
        } catch (Exception e) {
            System.out.println("error");
        }

        if (!VariableGlobales.identificaActa.isEmpty()) {
            VariableGlobales.identificaActa.clear();
        }

        //evento a combobox
        cboDocumentos.setOnAction(event -> {
            String seleccion = cboDocumentos.getSelectionModel().getSelectedItem().toString();
            VariableGlobales.identificaActa.put("nombreActaSeleccion", seleccion);
            //configuracionActa.put("tituloActaValidar",seleccion);

            try {
                int i = 0;
                for (Setting item : businessService.findAllSettingOnlyEleccion()) {
                    System.out.println("bucle for------" + seleccion + "||||" + ":" + item.getName().toString());
                    VariableGlobales.identificaActa.put("idSectionActaSeleccion", item.getId_setting());
                    VariableGlobales.identificaActa.put("nombreSeleccion", item.getName());
                    if (seleccion.equals(item.getName().toString())) {
                        //businessService.uploadSections(item.getId_setting(),configuracionActa.toString());// actualiza en base de datos
                        //item.getSetting().
                        System.out.println("DATO:::::::::::::::::" + item.getSetting().toString());
                        System.out.println("Tipo:::::::::::::::::" + item.getSetting().getClass());
                        Gson gson = new Gson();
                        String[] arreglo = gson.fromJson(item.getSetting(), String[].class);
                        System.out.println("arreglo ============ " + arreglo);
                        vboxPane.getChildren().clear();
                        vboxPane.setPadding(new Insets(20));
                        //int i = 0;
                        Label[] listLabel = new Label[arreglo.length];
                        Button[] listaBotones = new Button[arreglo.length];
                        for (var configuracion : arreglo) {
                            imgViewActa.setDisable(false);
                            System.out.println("DATO::LISTA::================================" + configuracion);
                            listLabel[i] = new Label(String.valueOf(configuracion));
                            listLabel[i].setStyle("-fx-font-size: 24px; -fx-text-fill: black;");
                            listLabel[i].setCursor(Cursor.HAND);
                            listaBotones[i] = new Button();
                            listaBotones[i].getStylesheets().add(getClass().getResource("/onpe/com/pe/styles/Style.css").toExternalForm());
                            listaBotones[i].getStyleClass().add("button-activar-seccion");
                            //listaBotones[i].setB
                            Button btnTem = listaBotones[i];

                            listLabel[i].setOnMouseClicked((MouseEvent ev) -> {
                                if (ev.getButton() == MouseButton.PRIMARY) {
                                    btnTem.setDisable(true);
                                }
                                imgViewActa.setOnMousePressed(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {

                                        if (event.getButton() == MouseButton.PRIMARY) {
                                            imgX = event.getX();
                                            imgY = event.getY();
                                            Canvas canvas = new Canvas(imgViewActa.getImage().getWidth(), imgViewActa.getImage().getHeight());// capura el alto y ancho de la acta scaneada
                                            GraphicsContext gc = canvas.getGraphicsContext2D();
                                            gc.drawImage(imgViewActa.getImage(), 0, 0);

                                            gc.setFill(Color.RED);
                                            gc.setLineWidth(10);
                                            gc.fillOval(imgX, imgY, 10, 10);
                                            WritableImage ImW = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
                                            PixelWriter gcIw = ImW.getPixelWriter();
                                            canvas.snapshot(null, ImW);
                                            imgViewActa.setImage(ImW);
                                            scrollPaneActa.setContent(imgViewActa);
                                            System.out.println("inicio x:" + imgX + "||" + "inicio y:" + imgY);
                                        }
                                    }
                                });
                                imgViewActa.setOnMouseDragged(eventDragagged -> {
                                    if (eventDragagged.getButton() == MouseButton.PRIMARY) {
                                        Canvas canvas = new Canvas(imgViewActa.getImage().getWidth(), imgViewActa.getImage().getHeight());// capura el alto y ancho de la acta scaneada
                                        GraphicsContext gc = canvas.getGraphicsContext2D();
                                        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                                        imgX2 = eventDragagged.getX();
                                        imgY2 = eventDragagged.getY();

                                        imgViewActa.setImage(img);

                                        double maxX = Math.max(imgX, imgX2);
                                        double maxY = Math.max(imgY, imgY2);
                                        double minX = Math.min(imgX, imgX2);
                                        double minY = Math.min(imgY, imgY2);
                                        //
                                        double imgAncho2 = maxX - minX;
                                        double imgAlto2 = maxY - minY;
                                        ////

                                        gc.drawImage(imgViewActa.getImage(), 0, 0);
                                        gc.setFill(Color.color(0, 0, 0, 0.5));

                                        gc.strokeRect(minX, minY, imgAncho2, imgAlto2);
                                        gc.fillRect(minX, minY, imgAncho2, imgAlto2);
                                        //Recreando la imagen
                                        WritableImage ImW = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
                                        PixelWriter gcIw = ImW.getPixelWriter();
                                        canvas.snapshot(null, ImW);
                                        imgViewActa.setImage(ImW);
                                        scrollPaneActa.setContent(imgViewActa);
                                    }
                                });

                                imgViewActa.setOnMouseReleased(eventReleased -> {

                                    if (eventReleased.getButton() == MouseButton.PRIMARY) {

                                        imgViewActa.setImage(img);
                                        //
                                        imgX2 = eventReleased.getX();
                                        imgY2 = eventReleased.getY();

                                        double maxX = Math.max(imgX, imgX2);
                                        double maxY = Math.max(imgY, imgY2);
                                        double minX = Math.min(imgX, imgX2);
                                        double minY = Math.min(imgY, imgY2);
                                        //
                                        double imgAncho2 = maxX - minX;
                                        double imgAlto2 = maxY - minY;
                                        ////

                                        //dibujando el rectangulo sobre la imagen
                                        Canvas canvas = new Canvas(imgViewActa.getImage().getWidth(), imgViewActa.getImage().getHeight());// capura el alto y ancho de la acta scaneada
                                        GraphicsContext gc = canvas.getGraphicsContext2D();
                                        gc.drawImage(imgViewActa.getImage(), 0, 0);
                                        gc.setStroke(Color.RED);
                                        gc.setLineWidth(10);
                                        //gc.strokeRect(imgX, imgY, imgAncho, imgAlto);
                                        gc.strokeRect(minX, minY, imgAncho2, imgAlto2);
                                        //Recreando la imagen
                                        WritableImage ImW = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
                                        PixelWriter gcIw = ImW.getPixelWriter();
                                        canvas.snapshot(null, ImW);
                                        imgViewActa.setImage(ImW);
                                        scrollPaneActa.setContent(imgViewActa);
                                        //configuracionActa.put(configuracion + "Xo", Double.toString(minX));
                                        VariableGlobales.coordenadasActa.put(configuracion + "Xo", Double.toString(minX));
                                        //configuracionActa.put(configuracion + "Yo", Double.toString(minY));
                                        VariableGlobales.coordenadasActa.put(configuracion + "Yo", Double.toString(minY));
                                        //configuracionActa.put(configuracion + "Ancho", Double.toString(imgAncho2));
                                        VariableGlobales.coordenadasActa.put(configuracion + "Ancho", Double.toString(imgAncho2));
                                        //configuracionActa.put(configuracion + "Alto", Double.toString(imgAlto2));
                                        VariableGlobales.coordenadasActa.put(configuracion + "Alto", Double.toString(imgAlto2));
                                        System.out.println("datos en Globales" + VariableGlobales.configuracionActa);
                                        //algoritmo para inserccion de base de datos
//                                        try {
//                                           
//                                            businessService.uploadSections(item.getId_setting(),VariableGlobales.coordenadasActa.toString());// actualiza en base de datos ESTO VA A CONFIRMAR
//                                        } catch (Exception e) {
//                                            System.out.println("datos:"+e);
//                                        }

                                    }

                                });
                            });
                            //AnchorPane anchor = new AnchorPane();// despues se grafica con el Hbox
                            HBox hbox = new HBox();
                            hbox.getChildren().add(listLabel[i]);
                            hbox.getChildren().add(listaBotones[i]);
                            vboxPane.getChildren().add(hbox);
                            i++;
                        }

                    }

                }

            } catch (Exception e) {
            }

        });

        // TODO los eventos para el imageview
        imgViewActa.setOnScroll(event -> {
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
            }
        });

    }

    @FXML
    private void actionRegresar() throws IOException {
        App.setRoot(null, "inicioMenu");
    }

    @FXML
    private void actionContinuar() throws IOException {
        configuracionActa.put("tituloActaValidar", "misdatosass a");
        App.setRoot(null, "validarSeccion");

    }

    @FXML
    private void cargarActa() {
        FileChooser fileChoiser = new FileChooser();
        fileChoiser.setTitle("Elegir Actas");
        fileSeleccionado = fileChoiser.showOpenDialog(null);
        //lbArchivosEncontrados.setText(businessService.uploadFileOnMemory(fileSeleccionado));

        //img = new Image("file:" + VariableGlobales.lecturaActasEnMemoria.get("fileNamePathOriginal"));// nota poner el file para poner la imagen
        VariableGlobales.lecturaActasEnMemoria.put("fileNamePathOriginal", fileSeleccionado.getPath().toString());// para chapar el nombre de la imagen
        img = new Image(fileSeleccionado.getPath().toString());
        imgViewActa.setImage(img);
        //amarrando las escalas del imageview al del scrollpanel
        //imgViewActa.fitHeightProperty().bind(Bindings.createDoubleBinding(() -> scrollPaneActa.getViewportBounds().getWidth(), scrollPaneActa.viewportBoundsProperty()));
        //imgViewActa.fitHeightProperty().bind(Bindings.createDoubleBinding(() -> scrollPaneActa.getViewportBounds().getHeight(), scrollPaneActa.viewportBoundsProperty()));
        double scale = imgViewActa.getScaleX();

        scrollPaneActa.setVvalue(0.5);
        scrollPaneActa.setHvalue(0.5);
        scrollPaneActa.setContent(imgViewActa);
        if (numEs == 0) {
            escalaLo = imgViewActa.getScaleX();
            numEs = 1;
        }
        if ((int) imgViewActa.getImage().getHeight() > 4500) {
            encuadrarActa(3, escalaLo);
        } else if ((int) imgViewActa.getImage().getHeight() < 3600) {
            encuadrarActa(2, escalaLo);
            System.out.println("posisicon:" + imgViewActa.getImage().getHeight());
        }

//        scrollPaneActa.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
//        scrollPaneActa.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
//        scrollPaneActa.setFitToWidth(false);
//        scrollPaneActa.setFitToHeight(false);
        //scrollPaneActa.setContent(imgViewActa);
    }

    private void encuadrarActa(int tipoHoja, double escala) {
        //algoritmo para reducir la imagen sin perderlas coordenadas
        int iteraciones = (int) Math.ceil(imgViewActa.getImage().getHeight() / scrollPaneActa.getHeight());
        System.out.println("iteraciones a reducir" + iteraciones);

        if (tipoHoja == 3) {

            imgViewActa.setScaleX(escala / 3);
            imgViewActa.setScaleY(escala / 3);
        } else if (tipoHoja == 2) {

            imgViewActa.setScaleX(escala / 2.5);
            imgViewActa.setScaleY(escala / 2.5);

        }

        scrollPaneActa.setVvalue(0.5);
        scrollPaneActa.setHvalue(0.5);
    }

    private void cargarSetings() throws Exception {

        for (Setting item : businessService.findAllSettingOnlyEleccion()) {
            System.out.println("el item:" + item.getName());
            cboDocumentos.getItems().add(item.getName().toString());

        }
    }

}
