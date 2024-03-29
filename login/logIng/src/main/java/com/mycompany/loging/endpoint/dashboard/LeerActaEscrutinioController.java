package com.mycompany.loging.endpoint.dashboard;

import com.mycompany.loging.App;
import com.mycompany.loging.score.Repository.FactoryServiciosExternos;
import com.mycompany.loging.score.model.ActasLeidas;
import com.mycompany.loging.score.negocio.NegocioServiceImpl;
import com.mycompany.loging.score.negocio.service.NegocioService;
import com.mycompany.loging.score.util.CreateObject;
import com.mycompany.loging.score.util.DropShadowE;
import com.mycompany.loging.score.util.constanst.VariableGlobals;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.util.converter.DateTimeStringConverter;
import javafx.util.converter.LocalTimeStringConverter;

public class LeerActaEscrutinioController implements Initializable {

    private final NegocioService negocioService;
    private DropShadowE dropShadowE;

    @FXML
    private Button btnCancelar, btnSiguiente, btnRecortar;

    public LeerActaEscrutinioController() {
        this.negocioService = new NegocioServiceImpl();
        this.dropShadowE = new DropShadowE();
    }

    public static ActasLeidas acta = new ActasLeidas();

    @FXML
    TextField txtHora, txtHoraInicio, txtHoraFin;
    Label lbFecha;
    @FXML
    Label lbVaDepartamento, lbVaDistrito, lbVaprovincia;
    @FXML
    ImageView imagenCodigoBarra,imagenHoraInicio,imagenHoraFin;
    private FactoryServiciosExternos factoryservices;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            dropShadowE.setTabEffect(btnCancelar);
            dropShadowE.setTabEffect(btnSiguiente);
            if ("SI".equals(VariableGlobals.lecturaActasEnMemoria.get("lecturaPrimera"))) {
                negocioService.readAndCutBarcode(Integer.parseInt(VariableGlobals.configuracionActa.get("0" + "Xo")),
                        Integer.parseInt(VariableGlobals.configuracionActa.get("0" + "Yo")),
                        Integer.parseInt(VariableGlobals.configuracionActa.get("0" + "Ancho")),
                        Integer.parseInt(VariableGlobals.configuracionActa.get("0" + "Alto")));

                negocioService.readAndCutHoraInicio("H1",
                        Integer.parseInt(VariableGlobals.configuracionActa.get("1" + "Xo")),
                        Integer.parseInt(VariableGlobals.configuracionActa.get("1" + "Yo")),
                        Integer.parseInt(VariableGlobals.configuracionActa.get("1" + "Ancho")),
                        Integer.parseInt(VariableGlobals.configuracionActa.get("1" + "Alto")));

                negocioService.readAndCutHoraInicio("H2",
                        Integer.parseInt(VariableGlobals.configuracionActa.get("2" + "Xo")),
                        Integer.parseInt(VariableGlobals.configuracionActa.get("2" + "Yo")),
                        Integer.parseInt(VariableGlobals.configuracionActa.get("2" + "Ancho")),
                        Integer.parseInt(VariableGlobals.configuracionActa.get("2" + "Alto")));
            }
            imagenCodigoBarra.setImage(CreateObject.image(VariableGlobals.lecturaActasEnMemoria.get("codigoBarra")));
            imagenHoraInicio.setImage(CreateObject.image(VariableGlobals.lecturaActasEnMemoria.get("H1")));
            imagenHoraFin.setImage(CreateObject.image(VariableGlobals.lecturaActasEnMemoria.get("H2")));

            VariableGlobals.actasLeida = negocioService.finByCodigoBarra(VariableGlobals.lecturaActasEnMemoria.get("codigoBarraResponse"));

            lbVaDepartamento.setText(VariableGlobals.actasLeida.getDepartamento());
            lbVaprovincia.setText(VariableGlobals.actasLeida.getProvincia());
            lbVaDistrito.setText(VariableGlobals.actasLeida.getDistrito());
            if (txtHoraInicio.getText().trim().equals("") || txtHoraFin.getText().trim().equals("")) {
                btnSiguiente.setDisable(true);
            }

            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            txtHoraInicio.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format), format.parse("00:00")));
            txtHoraInicio.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("[0-9:]*")) {
                    txtHoraInicio.setText(oldValue);
                }
            });

            txtHoraFin.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format), format.parse("00:00")));
            txtHoraFin.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("[0-9:]*")) {
                    txtHoraFin.setText(oldValue);
                }
            });

        } catch (Exception ex) {
            Logger.getLogger(LeerActaEscrutinioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void horaIniciohandleOnKeyPressed(KeyEvent event) throws IOException {
        //System.out.println("Pressed key text: " + event.getText());
        //System.out.println("Pressed key code: " + event.getCode());
        if (event.getText().trim().equals("") || event.getText().trim().equals("00:00") || txtHoraFin.getText().trim().equals("") || txtHoraFin.getText().trim().equals("00:00")) {
            btnSiguiente.setDisable(true);
        } else {
            btnSiguiente.setDisable(false);
        }
    }

    @FXML
    private void horaFinahandleOnKeyPressed(KeyEvent event) throws IOException {
        //System.out.println("Pressed key text: " + event.getText());
        //System.out.println("Pressed key code: " + event.getCode());
        if (event.getText().trim().equals("") || event.getText().trim().equals("00:00") || txtHoraInicio.getText().trim().equals("") || txtHoraInicio.getText().trim().equals("00:00")) {
            btnSiguiente.setDisable(true);
        } else {
            btnSiguiente.setDisable(false);
        }
    }

    @FXML
    private void regresarInicio() throws IOException {
        App.setRoot(null, "cargarActaEscrutinio");
    }

    @FXML
    private void registrarVotos() throws IOException {
        App.setRoot(null, "leerActasVotos");
    }

    @FXML
    private void abrirRecortarActa() throws IOException {
        App.setRoot(null, "recortarActa");
    }

}
