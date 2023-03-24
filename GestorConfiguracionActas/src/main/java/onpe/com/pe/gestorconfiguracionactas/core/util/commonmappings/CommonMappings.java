/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onpe.com.pe.gestorconfiguracionactas.core.util.commonmappings;

import java.io.File;

/**
 *
 * @author NHuaman
 */
public class CommonMappings {

    public static String nameOfFileWithoutExtension(File pathFile) {
        int dotNombreIndex = pathFile.getName().lastIndexOf(".");
        return pathFile.getName().substring(0, dotNombreIndex);
    }

    public static String pathOfFile(File pathFile) {
        int dotIndex = pathFile.getPath().lastIndexOf(pathFile.getName());
        return pathFile.getPath().substring(0, dotIndex);
    }
}
