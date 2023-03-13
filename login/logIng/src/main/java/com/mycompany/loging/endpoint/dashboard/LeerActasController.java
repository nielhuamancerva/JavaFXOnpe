package com.mycompany.loging.endpoint.dashboard;

import com.mycompany.loging.App;
import com.mycompany.loging.score.Repository.FactoryServiciosExternos;
import com.mycompany.loging.score.model.Actas;
import com.mycompany.loging.score.model.ActasLeidas;
import com.mycompany.loging.score.negocio.NegocioServiceImpl;
import com.mycompany.loging.score.negocio.service.NegocioService;
import com.mycompany.loging.score.util.DropShadowE;
import com.mycompany.loging.score.util.constanst.VariableGlobales;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LeerActasController implements Initializable {

    private final NegocioService negocioService;
    private DropShadowE dropShadowE;

    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSiguiente;
    @FXML
    private Button btnRecortar;

    public LeerActasController() {
        this.negocioService = new NegocioServiceImpl();
        this.dropShadowE = new DropShadowE();
    }

    private LocalTime horaSistema = LocalTime.now();
    public static ActasLeidas acta = new ActasLeidas();
    private String fechaFormatoCadena = "";
    int hora;
    int minutos;

    @FXML
    TextField txtHora;
    @FXML
    Label lbFecha, lbVaDepartamento, lbVaDistrito, lbVaprovincia;
    @FXML
    ImageView imagenCodigoBarra;
    private FactoryServiciosExternos factoryservices;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            dropShadowE.setTabEffect(btnCancelar);
            dropShadowE.setTabEffect(btnSiguiente);

            calcularFecha();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.txtHora.setText(hora + ":" + minutos);
        this.lbFecha.setText(fechaFormatoCadena);
    }

    private void calcularFecha() throws IOException {
        hora = horaSistema.getHour();
        minutos = horaSistema.getMinute();
        /*--------------------*/
        Date fehaActual = new Date();
        SimpleDateFormat formatoAnio = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatoMes = new SimpleDateFormat("MMMM");
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fehaActual);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        String anio = formatoAnio.format(fehaActual);
        String mes = formatoMes.format(fehaActual);
        fechaFormatoCadena = "del " + dia + " de " + mes + " de " + anio + ", se inicio el ACTO DE ESCRUTINIO";

        Actas acta;
        try {
            factoryservices = FactoryServiciosExternos.getInstance();
            
            
            if("SI".equals(VariableGlobales.lecturaActasEnMemoria.get("lecturaPrimera"))){
                    factoryservices.Tess4jServiceImpl().leerCodigoDeBarras(2000, 90, 780, 220);
            }
        
            
            
            Image imgRegion = new Image(VariableGlobales.lecturaActasEnMemoria.get("codigoBarra"));
            imagenCodigoBarra.setImage(imgRegion);
            acta = negocioService.finByCodigoBarra(
                    VariableGlobales.lecturaActasEnMemoria.get("codigoBarraResponse"));
            lbVaDepartamento.setText(acta.getDepartamento());
            lbVaprovincia.setText(acta.getProvincia());
            lbVaDistrito.setText(acta.getDistrito());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void regresarInicio() throws IOException {
        App.setRoot(null, "cargarActas");
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
