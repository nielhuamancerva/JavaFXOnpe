package com.mycompany.tesseractorc;

import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.ImageHelper;

/**
 *
 * @author JRoque
 */
public class TesseractOCR {

   
    
    public static String LecturaTituloActa(String direccionActas, String nombre, int x, int y, int ancho, int largo) throws Exception {
        Tesseract tc = new Tesseract();
        tc.setOcrEngineMode(2);
        tc.setTessVariable("user_defined_dpi", "70");
        tc.setDatapath(VariableGlobales.PATH_TESSERACT);

        File imageFile = new File(direccionActas + nombre);
        BufferedImage image = ImageIO.read(imageFile);
        BufferedImage bufferedRegionLista = image.getSubimage(x, y, ancho, largo);
        String texto = tc.doOCR(preprocesarImagenTitulo(bufferedRegionLista, "bufferedTituloActa", direccionActas, 2.9f, 10f));
        return texto;
    }
    
    public static Map<String, String> VotosActa;
    
    public static Map<String, String> LecturaNumeroVoto(String direccionActas, String nombre, int x, int y, int ancho, int largo, int numeroFilas, int anchoValorVoto) throws Exception {       
        File imageFile = new File(direccionActas + nombre);
        BufferedImage image = ImageIO.read(imageFile);
        
        BufferedImage bufferedRegionLista = image.getSubimage(x, y, ancho, largo);        //REGION [PARTIDO POLITICO, NUMERO VOTOS]
        return CortarImagen(bufferedRegionLista, direccionActas, numeroFilas, anchoValorVoto);
    }
    
    private static Map<String, String> CortarImagen(BufferedImage regionLista, String path, int numeroFilas, int anchoValorVoto) throws Exception {
            Tesseract tc = new Tesseract();
            tc.setOcrEngineMode(2);
            tc.setTessVariable("user_defined_dpi", "70");
            tc.setDatapath(VariableGlobales.PATH_TESSERACT);
            tc.setTessVariable("tessedit_char_whitelist", "0123456789");
        
            VotosActa = new HashMap();
            
            int numeroMaximoFila = numeroFilas;
            int altoImagen = regionLista.getHeight();
            int anchoImagen = regionLista.getWidth();
            int y=0;
            int altoImagenRecortada = (altoImagen/numeroMaximoFila);
            
            for(int i=0;i<=(numeroMaximoFila-1);i++){
                BufferedImage bufferedOrganizacion = regionLista.getSubimage(0, y, anchoImagen, altoImagenRecortada);
                //tc.doOCR(preprocesarImagen2(bufferedOrganizacion, "xxxxx"+i, path));
                
                BufferedImage bufferedOrganizacionCortada = bufferedOrganizacion.getSubimage(anchoImagen-anchoValorVoto, 0, anchoValorVoto, altoImagenRecortada);
                String textoPartidoA = tc.doOCR(preprocesarImagenRegionVoto(bufferedOrganizacionCortada, "bufferedValorVoto"+i, path, 1.5f, 10f));
                
                y+=(altoImagenRecortada);
                
                String[] numeros = textoPartidoA.trim().split("\n");
                VotosActa.put("bufferedValorVoto"+i, numeros[0]);
            }
        return VotosActa;
    }
       
    private static BufferedImage preprocesarImagenTitulo(BufferedImage bufferedImagen, String nombre, String path, float scaleFactor, float offset) throws IOException {
        bufferedImagen = ImageHelper.convertImageToGrayscale(bufferedImagen); // Convertir la imagen a escala de grises
        RescaleOp rescale = new RescaleOp(scaleFactor, offset, null);
        BufferedImage contraste = rescale.filter(bufferedImagen, null);
        File archivoValor55 = new File(path + nombre + ".png");
        ImageIO.write(contraste, "png", archivoValor55);
        return contraste;
    }
    
    private static BufferedImage preprocesarImagenRegionVoto(BufferedImage bufferedImagen, String nombre, String path, float scaleFactor, float offset) throws IOException {
        bufferedImagen = ImageHelper.convertImageToGrayscale(bufferedImagen); // Convertir la imagen a escala de grises
        RescaleOp rescale = new RescaleOp(scaleFactor, offset, null);
        BufferedImage contraste = rescale.filter(bufferedImagen, null);
        File archivoValor55 = new File(path + nombre + ".png");
        ImageIO.write(contraste, "png", archivoValor55);
        return contraste;
    }
}
