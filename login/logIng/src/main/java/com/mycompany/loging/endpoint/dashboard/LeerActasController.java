/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.loging.endpoint.dashboard;

import com.mycompany.loging.App;

import com.google.gson.Gson;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.mycompany.loging.score.Repository.FactoryServiciosExternos;
import com.mycompany.loging.score.model.Actas;
import com.mycompany.loging.score.model.ActasLeidas;
import com.mycompany.loging.score.negocio.NegocioServiceImpl;
import com.mycompany.loging.score.negocio.service.NegocioService;
import com.mycompany.loging.score.util.DropShadowE;
import com.mycompany.loging.score.util.constanst.VariableGlobales;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.RescaleOp;
import java.io.ByteArrayInputStream;
import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;

/**
 * FXML Controller class
 *
 * @author LMedina
 */
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
    Label lbFecha;

    @FXML
    Label lbVaDepartamento;
    @FXML
    Label lbVaprovincia;
    @FXML
    Label lbVaDistrito;
    @FXML
    ImageView imagenCodigoBarra;

    private FactoryServiciosExternos factoryservices;

    /**
     * Initializes the controller class.
     */
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
            imagenCodigoBarra.setImage(factoryservices.Tess4jServiceImpl().leerCodigoDeBarras());
            acta = negocioService.finByCodigoBarra(
                    VariableGlobales.lecturaActasEnMemoria.get("codigoBarraResponse"));
            lbVaDepartamento.setText(acta.getDepartamento());
            lbVaprovincia.setText(acta.getProvincia());
            lbVaDistrito.setText(acta.getDistrito());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private String leeDocumentoRegion(String pathTesseract, String path, String nombre) throws TesseractException, IOException, NotFoundException, Exception {
        File imageFile = new File(path + nombre);
        Tesseract tc = new Tesseract();

        //Configurar Tesseract
//        tc.setTessVariable("user_defined_dpi", "70");
//        tc.setDatapath(pathTesseract);
//        tc.setTessVariable("tessedit_char_whitelist", "0123456789");
        BufferedImage image = ImageIO.read(imageFile);

        BufferedImage imageCodBarras = image.getSubimage(1460, 110, 620, 140);       //CODIGO DE BARRAS
        BufferedImage regionLista = image.getSubimage(210, 880, 1800, 1780);         //REGION LISTA
        BufferedImage regionObservaciones = image.getSubimage(210, 2640, 1800, 300); //REGION OBSERVACION
        BufferedImage Firma1 = image.getSubimage(150, 3150, 500, 300);               //FIRMA 1
        BufferedImage Firma2 = image.getSubimage(804, 3150, 500, 300);               //FIRMA 2
        BufferedImage Firma3 = image.getSubimage(1458, 3150, 500, 300);              //FIRMA 3

        String nombreArchivo = imageFile.getName();
        int dotIndex = nombreArchivo.lastIndexOf(".");
        String nombreSinExtension = nombreArchivo.substring(0, dotIndex);

        //CREAMOS IMAGEN CODIGO DE BARRAS
        //--- archivoCodigoBarras  
        File archivoCodigoBarras = new File(path + "BAR-" + nombreSinExtension + ".png");
        ImageIO.write(imageCodBarras, "png", archivoCodigoBarras);
        VariableGlobales.lecturaActasEnMemoria.put("codigoBarra", archivoCodigoBarras.toURI().toString());
        leerNumeroVotos(regionLista, path, pathTesseract);
        //--- archivoRegionLista
        File archivoRegionLista = new File(path + "REG-" + nombreSinExtension + ".png");
        ImageIO.write(regionLista, "png", archivoRegionLista);
        VariableGlobales.lecturaActasEnMemoria.put("votos", archivoRegionLista.toURI().toString());
        //--- archivoRegionObservaciones
        File archivoRegionObservaciones = new File(path + "OBS-" + nombreSinExtension + ".png");
        ImageIO.write(regionObservaciones, "png", archivoRegionObservaciones);
        VariableGlobales.lecturaActasEnMemoria.put("observaciones", archivoRegionObservaciones.toURI().toString());
        
        //--- archivoFirma1
        File archivoFirma1 = new File(path + "FI1-" + nombreSinExtension + ".png");
        ImageIO.write(Firma1, "png", archivoFirma1);
        VariableGlobales.lecturaActasEnMemoria.put("firma1", archivoFirma1.toURI().toString());
        //--- archivoFirma2
        File archivoFirma2 = new File(path + "FI2-" + nombreSinExtension + ".png");
        ImageIO.write(Firma2, "png", archivoFirma2);
        VariableGlobales.lecturaActasEnMemoria.put("firma2", archivoFirma2.toURI().toString());
        //--- archivoFirma3
        File archivoFirma3 = new File(path + "FI3-" + nombreSinExtension + ".png");
        ImageIO.write(Firma3, "png", archivoFirma3);
        VariableGlobales.lecturaActasEnMemoria.put("firma3", archivoFirma3.toURI().toString());
        //-------
        String codigoBarras = leerCodigoDeBarras(path + "BAR-" + nombreSinExtension + ".png");

        acta.setCodigoBarras(codigoBarras);
        acta.setFirmaPresidente(validarFirma(path + "FI1-" + nombreSinExtension + ".png", MAX_BLACK_VALUE));
        acta.setFirmaSecretario(validarFirma(path + "FI2-" + nombreSinExtension + ".png", MAX_BLACK_VALUE));
        acta.setFirmaTercerMiembro(validarFirma(path + "FI3-" + nombreSinExtension + ".png", MAX_BLACK_VALUE));

        //     System.out.println(gson.toJson(acta));
        return codigoBarras;
    }

    @FXML
    private void regresarInicio() throws IOException {
        App.setRoot(null, "cargarActas");
    }

    public String leerCodigoDeBarras(String path) throws Exception {

        File file = new File(path);

        Image img = new Image(file.toURI().toString());

        imagenCodigoBarra.setImage(img);

        BufferedImage image = ImageIO.read(file);
        // create binary bitmap from image
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
        // set decoding hints
        MultiFormatReader reader = new MultiFormatReader();
        java.util.Map<DecodeHintType, Object> hints = new java.util.EnumMap<>(DecodeHintType.class);
        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        com.google.zxing.Result result = reader.decode(bitmap, hints);

        return result.getText();
    }

    public static ActasLeidas leerNumeroVotos(BufferedImage regionLista, String path, String pathTesseract) throws Exception {
            Tesseract tc = new Tesseract();
            tc.setOcrEngineMode(2);
            tc.setTessVariable("user_defined_dpi", "70");
            tc.setDatapath(pathTesseract);
            tc.setTessVariable("tessedit_char_whitelist", "0123456789");
            
            BufferedImage bufferedPartidoA = regionLista.getSubimage(1514, 120, 200, 240);
            BufferedImage bufferedVotoBlanco = regionLista.getSubimage(1514, 440, 200, 240);
            BufferedImage bufferedVotoNulo = regionLista.getSubimage(1514, 780, 200, 240);
            BufferedImage bufferedVotoImpugnado = regionLista.getSubimage(1514, 1120, 200, 240);
            BufferedImage bufferedVotoEmitido = regionLista.getSubimage(1514, 1460, 200, 240);                  
            
            String textoPartidoA = tc.doOCR(preprocesarImagen(bufferedPartidoA, "bufferedPartidoA", path));
            String textoVotoBlanco = tc.doOCR(preprocesarImagen(bufferedVotoBlanco,"bufferedVotoBlanco", path));
            String textoVotoNulo = tc.doOCR(preprocesarImagen(bufferedVotoNulo, "bufferedVotoNulo", path));
            String textoVotoImpugnado = tc.doOCR(preprocesarImagen(bufferedVotoImpugnado, "bufferedVotoImpugnado", path));
            String textoVotoEmitido = tc.doOCR(preprocesarImagen(bufferedVotoEmitido, "bufferedVotoEmitido", path));
            //String result5 = tc.doOCR(valor5);

            acta.setPartidoA(Integer.parseInt(textoPartidoA.trim()));
            acta.setVotoBlanco(Integer.parseInt(textoVotoBlanco.trim()));
            acta.setVotoNulo(Integer.parseInt(textoVotoNulo.trim()));
            acta.setVotoImpugnado(Integer.parseInt(textoVotoImpugnado.trim()));
            acta.setVotoEmitido(Integer.parseInt(textoVotoEmitido.trim()));
            //System.out.println(result1.trim()+"-"+result2.trim()+"-"+result3.trim()+"-"+result4.trim()+"-"+result5.trim());
            return acta;
    }
    
    

    private static BufferedImage preprocesarImagen(BufferedImage bufferedImagen, String nombre, String path) throws IOException {
        bufferedImagen = ImageHelper.convertImageToGrayscale(bufferedImagen); // Convertir la imagen a escala de grises
        //bufferedImagen = adjustBrightnessContrast(bufferedImagen);
        RescaleOp rescale = new RescaleOp(1.2f, 15, null);
        BufferedImage contraste = rescale.filter(bufferedImagen, null);
        //System.out.println(path + nombre + ".png");
        File imagenGenerado = new File(path + nombre + ".png");
        ImageIO.write(contraste, "png", imagenGenerado);
        
        return contraste;
    }
    
    private static final int MAX_BLACK_VALUE = 382; // ((255 * 3) / 2) rounded down
    private static boolean validarFirma(String signatureFile, int minPixelCount) {
        try {
            BufferedImage image = ImageIO.read(new File(signatureFile));
            int bytesPerPixel = image.getColorModel().getPixelSize() / 8;

            byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
            int blackPixels = 0;
            for (int h = 0; h < image.getHeight(); h++) {
                int currentLine = h * image.getWidth() * bytesPerPixel;
                for (int w = 0; w < (image.getWidth() * bytesPerPixel); w += bytesPerPixel) {
                    int blue = pixels[currentLine + w] & 0xFF;
                    int green = pixels[currentLine + w + 1] & 0xFF;
                    int red = pixels[currentLine + w + 2] & 0xFF;

                    if (blue + green + red <= MAX_BLACK_VALUE) {
                        blackPixels++;
                        if (blackPixels >= minPixelCount) {
                            //System.out.println(true);
                            return true;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(false);
        return false;
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
