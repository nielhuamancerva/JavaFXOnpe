package onpe.com.pe.transmisionscore.core.util.constanst;

import onpe.com.pe.transmisionscore.core.model.Actas;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author NHuaman
 */
public class VariableGlobales {

    public static Map<String, String> lecturaActasEnMemoria = new HashMap();
    public static Map<String, String> configuracionActa = new HashMap();
    public static String nombreDelArchivoProcesado = "";
    public static Actas actasLeida = new Actas();
    public static String viewImage = "";
}
