/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.loging.score.Repository.service;

import com.google.zxing.NotFoundException;
import com.mycompany.loging.score.model.ActasLeidas;
import java.io.IOException;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author CASSHERN
 */
public interface tess4jService {
     public ActasLeidas leerDocumentoRegion() throws TesseractException, IOException, NotFoundException, Exception ;
     
     public String leerCodigoDeBarras() throws Exception;
     
     public ActasLeidas validarFirma(String path);
}
