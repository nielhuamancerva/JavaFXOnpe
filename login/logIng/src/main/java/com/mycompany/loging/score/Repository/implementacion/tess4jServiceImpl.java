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
import com.mycompany.loging.score.Repository.service.tess4jService;
import com.mycompany.loging.score.model.ActasLeidas;
import com.mycompany.loging.score.util.VariableGlobales;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author CASSHERN
 */
public class tess4jServiceImpl implements tess4jService {

        private static final int MAX_BLACK_VALUE = 382; // ((255 * 3) / 2) rounded down
    
    @Override
    public ActasLeidas leerDocumentoRegion() throws TesseractException, IOException, NotFoundException, Exception {

            ActasLeidas acta = new ActasLeidas();
            String pathTesseract = VariableGlobales.lecturaActasEnMemoria.get("pathTesseract");
            String path = VariableGlobales.lecturaActasEnMemoria.get("path");
            String nombre = VariableGlobales.lecturaActasEnMemoria.get("fileName");

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
            /*leerNumeroVotos(regionLista, path, pathTesseract);*/
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
   

           
            /*acta.setFirmaPresidente(validarFirma(path + "FI1-" + nombreSinExtension + ".png", MAX_BLACK_VALUE));
            acta.setFirmaSecretario(validarFirma(path + "FI2-" + nombreSinExtension + ".png", MAX_BLACK_VALUE));
            acta.setFirmaTercerMiembro(validarFirma(path + "FI3-" + nombreSinExtension + ".png", MAX_BLACK_VALUE));*/

            //     System.out.println(gson.toJson(acta));
            return acta;
 
    }

    @Override
    public String leerCodigoDeBarras() throws Exception{
    
        File file = new File( VariableGlobales.lecturaActasEnMemoria.get("fileName"));

        BufferedImage image = ImageIO.read(file);
        // create binary bitmap from image
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
        // set decoding hints
        MultiFormatReader reader = new MultiFormatReader();
        java.util.Map<DecodeHintType, Object> hints = new java.util.EnumMap<>(DecodeHintType.class);
        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        com.google.zxing.Result result = reader.decode(bitmap, hints);
        acta.setCodigoBarras(result.getText());
        return file.toURI().toString();
    }

    @Override
    public ActasLeidas validarFirma(String signatureFile) {
        
        
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
                        if (blackPixels >= MAX_BLACK_VALUE) {
                            //System.out.println(true);
                            return new ActasLeidas();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(false);
         return new ActasLeidas();
    }

}
