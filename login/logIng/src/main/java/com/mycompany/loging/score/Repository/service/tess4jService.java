package com.mycompany.loging.score.Repository.service;

/**
 *
 * @author CASSHERN
 */
public interface Tess4jService {

    public void leerCodigoDeBarras(Integer X, Integer Y, Integer H, Integer W) throws Exception;

    public void leerRegionNumeroVotos(Integer X, Integer Y, Integer H, Integer W) throws Exception;

    public void leerNumeroVotos(String nombreVoto,Integer X, Integer Y, Integer H, Integer W) throws Exception;

    public Boolean validarFirma(String signatureFile, Integer X, Integer Y, Integer H, Integer W) throws Exception;

    public void leerObservaciones(Integer X, Integer Y, Integer H, Integer W) throws Exception;
}
