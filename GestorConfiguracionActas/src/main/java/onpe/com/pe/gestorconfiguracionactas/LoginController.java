/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package onpe.com.pe.gestorconfiguracionactas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import onpe.com.pe.gestorconfiguracionactas.core.business.BusinessService;
import onpe.com.pe.gestorconfiguracionactas.core.business.Impl.BusinessServiceImpl;
import org.bson.Document;

/**
 * FXML Controller class
 *
 * @author NHuaman
 */
public class LoginController implements Initializable {

    private final BusinessService businessService;
    public LoginController() {
        this.businessService = new BusinessServiceImpl();
    }
    
    @FXML
    TextField userName;
    @FXML
    PasswordField passwordField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void iniciandoSecion() throws Exception, IOException {

        Document document = businessService.findUserBy(userName.getText(), passwordField.getText());
        if(!document.isEmpty()){
              App.setRoot(null, "dashboard");
        }
      
    }

    @FXML
    private void salirApp() throws Exception {
        Platform.exit();
        System.exit(0);
    }
}
