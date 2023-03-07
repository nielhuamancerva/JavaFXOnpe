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
import com.mycompany.loging.score.model.Actas;
import com.mycompany.loging.score.model.ActasLeidas;
import com.mycompany.loging.score.negocio.NegocioServiceImpl;
import com.mycompany.loging.score.negocio.service.NegocioService;
import com.mycompany.loging.score.util.constanst.VariableGlobales;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * FXML Controller class
 *
 * @author LMedina
 */
public class LeerActasController implements Initializable {

    private final NegocioService negocioService;

    public LeerActasController() {
        this.negocioService = new NegocioServiceImpl();
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

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

        try {

            VariableGlobales.lecturaActasEnMemoria.put("pathTesseract", "D:\\TESSORC\\tessdata");
            VariableGlobales.lecturaActasEnMemoria.put("path", "D:\\TESSORC\\LEIDOS\\");
            String codigobarra = leeDocumentoRegion(
                    VariableGlobales.lecturaActasEnMemoria.get("pathTesseract"),
                    VariableGlobales.lecturaActasEnMemoria.get("path"),
                    VariableGlobales.lecturaActasEnMemoria.get("fileName"));
            Actas acta = negocioService.finByCodigoBarra(codigobarra);
            lbVaDepartamento.setText(acta.getDepartamento());
            lbVaprovincia.setText(acta.getProvincia());
            lbVaDistrito.setText(acta.getDistrito());
        } catch (NotFoundException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private String leeDocumentoRegion(String pathTesseract, String path, String nombre) throws TesseractException, IOException, NotFoundException, Exception {
        File imageFile = new File(path + nombre);
        Tesseract tc = new Tesseract();

        //Configurar Tesseract
        tc.setTessVariable("user_defined_dpi", "70");
        tc.setDatapath(pathTesseract);
        tc.setTessVariable("tessedit_char_whitelist", "0123456789");

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
        //tc.setPageSegMode(PSM.SINGLE_CHAR);
        tc.setTessVariable("user_defined_dpi", "2400");
        tc.setDatapath(pathTesseract);
        tc.setTessVariable("tessedit_char_whitelist", "0123456789");

        BufferedImage valor1 = regionLista.getSubimage(1514, 120, 200, 240);
        BufferedImage valor2 = regionLista.getSubimage(1514, 440, 200, 240);
        BufferedImage valor3 = regionLista.getSubimage(1514, 780, 200, 240);
        BufferedImage valor4 = regionLista.getSubimage(1514, 1120, 200, 240);
        BufferedImage valor5 = regionLista.getSubimage(1514, 1460, 200, 240);

        File archivoValor1 = new File(path + "archivoValor1.png");
        ImageIO.write(valor1, "png", archivoValor1);

        File archivoValor2 = new File(path + "archivoValor2.png");
        ImageIO.write(valor2, "png", archivoValor2);

        File archivoValor3 = new File(path + "archivoValor3.png");
        ImageIO.write(valor3, "png", archivoValor3);

        File archivoValor4 = new File(path + "archivoValor4.png");
        ImageIO.write(valor4, "png", archivoValor4);

        File archivoValor5 = new File(path + "archivoValor5.png");
        ImageIO.write(valor5, "png", archivoValor5);

        String result1 = tc.doOCR(valor1);
        String result2 = tc.doOCR(valor2);
        String result3 = tc.doOCR(valor3);
        String result4 = tc.doOCR(valor4);
        String result5 = tc.doOCR(valor5);

        //List<Acta> listActa = new ArrayList<>();
        //acta.setCodigoBarras(result1.trim());
        acta.setPartidoA(Integer.parseInt(result1.trim()));
        acta.setVotoBlanco(Integer.parseInt(result2.trim()));
        acta.setVotoNulo(Integer.parseInt(result3.trim()));
        acta.setVotoImpugnado(Integer.parseInt(result4.trim()));
        acta.setVotoEmitido(Integer.parseInt(result5.trim()));

        //System.out.println(result1.trim()+"-"+result2.trim()+"-"+result3.trim()+"-"+result4.trim()+"-"+result5.trim());
        return acta;
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
    private void abrirRecortarActa() throws IOException{
        App.setRoot(null, "recortarActa");
    }

}
