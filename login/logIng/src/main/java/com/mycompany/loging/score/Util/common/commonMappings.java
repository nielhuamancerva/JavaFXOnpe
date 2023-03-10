package com.mycompany.loging.score.util.common;

import java.io.File;

public class commonMappings {

    public static String nameOfFileWithoutExtension(File pathFile) {
        int dotNombreIndex = pathFile.getName().lastIndexOf(".");
        return pathFile.getName().substring(0, dotNombreIndex);
    }

    public static String pathOfFile(File pathFile) {
        int dotIndex = pathFile.getPath().lastIndexOf(pathFile.getName());
        return pathFile.getPath().substring(0, dotIndex);
    }
}
