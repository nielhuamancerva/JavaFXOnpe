/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.loging.endpoint.dashboard;

import com.mycompany.loging.App;
import com.mycompany.loging.score.negocio.NegocioServiceImpl;
import com.mycompany.loging.score.negocio.service.NegocioService;
import com.mycompany.loging.score.util.constanst.VariableGlobales;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author RDeLaCruz
 */
public class ConfiguraActaController implements Initializable {
    
    private File fileSeleccionado;
    private final NegocioService negocioService;

    public ConfiguraActaController() {
        this.negocioService = new NegocioServiceImpl();
    }
    
    @FXML
    private Button btnRegresar, btnBoton1, btnBoton2, btnBoton3, btnBoton4, btnBoton8, btnBoton7, btnBoton6, btnBoton5, btnCargar;

    @FXML
    private Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8;
    @FXML
    private Label lbArchivosEncontrados;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        VBox grupoBotones = new VBox();
        grupoBotones.getChildren().addAll(btnBoton1, btnBoton2, btnBoton3, btnBoton4, btnBoton5, btnBoton6, btnBoton7, btnBoton8);
        grupoBotones.setVisible(false);

        VBox grupoLabel = new VBox();
        grupoLabel.getChildren().addAll(lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8);
        grupoLabel.setVisible(false);

    }

    @FXML
    private void regresaMenu() throws IOException {
        App.setRoot(null, "iniciarConfiguracion");

    }

    @FXML
    private void ActionBoton1(ActionEvent event) {
    }

    @FXML
    private void ActionBoton2(ActionEvent event) {
    }

    @FXML
    private void ActionBoton3(ActionEvent event) {
    }

    @FXML
    private void ActionBoton4(ActionEvent event) {
    }

    @FXML
    private void ActionBoton8(ActionEvent event) {
    }

    @FXML
    private void ActionBoton7(ActionEvent event) {
    }

    @FXML
    private void ActionBoton6(ActionEvent event) {
    }

    @FXML
    private void ActionBoton5(ActionEvent event) {
    }

    @FXML
    private void elegirFichero() throws IOException {

        FileChooser fileChoiser = new FileChooser();
        fileChoiser.setTitle("Elegir Actas");
        fileSeleccionado = fileChoiser.showOpenDialog(null);
        VariableGlobales.lecturaActasEnMemoria.put("lecturaPrimera", "SI");
        lbArchivosEncontrados.setText(negocioService.uploadFileOnMemory(fileSeleccionado));
        
    }

}
