/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onpe.com.pe.transmisionscore.core.model;

import java.util.Date;

/**
 *
 * @author CASSHERN
 */
public class TransmisionRecibidas {
    Integer ncodtrama;
    String strama;
    String dfechahora;
    Integer nestado;

    public Integer getNcodtrama() {
        return ncodtrama;
    }

    public void setNcodtrama(Integer ncodtrama) {
        this.ncodtrama = ncodtrama;
    }

    public String getStrama() {
        return strama;
    }

    public void setStrama(String strama) {
        this.strama = strama;
    }

    public String getDfechahora() {
        return dfechahora;
    }

    public void setDfechahora(String dfechahora) {
        this.dfechahora = dfechahora;
    }



    public Integer getNestado() {
        return nestado;
    }

    public void setNestado(Integer nestado) {
        this.nestado = nestado;
    }
}
