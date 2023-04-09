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

public class LeerActaInstalacionController implements Initializable {

    private final NegocioService negocioService;
    private DropShadowE dropShadowE;

    @FXML
    private Button btnCancelar, btnSiguiente, btnRecortar;

    public LeerActaInstalacionController() {
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
            if ("SI".equals(VariableGlobals.lecturaActasEnMemoria.get("lecturaPrimera"))) {
                negocioService.readAndCutBarcode(Integer.parseInt(VariableGlobals.configuracionActa.get("codigoBarraCoordena" + "Xo")),
                        Integer.parseInt(VariableGlobals.configuracionActa.get("codigoBarraCoordena" + "Yo")),
                        Integer.parseInt(VariableGlobals.configuracionActa.get("codigoBarraCoordena" + "Ancho")),
                        Integer.parseInt(VariableGlobals.configuracionActa.get("codigoBarraCoordena" + "Alto")));
            }
            imagenCodigoBarra.setImage(CreateObject.image(VariableGlobals.lecturaActasEnMemoria.get("codigoBarra")));
            
            VariableGlobals.actasLeida = negocioService.finByCodigoBarra(VariableGlobals.lecturaActasEnMemoria.get("codigoBarraResponse"));
            
            lbVaDepartamento.setText(VariableGlobals.actasLeida.getDepartamento());
            lbVaprovincia.setText(VariableGlobals.actasLeida.getProvincia());
            lbVaDistrito.setText(VariableGlobals.actasLeida.getDistrito());
        } catch (Exception ex) {
            Logger.getLogger(LeerActaInstalacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void regresarInicio() throws IOException {
        App.setRoot(null, "cargarActaInstalacion");
    }

    @FXML
    private void registrarVotos() throws IOException {
        App.setRoot(null, "leerActasVotosInstalacion");
    }

    @FXML
    private void abrirRecortarActa() throws IOException {
        App.setRoot(null, "recortarActa");
    }

}
