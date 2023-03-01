package com.mycompany.loging.endpoint.login;

import com.mycompany.loging.App;
import com.mycompany.loging.score.Repository.Mongo.ConexionMongoImpl;
import com.mycompany.loging.score.negocio.NegocioServiceImpl;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.bson.Document;
import com.mycompany.loging.score.negocio.service.NegocioService;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
public class PrimaryController {
    private NegocioService negocioService;
  
    public PrimaryController() {
        this.negocioService = new NegocioServiceImpl();
    }

    @FXML
    TextField userName;
    @FXML
    PasswordField passwordField;
    @FXML
    Label lbError;

    @FXML
    private void iniciandoSecion() throws Exception, IOException  {
        lbError.setText("");
         Map<String, String> validadionesFormularioIngreso = new HashMap<>();
        
         validadionesFormularioIngreso.put("username", userName.getText());
         validadionesFormularioIngreso.put("password", passwordField.getText());
      
        if(validadionesFormularioIngreso.get("username").isEmpty()){
            lbError.setText("Campo usuario requerido");
        }
        
        if(validadionesFormularioIngreso.get("password").isEmpty()){
            lbError.setText("Campo password requerido");
        }
       
        
        if(!validadionesFormularioIngreso.containsValue("")){
            Document login = negocioService.consultaUsuarioDb(userName.getText(), passwordField.getText());

           if (Objects.nonNull(login)){
               App.setRoot(null,"dashboard");            
           }else{
               lbError.setText("Usuario no esta Registrado");

           } 
        }
        

    }
}
