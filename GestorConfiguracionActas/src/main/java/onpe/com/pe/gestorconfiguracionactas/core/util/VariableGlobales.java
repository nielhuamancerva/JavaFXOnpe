/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onpe.com.pe.gestorconfiguracionactas.core.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import onpe.com.pe.gestorconfiguracionactas.core.model.Component;
import onpe.com.pe.gestorconfiguracionactas.core.model.Modules;

/**
 *
 * @author NHuaman
 */
public class VariableGlobales {

    public static Map<String, String> lecturaActasEnMemoria = new HashMap();

    public static Map<String, String> configuracionActa = new HashMap();

    public static Map<String, String> coordenadasActa = new HashMap();

    public static Map<String, String> identificaActa = new HashMap();

    public static Map<String, String> configuracionTess4j = new HashMap();

    public static String[] arrayNombresModulos;

    public static ArrayList<String> listCount = new ArrayList<>();
    public static Modules[] listModules;
    public static List<String> listComponent;

}
