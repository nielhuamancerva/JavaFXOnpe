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
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class LoginController {

    private final NegocioService negocioService;
    private final ValidadionesFormularios validadionesFormularios;
    private final DropShadowE dropShadowE;
    @FXML
    private Button btnIngresar;
    @FXML
    private AnchorPane ppAnchorPane;
    @FXML
    private Button btnSalir;

    private void initialize() {
        dropShadowE.setTabEffect(btnIngresar);
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
    private void iniciandoSecion() throws Exception, IOException {
        String Msg = validadionesFormularios.login(userName.getText(), passwordField.getText());
        if (Msg.equals("")) {
            Document login = negocioService.consultaUsuarioDb(userName.getText(), passwordField.getText());

            if (Objects.nonNull(login)) {
                App.setRoot(null, "inicioMenu");
            } else {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Usuario no registrado");
                alert.setHeaderText("El usuario ingresado no está registrado");
                alert.setContentText("Por favor, verifique que ha ingresado correctamente sus datos");

                alert.showAndWait();

            }
        }
    }

    @FXML
    private void actionSalir() throws Exception {
        Platform.exit();
        System.exit(0);
    }
}
