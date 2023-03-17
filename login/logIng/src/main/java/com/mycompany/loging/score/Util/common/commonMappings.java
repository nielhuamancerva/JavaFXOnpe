package com.mycompany.loging.score.util.common;

import com.mycompany.loging.score.util.constanst.VariableGlobales;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

public class commonMappings {

    public static String nameOfFileWithoutExtension(File pathFile) {
        int dotNombreIndex = pathFile.getName().lastIndexOf(".");
        return pathFile.getName().substring(0, dotNombreIndex);
    }

    public static String pathOfFile(File pathFile) {
        int dotIndex = pathFile.getPath().lastIndexOf(pathFile.getName());
        return pathFile.getPath().substring(0, dotIndex);
    }

    public static String convertFileToBase64() throws FileNotFoundException, IOException {
        // Selecciona el archivo a convertir
        File file = new File(VariableGlobales.lecturaActasEnMemoria.get("fileNamePathOriginal"));
        // Lee el archivo en un array de bytes
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fileInputStream.read(bytes);
        // Codifica los bytes en Base64
         VariableGlobales.actasLeida.getImagen().setImagen(Base64.getEncoder().encodeToString(bytes));
        return Base64.getEncoder().encodeToString(bytes);
    }
}
