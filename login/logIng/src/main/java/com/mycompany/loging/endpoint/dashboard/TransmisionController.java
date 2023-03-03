/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loging.endpoint.dashboard;

import com.mycompany.loging.score.model.Actas;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.mycompany.loging.App;
import javafx.event.ActionEvent;

/**
 *
 * @author LMedina
 */
public class TransmisionController implements Initializable {

    private File fileSeleccionado;

    @FXML
    Label lbArchivosEncontrados;

    @FXML
    TableView<Actas> tableActas;

    @FXML
    private void elegirFichero() throws IOException {
        FileChooser fileChoiser = new FileChooser();
        fileChoiser.setTitle("Elegir Actas");
        fileSeleccionado = fileChoiser.showOpenDialog(null);
        lbArchivosEncontrados.setText(fileSeleccionado.getName());
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
        TableColumn<Actas, Integer> columnaEdad = new TableColumn<Actas, Integer>("id_acta");
      

        TableColumn<Actas, String> columnaNombre = new TableColumn<Actas, String>("codigo");
  

        tableActas.getColumns().addAll(columnaNombre, columnaEdad);

 }
    @FXML
    public void cargarActas() throws IOException{
        App.setRoot(null,"leerActas");            
    }

    @FXML
    private void regresarDashboard() throws IOException {
        App.setRoot(null,"dashboard");  
    }

}
