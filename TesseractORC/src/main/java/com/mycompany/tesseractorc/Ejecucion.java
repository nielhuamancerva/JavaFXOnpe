package com.mycompany.tesseractorc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author JRoque
 */
public class Ejecucion {
    
        public static Map<String, String> VotosActa = new HashMap();
        
        public static void main(String[] args) throws TesseractException, IOException, Exception{
        
            TesseractOCR tess = new TesseractOCR();
            System.out.println(tess.LecturaTituloActa(VariableGlobales.PATH_ACTAS, "01700187O.PNG", VariableGlobales.Y_COORDENADA_TITULO, VariableGlobales.X_COORDENADA_TITULO, 
                    VariableGlobales.ANCHO_COORDENADA_TITULO, VariableGlobales.ALTO_COORDENADA_TITULO));

            VotosActa = tess.LecturaNumeroVoto(VariableGlobales.PATH_ACTAS, "01700187O.PNG", VariableGlobales.Y_COORDENADA_REGION, VariableGlobales.X_COORDENADA_REGION, 
                    VariableGlobales.ANCHO_COORDENADA_REGION, VariableGlobales.ALTO_COORDENADA_REGION, VariableGlobales.NUMERO_FILAS_REGION, VariableGlobales.ANCHO_COORDENADA_VALOR_VOTO);

            System.out.println(VotosActa.get("bufferedValorVoto0"));
            System.out.println(VotosActa.get("bufferedValorVoto1"));
            System.out.println(VotosActa.get("bufferedValorVoto2"));
            System.out.println(VotosActa.get("bufferedValorVoto3"));
            System.out.println(VotosActa.get("bufferedValorVoto4"));
        }
}