/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.loging.score.Repository.implementacion;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.Tesseract;
import com.mycompany.loging.score.Repository.service.Tess4jService;
import com.mycompany.loging.score.util.constanst.VariableGlobales;
import java.awt.image.RescaleOp;
import net.sourceforge.tess4j.util.ImageHelper;

/**
 *
 * @author CASSHERN
 */
public class Tess4jServiceImpl implements Tess4jService {

    private static final int MAX_BLACK_VALUE = 382; // ((255 * 3) / 2) rounded down

    @Override
    public void leerCodigoDeBarras(Integer X, Integer Y, Integer H, Integer W) throws Exception {

        File imageFile = new File(VariableGlobales.lecturaActasEnMemoria.get("fileNamePathOriginal"));
        BufferedImage imageOriginal = ImageIO.read(imageFile);

        BufferedImage imageCodBarras = imageOriginal.getSubimage(X, Y, H, W);

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
        VariableGlobales.lecturaActasEnMemoria.put("codigoBarra", archivoCodigoBarras.toURI().toString());

    }

    @Override
    public void leerNumeroVotos(String nombreVoto, Integer X, Integer Y, Integer H, Integer W) throws Exception {
        File imageFile = new File(VariableGlobales.lecturaActasEnMemoria.get("leerRegionNumeroVotos"));
        BufferedImage image = ImageIO.read(imageFile);

        Tesseract tc = new Tesseract();
        tc.setOcrEngineMode(2);
        tc.setTessVariable("user_defined_dpi", "2400");
        tc.setDatapath(VariableGlobales.lecturaActasEnMemoria.get("pathTesseract"));
        tc.setTessVariable("tessedit_char_whitelist", "0123456789");

        BufferedImage valor1 = image.getSubimage(X, Y, H, W);
        File archivoValor1 = new File(VariableGlobales.lecturaActasEnMemoria.get("fileNamePath") + nombreVoto + ".png");
        ImageIO.write(valor1, "png", archivoValor1);

        valor1 = ImageHelper.convertImageToGrayscale(valor1);
        RescaleOp rescale = new RescaleOp(1.2f, 15, null);

        BufferedImage valor5Final = rescale.filter(valor1, null);

        String result5 = tc.doOCR(valor5Final);

        VariableGlobales.lecturaActasEnMemoria.put(nombreVoto, result5);

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
                    VariableGlobales.lecturaActasEnMemoria.get("fileNamePath") + signatureFile));
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
    public Image leerObservaciones(Integer X, Integer Y, Integer H, Integer W) throws Exception {

        File imageFile = new File(VariableGlobales.lecturaActasEnMemoria.get("fileNamePathOriginal"));
        BufferedImage image = ImageIO.read(imageFile);
        BufferedImage regionObservaciones = image.getSubimage(X, Y, H, W); //REGION OBSERVACION
        File archivoRegionObservaciones = new File(VariableGlobales.lecturaActasEnMemoria.get("fileNamePath") + "OBS-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png");
        ImageIO.write(regionObservaciones, "png", archivoRegionObservaciones);
        VariableGlobales.lecturaActasEnMemoria.put("observaciones", archivoRegionObservaciones.toURI().toString());
        Image img = new Image(archivoRegionObservaciones.toURI().toString());
        return img;
    }

    @Override
    public void leerRegionNumeroVotos(Integer X, Integer Y, Integer H, Integer W) throws Exception {
        File imageFile = new File(VariableGlobales.lecturaActasEnMemoria.get("fileNamePathOriginal"));
        BufferedImage image = ImageIO.read(imageFile);
        BufferedImage regionLista = image.getSubimage(X, Y, H, W);

        File archivoRegionLista = new File(VariableGlobales.lecturaActasEnMemoria.get("fileNamePath") + "REG-" + VariableGlobales.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png");
        ImageIO.write(regionLista, "png", archivoRegionLista);
        VariableGlobales.lecturaActasEnMemoria.put("leerRegionNumeroVotos", archivoRegionLista.getPath());
        VariableGlobales.lecturaActasEnMemoria.put("leerRegionNumeroVotosUri", archivoRegionLista.toURI().toString());

    }

}
