package com.mycompany.loging.endpoint.login;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
//import com.mycompany.loging.
public class PrimaryController {
    
    @FXML
    TextField userName;
    @FXML
    PasswordField passwordField;
    @FXML
    Label lbError;

    @FXML
    private void iniciandoSecion() throws IOException, Exception {
        
        lbError.setText("");
        if(userName.getText().isEmpty()){
            lbError.setText("Campo usuario requerido");
        }else if(passwordField.getText().isEmpty()){
            lbError.setText("Campo password requerido");
        }else{
            lbError.setText("Usuario no esta Registrado");
        }
       
    }
}
