/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.loging.endpoint.dashboard;

import com.mycompany.loging.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author rdela
 */
public class VerificaFirmasController implements Initializable {

    @FXML
    private ComboBox<?> cboDocumentos;
    @FXML
    private Label lbVaDistrito;
    @FXML
    private Label lbVaprovincia;
    @FXML
    private Label lbVaDepartamento;
    @FXML
    private ImageView imagenCodigoBarra;
    @FXML
    private ImageView imagenHoraFin;
    @FXML
    private TextField txtHoraInicio;
    @FXML
    private ImageView imagenHoraInicio;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

}