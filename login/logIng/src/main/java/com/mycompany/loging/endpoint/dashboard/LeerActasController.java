/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.loging.endpoint.dashboard;

import com.mycompany.loging.App;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author LMedina
 */
public class LeerActasController implements Initializable {
    private LocalTime horaSistema = LocalTime.now();
    
    private String fechaFormatoCadena="";
    int hora;
    int minutos;
    
    @FXML
    TextField txtHora;
    @FXML
    Label lbFecha;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        calcularFecha();
        this.txtHora.setText(hora+":"+minutos);
        this.lbFecha.setText(fechaFormatoCadena);
    }  
    
    
    private void calcularFecha(){
    hora = horaSistema.getHour();
    minutos =horaSistema.getMinute();
    /*--------------------*/
    Date fehaActual = new Date();
    SimpleDateFormat formatoAnio = new SimpleDateFormat("yyyy");
    SimpleDateFormat formatoMes = new SimpleDateFormat("MMMM");
    Calendar calendario = Calendar.getInstance();
    calendario.setTime(fehaActual);
    int dia = calendario.get(Calendar.DAY_OF_MONTH);
    String anio = formatoAnio.format(fehaActual);
    String mes = formatoMes.format(fehaActual);
    fechaFormatoCadena = "del "+dia+" de "+mes+" de "+anio+", se inicio el ACTO DE ESCRUTINIO";
    
    }

    @FXML
    private void regresarInicio() throws IOException {
        App.setRoot(null,"cargarActas");
    }
    
    
    
    
    
}
