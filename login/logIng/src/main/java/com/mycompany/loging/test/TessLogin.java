package com.mycompany.loging.test;

import com.mycompany.loging.score.negocio.service.NegocioService;
import com.mycompany.loging.score.util.ValidadionesFormularios;
import java.util.Objects;
import org.bson.Document;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TessLogin {
    
    private NegocioService negocioService;
    private ValidadionesFormularios validadionesFormularios;
    
    @Test
    public void testLogin() throws Exception {
        String username = "nhc";
        String passwordField = "nhc";
        String Msg = validadionesFormularios.login(username, passwordField);
        
        boolean resultado=false;
        if (Msg.equals("")) {
            resultado = true;
        }
        assertEquals(false, resultado);
    }
    
    @Test
    public void testConsultaUsuarioDb() throws Exception {
        String username = "nhc";
        String passwordField = "nhc";
        Document login = negocioService.consultaUsuarioDb(username, passwordField);
        
        boolean resultado=false;
        if (Objects.nonNull(login)) {
            resultado = true;
        }
        assertEquals(false, resultado);
    }
}
