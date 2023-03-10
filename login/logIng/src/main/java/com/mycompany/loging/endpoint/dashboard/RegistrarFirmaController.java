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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RegistrarFirmaController implements Initializable {
    private DropShadowE dropShadowE;
    boolean firmoP;
    boolean firmoS;
    boolean firmoT;
    private FactoryServiciosExternos factoryservices;

    @FXML
    ImageView firma1, firma2, firma3;
    @FXML
    private Button btnSiPresi, btnNoPresi, btnSiSecre, btnSiTercer, btnNoSecre, btnNoTercer;

    @FXML
    private Button btnVerificaTransmision;
    @FXML
    private Button btnRegresarObs;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dropShadowE.setTabEffect(btnVerificaTransmision);
        dropShadowE.setTabEffect(btnRegresarObs);
        factoryservices = FactoryServiciosExternos.getInstance();
        try {
            firmoP = factoryservices.Tess4jServiceImpl().validarFirma("FI1-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png", 175, 4520, 780, 480);
            firmoS = factoryservices.Tess4jServiceImpl().validarFirma("FI2-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png", 1010, 4520, 780, 480);
            firmoT = factoryservices.Tess4jServiceImpl().validarFirma("FI3-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png", 1840, 4520, 780, 480);

            System.out.println(firmoP);
            System.out.println(firmoS);
            System.out.println(firmoT);

            btnSiPresi.getStyleClass().add(firmoP ? "boton-active" : "");
            btnNoPresi.getStyleClass().add(!firmoP ? "boton-activeN" : "");
            btnSiSecre.getStyleClass().add(firmoP ? "boton-active" : "");
            btnNoSecre.getStyleClass().add(!firmoP ? "boton-activeN" : "");
            btnSiTercer.getStyleClass().add(firmoP ? "boton-active" : "");
            btnNoTercer.getStyleClass().add(!firmoP ? "boton-activeN" : "");

            Image imgfirma1 = new Image(VariableGlobales.lecturaActasEnMemoria.get("FI1-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png"));
            firma1.setImage(imgfirma1);

            Image imgfirma2 = new Image(VariableGlobales.lecturaActasEnMemoria.get("FI2-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png"));
            firma2.setImage(imgfirma2);

            Image imgfirma3 = new Image(VariableGlobales.lecturaActasEnMemoria.get("FI3-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png"));
            firma3.setImage(imgfirma3);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //constructor

    public RegistrarFirmaController() {
        this.dropShadowE = new DropShadowE();
    }

    @FXML
    private void regresarActaVoto() throws IOException {
        App.setRoot(null, "registrarObs");
    }

    @FXML
    private void verificaTransmite() throws IOException {
        App.setRoot(null, "transmisionRabbit");
    }

}
