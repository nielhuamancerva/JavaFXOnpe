package com.mycompany.loging.endpoint.dashboard;
import com.mycompany.loging.App;
import com.mycompany.loging.score.Repository.FactoryServiciosExternos;
import com.mycompany.loging.score.util.constanst.VariableGlobales;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RegistrarObsController implements Initializable {
    private FactoryServiciosExternos factoryservices;
    @FXML
    ImageView observacionesActa, codigoBarra;

    public RegistrarObsController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        factoryservices = FactoryServiciosExternos.getInstance();
        try {
            factoryservices.Tess4jServiceImpl().leerObservaciones();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Image imgObservacionesActa = new Image(VariableGlobales.lecturaActasEnMemoria.get("observaciones"));
        observacionesActa.setImage(imgObservacionesActa);

        Image imgCodigoBarra = new Image(VariableGlobales.lecturaActasEnMemoria.get("codigoBarra"));
        codigoBarra.setImage(imgCodigoBarra);
    }

    @FXML
    private void registrarFma() throws IOException{
        App.setRoot(null, "registrarFirma");
    }

    @FXML
    private void regresaActasV() throws IOException{
         App.setRoot(null, "leerActasVotos");
    }

}
