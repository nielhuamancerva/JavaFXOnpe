/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onpe.com.pe.gestorconfiguracionactas.endpoint;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import onpe.com.pe.gestorconfiguracionactas.App;
import static onpe.com.pe.gestorconfiguracionactas.core.util.VariableGlobales.arrayNombresModulos;

/**
 * FXML Controller class
 *
 * @author CASSHERN
 */
public class CompletionController implements Initializable {
    
    @FXML
    VBox conteninerSettingButton, conteninerSettingButtonAdd, conteninerSettingButtonDelete;
    @FXML
    TextField textFieldEleccion;
    @FXML
    Button btnAddTitle;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO ingresarInicializador
        textFieldEleccion.addEventFilter(KeyEvent.KEY_TYPED, keyEvent->{
            if(!isNumeric(keyEvent.getCharacter())){
                keyEvent.consume();            
            }        
        });        
    }
    
    @FXML
    private void funcionProcesar() throws IOException, Exception {
        App.setRoot(null, "inicioMenu");
    }
    
    @FXML
    private void actionAddTitle(ActionEvent event) throws Exception {
        
    }
    
    @FXML
    private void regresaMenu() throws IOException {
        for (int y = 0; y < arrayNombresModulos.length; y++) {
            System.out.println(arrayNombresModulos[y]);
        }
        App.setRoot(null, "inicioMenu");
        
    }
    
    final String[] arrayNombr2 = arrayNombresModulos;
    
    @FXML
    private void actionAddModulos(ActionEvent event) throws Exception {
        TextField[] buttonEventConfi = new TextField[Integer.parseInt(textFieldEleccion.getText())];
        Button[] buttonEventAdd = new Button[Integer.parseInt(textFieldEleccion.getText())];
        
        for (int i = 0; i <= buttonEventConfi.length - 1; i++) {
            buttonEventConfi[i] = new TextField("i" + i);
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
            arrayNombresModulos = new String[buttonEventConfi.length];
        }
        
        for (int u = 0; u <= buttonEventConfi.length - 1; u++) {
            
            TextField textField = buttonEventConfi[u];
            Button btAdds = buttonEventAdd[u];
            
            Integer uu = u;
            buttonEventAdd[u].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    
                    insertar(textField.getText(), uu);
                    btAdds.setDisable(true);
                    textField.setDisable(true);
                }
                
            });
            
        }
        btnAddTitle.setDisable(true);
    }
    @FXML
    private void limpiarModulo() throws IOException{
         App.setRoot(null, "completion");
    }
    
    private void insertar(String s, Integer unidad) {
        arrayNombresModulos[unidad] = s;
        
    }
    private boolean isNumeric(String caracter){
        return caracter.matches("\\d");
    }
}
