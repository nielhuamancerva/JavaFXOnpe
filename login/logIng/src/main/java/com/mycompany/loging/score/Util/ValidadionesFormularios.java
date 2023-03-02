/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loging.score.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author NHuaman
 */
public class ValidadionesFormularios {

    public String login(String usuario, String clave) {
        Map<String, String> validadionesFormularioIngreso = new HashMap<>();
        validadionesFormularioIngreso.put("username", usuario);
        validadionesFormularioIngreso.put("password", clave);

        String mensaje = "";
       
        if (validadionesFormularioIngreso.get("username").isEmpty()) {

            if(mensaje.equals("")){
                 mensaje =  mensaje.concat("Campo usuario es requerido");
            }else{
                  mensaje =  mensaje.concat(" y Campo usuario es requerido");
            }
            
        }

        if (validadionesFormularioIngreso.get("password").isEmpty()) {
        
            if(mensaje.equals("")){
                mensaje = mensaje.concat("Campo password es requerido");
            }else{
                mensaje =  mensaje.concat(" y Campo password es requerido");
            }
        }
        
        return mensaje;
    }
}
