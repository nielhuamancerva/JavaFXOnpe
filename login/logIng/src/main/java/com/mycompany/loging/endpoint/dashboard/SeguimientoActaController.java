/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.loging.endpoint.dashboard;
import com.mycompany.loging.App;

import com.mycompany.loging.score.model.Actas;
import com.mycompany.loging.score.negocio.NegocioServiceImpl;
import com.mycompany.loging.score.negocio.service.NegocioService;
import com.mycompany.loging.score.util.CreacionTable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author RDeLaCruz
 */
public class SeguimientoActaController implements Initializable {

    @FXML
    private TableView<Actas> tableActas;

    /**
     * Initializes the controller class.
     */
    
    private final NegocioService negocioService;
    
    public SeguimientoActaController() {
         this.negocioService = new NegocioServiceImpl();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            CreacionTable yy = new CreacionTable();
            yy.viewActas(tableActas);
            tableActas.setItems(negocioService.finAllActas());
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void actionSalir() throws IOException {
        App.setRoot(null, "inicioMenu");
    }


    @FXML
    private void regresarMenu() throws IOException {
        App.setRoot(null, "inicioMenu");
    }

}
