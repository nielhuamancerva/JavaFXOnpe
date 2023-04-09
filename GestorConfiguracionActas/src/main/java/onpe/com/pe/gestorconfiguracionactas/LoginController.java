package onpe.com.pe.gestorconfiguracionactas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import onpe.com.pe.gestorconfiguracionactas.core.business.BusinessService;
import onpe.com.pe.gestorconfiguracionactas.core.business.Impl.BusinessServiceImpl;
import org.bson.Document;

public class LoginController implements Initializable {

    private final BusinessService businessService;
    @FXML
    private Button btnIngresar;
    @FXML
    private AnchorPane ppAnchorPane;
    @FXML
    private Button btnSalir;

    public LoginController() {
        this.businessService = new BusinessServiceImpl();
    }
    @FXML
    TextField userName;

    @FXML
    PasswordField passwordField;

    AnchorPane anchoPaneLogin;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//        double screenWidth = screenBounds.getWidth();
//        double screenHeight = screenBounds.getHeight();
//
//        double scaleX = screenWidth / anchoPaneLogin.getPrefWidth();
//        double scaleY = screenHeight / anchoPaneLogin.getPrefHeight();
//        System.out.println("escale X" + scaleX);
//        System.out.println("escale Y" + scaleY);
//        double scaleFactor = Math.min(scaleX, scaleY);
    }

    @FXML
    private void iniciandoSecion() throws Exception, IOException {
        Document document = businessService.findUserBy(userName.getText(), passwordField.getText());
        if (!document.isEmpty()) {
            App.setRoot(null, "inicioMenu");
        }

    }

    @FXML
    private void actionSalir(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }
}
