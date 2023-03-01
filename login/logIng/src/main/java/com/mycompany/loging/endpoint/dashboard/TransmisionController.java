/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loging.endpoint.dashboard;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

/**
 *
 * @author LMedina
 */
public class TransmisionController{
    private  File fileSeleccionado;
    
    @FXML
    private void elegirFichero() throws IOException {
        FileChooser fileChoiser =new FileChooser();
        fileChoiser.setTitle("Elegir Actas");
        fileSeleccionado = fileChoiser.showOpenDialog(null);
    }
    
}
