/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.loging.score.Repository.implementacion;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import static com.mycompany.loging.endpoint.dashboard.LeerActasController.acta;
import static com.mycompany.loging.endpoint.dashboard.LeerActasController.leerNumeroVotos;
import com.mycompany.loging.score.model.ActasLeidas;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import com.mycompany.loging.score.Repository.service.Tess4jService;
import com.mycompany.loging.score.util.constanst.VariableGlobales;

/**
 *
 * @author CASSHERN
 */
public class Tess4jServiceImpl implements Tess4jService {

    private static final int MAX_BLACK_VALUE = 382; // ((255 * 3) / 2) rounded down

    @Override
    public Image leerCodigoDeBarras() throws Exception {

        File imageFile = new File(VariableGlobales.lecturaActasEnMemoria.get("fileNamePathOriginal"));
        BufferedImage imageOriginal = ImageIO.read(imageFile);

        BufferedImage imageCodBarras = imageOriginal.getSubimage(1460, 110, 620, 140);

        File archivoCodigoBarras = new File(VariableGlobales.lecturaActasEnMemoria.get("fileNamePath") + "BAR-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png");
        ImageIO.write(imageCodBarras, "png", archivoCodigoBarras);
        VariableGlobales.lecturaActasEnMemoria.put("codigoBarra", archivoCodigoBarras.toURI().toString());

        File file = new File(VariableGlobales.lecturaActasEnMemoria.get("fileNamePath") + "BAR-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png");

        BufferedImage image = ImageIO.read(file);
        // create binary bitmap from image
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
        // set decoding hints
        MultiFormatReader reader = new MultiFormatReader();
        java.util.Map<DecodeHintType, Object> hints = new java.util.EnumMap<>(DecodeHintType.class);
        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        com.google.zxing.Result result = reader.decode(bitmap, hints);

        VariableGlobales.lecturaActasEnMemoria.put("codigoBarraResponse", result.getText());
        //acta.setCodigoBarras(result.getText());

        VariableGlobales.lecturaActasEnMemoria.put("codigoBarra", archivoCodigoBarras.toURI().toString());
        Image img = new Image(archivoCodigoBarras.toURI().toString());
        return img;
    }

    @Override
    public Image leerNumeroVotos() throws Exception {
        File imageFile = new File(VariableGlobales.lecturaActasEnMemoria.get("fileNamePathOriginal"));
        BufferedImage image = ImageIO.read(imageFile);
        BufferedImage regionLista = image.getSubimage(210, 880, 1800, 1780);

        File archivoRegionLista = new File(VariableGlobales.lecturaActasEnMemoria.get("fileNamePath") + "REG-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png");
        ImageIO.write(regionLista, "png", archivoRegionLista);

        Tesseract tc = new Tesseract();
        tc.setOcrEngineMode(2);
        //tc.setPageSegMode(PSM.SINGLE_CHAR);
        tc.setTessVariable("user_defined_dpi", "2400");
        tc.setDatapath(VariableGlobales.lecturaActasEnMemoria.get("pathTesseract"));
        tc.setTessVariable("tessedit_char_whitelist", "0123456789");

        BufferedImage valor1 = regionLista.getSubimage(1514, 120, 200, 240);
        BufferedImage valor2 = regionLista.getSubimage(1514, 440, 200, 240);
        BufferedImage valor3 = regionLista.getSubimage(1514, 780, 200, 240);
        BufferedImage valor4 = regionLista.getSubimage(1514, 1120, 200, 240);
        BufferedImage valor5 = regionLista.getSubimage(1514, 1460, 200, 240);

        File archivoValor1 = new File(VariableGlobales.lecturaActasEnMemoria.get("fileNamePath") + "archivoValor1.png");
        ImageIO.write(valor1, "png", archivoValor1);

        File archivoValor2 = new File(VariableGlobales.lecturaActasEnMemoria.get("fileNamePath") + "archivoValor2.png");
        ImageIO.write(valor2, "png", archivoValor2);

        File archivoValor3 = new File(VariableGlobales.lecturaActasEnMemoria.get("fileNamePath") + "archivoValor3.png");
        ImageIO.write(valor3, "png", archivoValor3);

        File archivoValor4 = new File(VariableGlobales.lecturaActasEnMemoria.get("fileNamePath") + "archivoValor4.png");
        ImageIO.write(valor4, "png", archivoValor4);

        File archivoValor5 = new File(VariableGlobales.lecturaActasEnMemoria.get("fileNamePath") + "archivoValor5.png");
        ImageIO.write(valor5, "png", archivoValor5);

        String result1 = tc.doOCR(valor1);
        String result2 = tc.doOCR(valor2);
        String result3 = tc.doOCR(valor3);
        String result4 = tc.doOCR(valor4);
        String result5 = tc.doOCR(valor5);

        VariableGlobales.lecturaActasEnMemoria.put("Region1", result1);
        VariableGlobales.lecturaActasEnMemoria.put("Region2", result2);
        VariableGlobales.lecturaActasEnMemoria.put("Region3", result3);
        VariableGlobales.lecturaActasEnMemoria.put("Region4", result4);
        VariableGlobales.lecturaActasEnMemoria.put("Region5", result5);

        Image img = new Image(archivoRegionLista.toURI().toString());
        return img;
    }

    @Override
    public Boolean validarFirma(String signatureFile, Integer X, Integer Y, Integer H, Integer W) throws Exception {
        File imageFile = new File(VariableGlobales.lecturaActasEnMemoria.get("fileNamePathOriginal"));
        BufferedImage image = ImageIO.read(imageFile);

        BufferedImage Firma = image.getSubimage(X, Y, H, W);

        //--- archivoFirma1
        File archivoFirma = new File(
                    VariableGlobales.lecturaActasEnMemoria.get("fileNamePath") + signatureFile);
        ImageIO.write(Firma, "png", archivoFirma);
        VariableGlobales.lecturaActasEnMemoria.put(signatureFile, archivoFirma.toURI().toString());

        try {
            BufferedImage imageFinal = ImageIO.read(new File(
                    VariableGlobales.lecturaActasEnMemoria.get("fileNamePath") +signatureFile));
            int bytesPerPixel = imageFinal.getColorModel().getPixelSize() / 8;

            byte[] pixels = ((DataBufferByte) imageFinal.getRaster().getDataBuffer()).getData();
            int blackPixels = 0;
            for (int h = 0; h < imageFinal.getHeight(); h++) {
                int currentLine = h * imageFinal.getWidth() * bytesPerPixel;
                for (int w = 0; w < (imageFinal.getWidth() * bytesPerPixel); w += bytesPerPixel) {
                    int blue = pixels[currentLine + w] & 0xFF;
                    int green = pixels[currentLine + w + 1] & 0xFF;
                    int red = pixels[currentLine + w + 2] & 0xFF;

                    if (blue + green + red <= MAX_BLACK_VALUE) {
                        blackPixels++;
                        if (blackPixels >= MAX_BLACK_VALUE) {
                            return Boolean.TRUE;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(false);
        return Boolean.FALSE;
    }

    @Override
    public Image leerObservaciones() throws Exception {

        File imageFile = new File(VariableGlobales.lecturaActasEnMemoria.get("fileNamePathOriginal"));
        BufferedImage image = ImageIO.read(imageFile);
        BufferedImage regionObservaciones = image.getSubimage(210, 2640, 1800, 300); //REGION OBSERVACION
        File archivoRegionObservaciones = new File(VariableGlobales.lecturaActasEnMemoria.get("fileNamePath") + "OBS-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png");
        ImageIO.write(regionObservaciones, "png", archivoRegionObservaciones);
        VariableGlobales.lecturaActasEnMemoria.put("observaciones", archivoRegionObservaciones.toURI().toString());
        Image img = new Image(archivoRegionObservaciones.toURI().toString());
        return img;
    }

}
