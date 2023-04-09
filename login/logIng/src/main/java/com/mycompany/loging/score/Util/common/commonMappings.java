package com.mycompany.loging.score.util.common;

import com.mycompany.loging.score.model.ActasLeidas;
import com.mycompany.loging.score.util.constanst.VariableGlobals;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

public class CommonMappings {

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
        File file = new File(VariableGlobals.lecturaActasEnMemoria.get("fileNamePathOriginal"));
        // Lee el archivo en un array de bytes
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fileInputStream.read(bytes);
        // Codifica los bytes en Base64
        VariableGlobals.actasLeida.getImagen().setImagen(Base64.getEncoder().encodeToString(bytes));
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String calcuteFechaNow() {
        LocalTime horaSistema = LocalTime.now();
        String fechaFormatoCadena = "";
        int hora;
        int minutos;
        
        hora = horaSistema.getHour();
        minutos = horaSistema.getMinute();
        /*--------------------*/
        Date fehaActual = new Date();
        SimpleDateFormat formatoAnio = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatoMes = new SimpleDateFormat("MMMM");
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fehaActual);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        String anio = formatoAnio.format(fehaActual);
        String mes = formatoMes.format(fehaActual);
        fechaFormatoCadena = "del " + dia + " de " + mes + " de " + anio + ", se inicio el ACTO DE ESCRUTINIO";
        return "nie";
    }
}
