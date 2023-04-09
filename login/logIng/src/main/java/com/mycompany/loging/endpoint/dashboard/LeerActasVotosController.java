package com.mycompany.loging.endpoint.dashboard;

import com.mycompany.loging.App;
import com.mycompany.loging.score.Repository.FactoryServiciosExternos;
import com.mycompany.loging.score.model.Setting;
import com.mycompany.loging.score.negocio.NegocioServiceImpl;
import com.mycompany.loging.score.negocio.service.NegocioService;
import com.mycompany.loging.score.util.CreateObject;
import com.mycompany.loging.score.util.DropShadowE;
import com.mycompany.loging.score.util.constanst.VariableGlobals;
import static com.mycompany.loging.score.util.constanst.VariableGlobals.list;
import static com.mycompany.loging.score.util.constanst.VariableGlobals.nombreDelArchivoProcesado;
import com.mycompany.loging.score.util.mapper.Mappers;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class LeerActasVotosController implements Initializable {

    private final NegocioService negocioService;
    private FactoryServiciosExternos factoryservices;
    private DropShadowE dropShadowE;
    @FXML
    private Label numVotoPreferencial, lblTipoActa;

    public LeerActasVotosController() {
        this.negocioService = new NegocioServiceImpl();
        this.dropShadowE = new DropShadowE();
    }

    @FXML
    ImageView imagenVotos;

    @FXML
    private Button btnRegresar, btnSiguiente;
    VBox votoBox;

    @FXML
    TextField voto1, voto2, voto3, voto4, voto5, voto6, voto7, voto8, voto9, voto10, voto11, voto12, voto13, voto14,
            voto15, voto16, voto17, voto18, voto19, voto20;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dropShadowE.setTabEffect(btnRegresar);
        dropShadowE.setTabEffect(btnSiguiente);

        lblTipoActa.setText(nombreDelArchivoProcesado);

        try {

            if (!VariableGlobals.lecturaActasEnMemoria.get("leerRegionNumeroVotosUri").equals("")) {
                imagenVotos.setImage(CreateObject.image(VariableGlobals.lecturaActasEnMemoria.get("leerRegionNumeroVotosUri")));

                voto1.setText(VariableGlobals.lecturaActasEnMemoria.get("bufferedValorVoto0"));
                voto2.setText(VariableGlobals.lecturaActasEnMemoria.get("bufferedValorVoto1"));
                voto3.setText(VariableGlobals.lecturaActasEnMemoria.get("bufferedValorVoto2"));
                voto4.setText(VariableGlobals.lecturaActasEnMemoria.get("bufferedValorVoto3"));
                voto5.setText(VariableGlobals.lecturaActasEnMemoria.get("bufferedValorVoto4"));

                System.out.println("bufferedValorVoto1 " + VariableGlobals.lecturaActasEnMemoria.get("bufferedValorVoto0"));
                System.out.println("bufferedValorVoto2 " + VariableGlobals.lecturaActasEnMemoria.get("bufferedValorVoto1"));
                System.out.println("bufferedValorVoto3 " + VariableGlobals.lecturaActasEnMemoria.get("bufferedValorVoto2"));
                System.out.println("bufferedValorVoto4 " + VariableGlobals.lecturaActasEnMemoria.get("bufferedValorVoto3"));
                System.out.println("bufferedValorVoto5 " + VariableGlobals.lecturaActasEnMemoria.get("bufferedValorVoto4"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void regresarLeerActas() throws IOException {
        App.setRoot(null, "verificaFirmas");
    }

    @FXML
    private void registrarObs() throws IOException {
        App.setRoot(null, "registrarObs");
    }
}
