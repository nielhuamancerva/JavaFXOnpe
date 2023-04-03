package com.mycompany.loging.test;
import com.mycompany.loging.score.Repository.implementacion.Tess4jServiceImpl;
//import static junit.framework.Assert.assertEquals;
//import static org.junit.Assert.assertEquals;
//import org.junit.Test;

/**
 * @author JRoque
 */
public class Tess4jServiceTest {
    
    private Tess4jServiceImpl tess4jServiceImpl;
    private static final int MAX_BLACK_VALUE = 382;
    
    //@Test
    public void testValidarFirma() throws Exception {
        String nombre = "01700187O.TIF";

        boolean resultado = tess4jServiceImpl.validarFirma(nombre, 100, 100, 200, 200);      
        //assertEquals(false, resultado);
    }

    //@Test
    public void testLeerObservaciones() throws Exception {
        tess4jServiceImpl.leerCodigoDeBarras(100, 100, 100, 200);      
    }
}