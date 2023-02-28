package com.mycompany.loging.endpoint.login;

import com.mycompany.loging.App;
import static com.mycompany.loging.App.loadFXML;
import com.mycompany.loging.negocio.Repository.Mongo.ConexionMongo;
import com.mycompany.loging.negocio.Repository.Mongo.ConexionMongoImpl;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.bson.Document;
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
        //cambiar
        ConexionMongoImpl db = new ConexionMongoImpl();
        db.conexionMongo();
        
        //System.out.println(db.findCollection());
         
       
        lbError.setText("");
        if(userName.getText().isEmpty()){
            lbError.setText("Campo usuario requerido");
        }else if(passwordField.getText().isEmpty()){
            lbError.setText("Campo password requerido");
        }else{
            lbError.setText("Usuario no esta Registrado");
        }
        
        Document login = db.findCollection(userName.getText(), passwordField.getText());
        System.err.println("Tipo es :"+login.toJson());
        if (!login.toJson().isEmpty()){
            App.setRoot(null,"dashboard");            
        }
    }
}
