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
import com.mycompany.loging.score.util.constanst.VariableGlobals;
import java.awt.image.RescaleOp;
import java.util.HashMap;
import net.sourceforge.tess4j.util.ImageHelper;

public class Tess4jServiceImpl implements Tess4jService {

    private static final int MAX_BLACK_VALUE = 382; // ((255 * 3) / 2) rounded down

    @Override
    public void leerCodigoDeBarras(Integer X, Integer Y, Integer H, Integer W) throws Exception {

        File imageFile = new File(VariableGlobals.lecturaActasEnMemoria.get("fileNamePathOriginal"));
        BufferedImage imageOriginal = ImageIO.read(imageFile);

        BufferedImage imageCodBarras = imageOriginal.getSubimage(X, Y, H, W);

        File archivoCodigoBarras = new File(VariableGlobals.lecturaActasEnMemoria.get("fileNamePath") + "BAR-" + VariableGlobals.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png");
        ImageIO.write(imageCodBarras, "png", archivoCodigoBarras);
        VariableGlobals.lecturaActasEnMemoria.put("codigoBarra", archivoCodigoBarras.toURI().toString());

        BufferedImage image = ImageIO.read(archivoCodigoBarras);
        // create binary bitmap from image
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
        // set decoding hints
        MultiFormatReader reader = new MultiFormatReader();
        java.util.Map<DecodeHintType, Object> hints = new java.util.EnumMap<>(DecodeHintType.class);
        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        com.google.zxing.Result result = reader.decode(bitmap, hints);

        VariableGlobals.lecturaActasEnMemoria.put("codigoBarraResponse", result.getText());
        VariableGlobals.lecturaActasEnMemoria.put("codigoBarra", archivoCodigoBarras.toURI().toString());

    }

    @Override
    public void leerNumeroVotos(String nombreVoto, Integer X, Integer Y, Integer H, Integer W) throws Exception {
        File fileImageRegionVotos = new File(VariableGlobals.lecturaActasEnMemoria.get("leerRegionNumeroVotos"));
        BufferedImage image = ImageIO.read(fileImageRegionVotos);
        BufferedImage valor1 = image.getSubimage(X, Y, H, W);

        Tesseract tc = new Tesseract();
        tc.setOcrEngineMode(2);
        tc.setTessVariable("user_defined_dpi", "2400");
        tc.setDatapath(VariableGlobals.lecturaActasEnMemoria.get("pathTesseract"));
        tc.setTessVariable("tessedit_char_whitelist", "0123456789");

        File archivoValor1 = new File(VariableGlobals.lecturaActasEnMemoria.get("fileNamePath") + nombreVoto + ".png");
        ImageIO.write(valor1, "png", archivoValor1);

        valor1 = ImageHelper.convertImageToGrayscale(valor1);
        RescaleOp rescale = new RescaleOp(1.2f, 15, null);

        BufferedImage valor5Final = rescale.filter(valor1, null);

        String result5 = tc.doOCR(valor5Final);

        VariableGlobals.lecturaActasEnMemoria.put(nombreVoto, result5);

    }

    @Override
    public Boolean validarFirma(String signatureFile, Integer X, Integer Y, Integer H, Integer W) throws Exception {
        File imageFile = new File(VariableGlobals.lecturaActasEnMemoria.get("fileNamePathOriginal"));
        BufferedImage image = ImageIO.read(imageFile);

        BufferedImage Firma = image.getSubimage(X, Y, H, W);

        File archivoFirma = new File(VariableGlobals.lecturaActasEnMemoria.get("fileNamePath") + signatureFile+ ".png");
        ImageIO.write(Firma, "png", archivoFirma);
        VariableGlobals.lecturaActasEnMemoria.put(signatureFile, archivoFirma.toURI().toString());

        try {
            BufferedImage imageFinal = ImageIO.read(archivoFirma);
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
        return Boolean.FALSE;
    }

    @Override
    public void leerObservaciones(Integer X, Integer Y, Integer H, Integer W) throws Exception {
        File imageFile = new File(VariableGlobals.lecturaActasEnMemoria.get("fileNamePathOriginal"));
        BufferedImage image = ImageIO.read(imageFile);
        BufferedImage regionObservaciones = image.getSubimage(X, Y, H, W); //REGION OBSERVACION
        File archivoRegionObservaciones = new File(VariableGlobals.lecturaActasEnMemoria.get("fileNamePath") + "OBS-" + VariableGlobals.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png");
        ImageIO.write(regionObservaciones, "png", archivoRegionObservaciones);
        VariableGlobals.lecturaActasEnMemoria.put("observaciones", archivoRegionObservaciones.toURI().toString());
    }

    @Override
    public void leerRegionNumeroVotos(Integer X, Integer Y, Integer H, Integer W) throws Exception {
        File imageFile = new File(VariableGlobals.lecturaActasEnMemoria.get("fileNamePathOriginal"));
        BufferedImage image = ImageIO.read(imageFile);
        BufferedImage regionLista = image.getSubimage(X, Y, H, W);

        File archivoRegionLista = new File(VariableGlobals.lecturaActasEnMemoria.get("fileNamePath") + "REG-" + VariableGlobals.lecturaActasEnMemoria.get("fileNameSinExtension") + ".png");
        ImageIO.write(regionLista, "png", archivoRegionLista);

        VariableGlobals.lecturaActasEnMemoria.put("leerRegionNumeroVotos", archivoRegionLista.getPath());
        VariableGlobals.lecturaActasEnMemoria.put("leerRegionNumeroVotosUri", archivoRegionLista.toURI().toString());

        Tesseract tc = new Tesseract();
        tc.setOcrEngineMode(2);
        tc.setTessVariable("user_defined_dpi", "70");
        tc.setDatapath(VariableGlobals.lecturaActasEnMemoria.get("pathTesseract"));
        tc.setTessVariable("tessedit_char_whitelist", "0123456789");

        int numeroMaximoFila = 7;
        int altoImagen = regionLista.getHeight();
        int anchoImagen = regionLista.getWidth();
        int y = 0;
        int altoImagenRecortada = (altoImagen / numeroMaximoFila);

        for (int i = 0; i <= (numeroMaximoFila - 1); i++) {
            BufferedImage bufferedOrganizacion = regionLista.getSubimage(0, y, anchoImagen, altoImagenRecortada);
            //tc.doOCR(preprocesarImagen2(bufferedOrganizacion, "xxxxx"+i, path));
            // coordenada imagen (100,200,300,400)                                                     220, 0  220
            BufferedImage bufferedOrganizacionCortada = bufferedOrganizacion.getSubimage(anchoImagen - 200, 0, 170, altoImagenRecortada);
            String textoPartidoA = tc.doOCR(preprocesarImagenRegionVoto(bufferedOrganizacionCortada, "bufferedValorVoto" + i, VariableGlobals.lecturaActasEnMemoria.get("fileNamePath"), 1.61f, 1.9f));

            y += (altoImagenRecortada);

            String[] numeros = textoPartidoA.trim().split("\n");
            VariableGlobals.lecturaActasEnMemoria.put("bufferedValorVoto" + i, numeros[0]);
            //System.out.println("bufferedValorVoto" + i+"  "+ numeros[0]);
        }
    }

    private static BufferedImage preprocesarImagenRegionVoto(BufferedImage bufferedImagen, String nombre, String path, float scaleFactor, float offset) throws IOException {
        bufferedImagen = ImageHelper.convertImageToGrayscale(bufferedImagen); // Convertir la imagen a escala de grises
        RescaleOp rescale = new RescaleOp(scaleFactor, offset, null);
        BufferedImage contraste = rescale.filter(bufferedImagen, null);
        File archivoValor55 = new File(path + nombre + ".png");
        ImageIO.write(contraste, "png", archivoValor55);
        return contraste;
    }

    @Override
    public void leerHora(String nameReadTime, Integer X, Integer Y, Integer H, Integer W) throws Exception {
        File imageFile = new File(VariableGlobals.lecturaActasEnMemoria.get("fileNamePathOriginal"));
        BufferedImage image = ImageIO.read(imageFile);
        BufferedImage regionObservaciones = image.getSubimage(X, Y, H, W); //REGION OBSERVACION
        File archivoRegionObservaciones = new File(VariableGlobals.lecturaActasEnMemoria.get("fileNamePath") + nameReadTime+ ".png");
        ImageIO.write(regionObservaciones, "png", archivoRegionObservaciones);
        VariableGlobals.lecturaActasEnMemoria.put(nameReadTime, archivoRegionObservaciones.toURI().toString());
    }

}
