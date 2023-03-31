package com.mycompany.loging.endpoint.dashboard;

import com.mycompany.loging.App;
import com.mycompany.loging.score.Repository.FactoryServiciosExternos;
import com.mycompany.loging.score.model.ActasLeidas;
import com.mycompany.loging.score.negocio.NegocioServiceImpl;
import com.mycompany.loging.score.negocio.service.NegocioService;
import com.mycompany.loging.score.util.CreateObject;
import com.mycompany.loging.score.util.DropShadowE;
import com.mycompany.loging.score.util.constanst.VariableGlobales;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    TextField txtHora;
    Label lbFecha;
    @FXML
    Label lbVaDepartamento, lbVaDistrito, lbVaprovincia;
    @FXML
    ImageView imagenCodigoBarra;
    private FactoryServiciosExternos factoryservices;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            dropShadowE.setTabEffect(btnCancelar);
            dropShadowE.setTabEffect(btnSiguiente);
            if ("SI".equals(VariableGlobales.lecturaActasEnMemoria.get("lecturaPrimera"))) {
                negocioService.readAndCutBarcode(
                        Integer.parseInt(VariableGlobales.configuracionActa.get("0" + "Xo")),
                        Integer.parseInt(VariableGlobales.configuracionActa.get("0" + "Yo")),
                        Integer.parseInt(VariableGlobales.configuracionActa.get("0" + "Ancho")),
                        Integer.parseInt(VariableGlobales.configuracionActa.get("0" + "Alto")));
            }
            imagenCodigoBarra.setImage(CreateObject.image(VariableGlobales.lecturaActasEnMemoria.get("codigoBarra")));
            
            VariableGlobales.actasLeida = negocioService.finByCodigoBarra(
                    VariableGlobales.lecturaActasEnMemoria.get("codigoBarraResponse"));
            
            lbVaDepartamento.setText(VariableGlobales.actasLeida.getDepartamento());
            lbVaprovincia.setText(VariableGlobales.actasLeida.getProvincia());
            lbVaDistrito.setText(VariableGlobales.actasLeida.getDistrito());
        } catch (Exception ex) {
            Logger.getLogger(LeerActaEscrutinioController.class.getName()).log(Level.SEVERE, null, ex);
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
