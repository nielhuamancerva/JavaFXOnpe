package com.mycompany.loging.endpoint.login;
import com.mycompany.loging.App;
import com.mycompany.loging.score.util.DropShadowE;
import com.mycompany.loging.score.util.ValidadionesFormularios;
import com.mycompany.loging.score.negocio.NegocioServiceImpl;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.bson.Document;
import com.mycompany.loging.score.negocio.service.NegocioService;
import java.util.Objects;
import javafx.application.Platform;
import javafx.scene.control.Button;
public class LoginController {
    private final NegocioService negocioService;
    private final ValidadionesFormularios validadionesFormularios;
    private final DropShadowE dropShadowE;
    @FXML
    private Button btnIngresar;
    @FXML
    private Button btnSalir;

    @FXML
    private void initialize() {
        dropShadowE.setTabEffect(btnIngresar);
        dropShadowE.setTabEffect(btnSalir);
    }

    public LoginController() {
        this.negocioService = new NegocioServiceImpl();
        this.validadionesFormularios = new ValidadionesFormularios();
        this.dropShadowE = new DropShadowE();
    }

    @FXML
    TextField userName;
    @FXML
    PasswordField passwordField;
    @FXML
    Label lbError;
    @FXML
    private void iniciandoSecion() throws Exception, IOException {
        lbError.setText("");

        String Msg = validadionesFormularios.login(userName.getText(), passwordField.getText());
        lbError.setText(Msg);
        if (Msg.equals("")) {
            Document login = negocioService.consultaUsuarioDb(userName.getText(), passwordField.getText());

            if (Objects.nonNull(login)) {
                App.setRoot(null, "dashboard");
            } else {
                lbError.setText("Usuario no esta Registrado");
            }
        }
    }

    @FXML
    private void salirApp() throws Exception {
        Platform.exit();
        System.exit(0);
    }
}
