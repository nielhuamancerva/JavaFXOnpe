package com.mycompany.loging.score.util.constanst;

import com.mycompany.loging.score.model.Actas;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author NHuaman
 */
public class VariableGlobales {

    public static Map<String, String> lecturaActasEnMemoria = new HashMap();
    public static String nombreDelArchivoProcesado = "";
    public static Actas actasLeida = new Actas();
}
