package com.mycompany.loging.score.util.constanst;

import com.mycompany.loging.score.model.Actas;
import com.mycompany.loging.score.model.Modules;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author NHuaman
 */
public class VariableGlobals {

    public static Map<String, String> lecturaActasEnMemoria = new HashMap();
    public static Map<String, String> configuracionActa = new HashMap();
    public static String nombreDelArchivoProcesado = "";
    public static Actas actasLeida = new Actas();
    public static String viewImage = "";
    public static List<String> list;
    public static Modules[] listModules;
}
