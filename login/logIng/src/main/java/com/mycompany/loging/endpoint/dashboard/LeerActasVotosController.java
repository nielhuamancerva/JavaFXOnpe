package com.mycompany.loging.endpoint.dashboard;

import com.mycompany.loging.App;
import com.mycompany.loging.score.Repository.FactoryServiciosExternos;
import com.mycompany.loging.score.util.DropShadowE;
import com.mycompany.loging.score.util.constanst.VariableGlobales;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LeerActasVotosController implements Initializable {

    private FactoryServiciosExternos factoryservices;

    @FXML
    ImageView imagenVotos, codigoBarra;
    private DropShadowE dropShadowE;

    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnSiguiente;

    @FXML
    TextField voto1, voto2, voto3, voto4, voto5;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        factoryservices = FactoryServiciosExternos.getInstance();

        dropShadowE.setTabEffect(btnRegresar);
        dropShadowE.setTabEffect(btnSiguiente);

        Image img = new Image(VariableGlobales.lecturaActasEnMemoria.get("codigoBarra"));
        codigoBarra.setImage(img);

        try {
            factoryservices.Tess4jServiceImpl().leerRegionNumeroVotos(210, 880, 1800, 1780);
            Image imgRegion = new Image(VariableGlobales.lecturaActasEnMemoria.get("leerRegionNumeroVotosUri"));
            imagenVotos.setImage(imgRegion);
            factoryservices.Tess4jServiceImpl().leerNumeroVotos("voto1", 1514, 120, 200, 240);
            factoryservices.Tess4jServiceImpl().leerNumeroVotos("voto2", 1514, 440, 200, 240);
            factoryservices.Tess4jServiceImpl().leerNumeroVotos("voto3", 1514, 780, 200, 240);
            factoryservices.Tess4jServiceImpl().leerNumeroVotos("voto4", 1514, 1120, 200, 240);
            factoryservices.Tess4jServiceImpl().leerNumeroVotos("voto5", 1514, 1460, 200, 240);
            voto1.setText(VariableGlobales.lecturaActasEnMemoria.get("voto1"));
            voto2.setText(VariableGlobales.lecturaActasEnMemoria.get("voto2"));
            voto3.setText(VariableGlobales.lecturaActasEnMemoria.get("voto3"));
            voto4.setText(VariableGlobales.lecturaActasEnMemoria.get("voto4"));
            voto5.setText(VariableGlobales.lecturaActasEnMemoria.get("voto5"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LeerActasVotosController() {
        this.dropShadowE = new DropShadowE();
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
