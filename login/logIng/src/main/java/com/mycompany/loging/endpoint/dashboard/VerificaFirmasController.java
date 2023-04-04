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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author rdela
 */
public class VerificaFirmasController implements Initializable {

    private final NegocioService negocioService;
    private FactoryServiciosExternos factoryservices;
    boolean firmoP, firmoS, firmoT;

    public VerificaFirmasController(){
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
    @FXML

        
    ObservableList<String> opciones = FXCollections.observableArrayList(
        "ACTA DE ESCRUTINIO",
        "ACTA DE INSTALACION"
    );
    
    Image img;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int indiceSeleccionado = 0;
        cboDocumentos.setValue(opciones.get(indiceSeleccionado));
        cboDocumentos.setItems(opciones);
    }

    @FXML
    private void horaIniciohandleOnKeyPressed(KeyEvent event) {
    }

    @FXML
    private void ActionFirmoSiP(ActionEvent event) {
    }

    @FXML
    private void ActionFirmoNoP(ActionEvent event) {
    }

    @FXML
    private void ActionFirmoNoS(ActionEvent event) {
    }

    @FXML
    private void ActionFirmoSiS(ActionEvent event) {
    }

    @FXML
    private void ActionFirmoSiT(ActionEvent event) {
    }

    @FXML
    private void ActionFirmoNoT(ActionEvent event) {
    }

    @FXML
    private void actionRegresar() throws IOException {
        App.setRoot(null, "inicioMenu");
    }

    @FXML
    private void actionContinuar() throws IOException {
        App.setRoot(null, "leerActasVotos");

    }

    @FXML
    private void elegirFichero() throws IOException {
        FileChooser fileChoiser = new FileChooser();
        fileChoiser.setTitle("Elegir Actas");
        fileSeleccionado = fileChoiser.showOpenDialog(null);
       
        VariableGlobales.lecturaActasEnMemoria.put("tipoActa", cboDocumentos.getValue().toString());
        //System.out.println("qqq "+cboDocumentos.getValue()+"|||"+ VariableGlobales.lecturaActasEnMemoria.get("tipoActa"));
        VariableGlobales.lecturaActasEnMemoria.put("lecturaPrimera", "SI");
        //VariableGlobales.lecturaActasEnMemoria.get("lecturaPrimera");
        lbArchivosEncontrados.setText(negocioService.uploadFileOnMemory(fileSeleccionado));
        
        try {
            negocioService.loadSettingActa();
            negocioService.finAllSettingByName("x");
            //negocioService.finAllActas();

            Setting s =  negocioService.finAllSettingByName("x");
            String map = s.getSetting();
            
            //ObjectMapper mapper = new ObjectMapper();
            final Gson gson = new Gson();
            final Properties object = gson.fromJson(map, Properties.class);
            System.out.println("0Xo: "+object.getProperty("0Xo"));
            
            //System.out.println("Name: "+ s.getName()+", setting: "+ s.getSetting());
            System.out.println(VariableGlobales.configuracionActa.get("0Alto"));
            
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
            
            System.out.println("sss: "+VariableGlobales.lecturaActasEnMemoria.get("codigoBarra"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        img = new Image("file:" + VariableGlobales.lecturaActasEnMemoria.get("fileNamePathOriginal"));// solo se neceita en esta ubiacion la carga del fichero
        //Image img = new Image(fileSeleccionado.toURI().toString());
        //imgViewActa.setImage(img);
        
        /*
        System.out.println("aa "+ VariableGlobales.lecturaActasEnMemoria.get("fileNamePathOriginal"));
        System.out.println("xx "+ VariableGlobales.lecturaActasEnMemoria.get("codigoBarra"));
        
        if(!fileSeleccionado.toString().equals("")){
            imagenCodigoBarra.setImage(img);
        }
        */
    }
}