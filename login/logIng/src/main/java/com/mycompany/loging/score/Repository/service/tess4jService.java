/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.loging.score.Repository.service;

import com.google.zxing.NotFoundException;
import com.mycompany.loging.score.model.ActasLeidas;
import java.io.IOException;
import javafx.scene.image.Image;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author CASSHERN
 */
public interface Tess4jService {

    public void leerCodigoDeBarras(Integer X, Integer Y, Integer H, Integer W) throws Exception;

    public void leerRegionNumeroVotos(Integer X, Integer Y, Integer H, Integer W) throws Exception;

    public void leerNumeroVotos(String nombreVoto,Integer X, Integer Y, Integer H, Integer W) throws Exception;

    public Boolean validarFirma(String signatureFile, Integer X, Integer Y, Integer H, Integer W) throws Exception;

    public Image leerObservaciones(Integer X, Integer Y, Integer H, Integer W) throws Exception;
}
