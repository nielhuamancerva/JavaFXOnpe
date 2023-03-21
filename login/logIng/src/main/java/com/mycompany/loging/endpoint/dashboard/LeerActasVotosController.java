package com.mycompany.loging.endpoint.dashboard;

import com.mycompany.loging.App;
import com.mycompany.loging.score.Repository.FactoryServiciosExternos;
import com.mycompany.loging.score.negocio.NegocioServiceImpl;
import com.mycompany.loging.score.negocio.service.NegocioService;
import com.mycompany.loging.score.util.CreateObject;
import com.mycompany.loging.score.util.DropShadowE;
import com.mycompany.loging.score.util.constanst.VariableGlobales;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class LeerActasVotosController implements Initializable {

    private final NegocioService negocioService;
    private FactoryServiciosExternos factoryservices;
    private DropShadowE dropShadowE;

    public LeerActasVotosController() {
        this.negocioService = new NegocioServiceImpl();
        this.dropShadowE = new DropShadowE();
    }

    @FXML
    ImageView imagenVotos, codigoBarra;

    @FXML
    private Button btnRegresar, btnSiguiente;

    @FXML
    private TextField[] voto;

    @FXML
    VBox votoBox;

    @FXML
    TextField voto1, voto2, voto3, voto4, voto5, voto6, voto7, voto8, voto9, voto10, voto11, voto12, voto13, voto14,
            voto15, voto16, voto17, voto18, voto19, voto20;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dropShadowE.setTabEffect(btnRegresar);
        dropShadowE.setTabEffect(btnSiguiente);

//        voto = new TextField[20];
//        for (int i = 0; i < voto.length ; i++) {
//            voto[i] = new TextField();
//            votoBox.getChildren().add(voto[i]);
//            
//        }
        codigoBarra.setImage(CreateObject.image(VariableGlobales.lecturaActasEnMemoria.get("codigoBarra")));

        try {
            negocioService.readAndCutOrganizationsPolitical(
                    Integer.parseInt(VariableGlobales.configuracionActa.get("regionOrganizaciones" + "Xo")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("regionOrganizaciones" + "Yo")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("regionOrganizaciones" + "Ancho")),
                    Integer.parseInt(VariableGlobales.configuracionActa.get("regionOrganizaciones" + "Alto")));
            
            imagenVotos.setImage(
                    CreateObject.image(
                            VariableGlobales.lecturaActasEnMemoria.get("leerRegionNumeroVotosUri")));

            /*factoryservices.Tess4jServiceImpl().leerNumeroVotos("voto1", 1514, 120, 200, 240);
            factoryservices.Tess4jServiceImpl().leerNumeroVotos("voto2", 1514, 440, 200, 240);
            factoryservices.Tess4jServiceImpl().leerNumeroVotos("voto3", 1514, 780, 200, 240);
            factoryservices.Tess4jServiceImpl().leerNumeroVotos("voto4", 1514, 1120, 200, 240);
            factoryservices.Tess4jServiceImpl().leerNumeroVotos("voto5", 1514, 1460, 200, 240);
            voto1.setText(VariableGlobales.lecturaActasEnMemoria.get("voto1"));
            voto2.setText(VariableGlobales.lecturaActasEnMemoria.get("voto2"));
            voto3.setText(VariableGlobales.lecturaActasEnMemoria.get("voto3"));
            voto4.setText(VariableGlobales.lecturaActasEnMemoria.get("voto4"));
            voto5.setText(VariableGlobales.lecturaActasEnMemoria.get("voto5"));*/
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void regresarLeerActas() throws IOException {
        App.setRoot(null, "leerActas");
    }

    @FXML
    private void registrarObs() throws IOException {
        App.setRoot(null, "registrarObs");

    }

}
