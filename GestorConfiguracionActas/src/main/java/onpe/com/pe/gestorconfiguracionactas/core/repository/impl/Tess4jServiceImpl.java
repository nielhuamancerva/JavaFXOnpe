/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onpe.com.pe.gestorconfiguracionactas.core.repository.impl;

import java.io.File;
import net.sourceforge.tess4j.Tesseract;
import onpe.com.pe.gestorconfiguracionactas.core.repository.service.Tess4jService;
import onpe.com.pe.gestorconfiguracionactas.core.util.VariableGlobales;
import onpe.com.pe.gestorconfiguracionactas.core.util.constants.Constants;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.util.ImageHelper;

/**
 *
 * @author NHuaman
 */
public class Tess4jServiceImpl implements Tess4jService {

    @Override
    public String readTitleActa(Integer x, Integer y, Integer h, Integer w) throws Exception {
        Tesseract tc = new Tesseract();
        tc.setOcrEngineMode(2);
        tc.setTessVariable("user_defined_dpi", "70");
        tc.setDatapath(Constants.PATH_TESSERACT);

        File imageFile = new File(VariableGlobales.lecturaActasEnMemoria.get("fileNamePathOriginal"));
        BufferedImage image = ImageIO.read(imageFile);
        BufferedImage bufferedRegionLista = image.getSubimage(x, y, h, w);
        String texto = tc.doOCR(preprocesarImagenTitulo(bufferedRegionLista, "bufferedTituloActa", VariableGlobales.lecturaActasEnMemoria.get("fileNamePath"), 2.9f, 10f));
        return texto;
    }

    private static BufferedImage preprocesarImagenTitulo(BufferedImage bufferedImagen, String nombre, String path, float scaleFactor, float offset) throws IOException {
        bufferedImagen = ImageHelper.convertImageToGrayscale(bufferedImagen); // Convertir la imagen a escala de grises
        RescaleOp rescale = new RescaleOp(scaleFactor, offset, null);
        BufferedImage contraste = rescale.filter(bufferedImagen, null);
        File archivoValor55 = new File(path + nombre + ".png");
        ImageIO.write(contraste, "png", archivoValor55);
        return contraste;
    }

}
