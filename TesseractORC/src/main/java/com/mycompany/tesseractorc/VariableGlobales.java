package com.mycompany.tesseractorc;

import java.util.HashMap;
import java.util.Map;

public class VariableGlobales {
    
    public static String PATH_TESSERACT = "D:\\TESSORC\\tessdata";
    public static String PATH_ACTAS = "D:\\Tools-OCR\\ACTA\\";
    public static Map<String, String> VotosActa = new HashMap();

    public static int Y_COORDENADA_TITULO = 580;
    public static int X_COORDENADA_TITULO = 230;
    public static int ANCHO_COORDENADA_TITULO = 1300;
    public static int ALTO_COORDENADA_TITULO = 180;
    
    public static int NUMERO_FILAS_REGION = 5;

    public static int Y_COORDENADA_REGION = 245;
    public static int X_COORDENADA_REGION = 980;
    public static int ANCHO_COORDENADA_REGION = 1705;
    public static int ALTO_COORDENADA_REGION = 1655;

    public static int Y_COORDENADA_VALOR_VOTO = 0;
    public static int X_COORDENADA_VALOR_VOTO = 980;
    public static int ANCHO_COORDENADA_VALOR_VOTO = 220;
    public static int ALTO_COORDENADA_VALOR_VOTO = 331;
}
