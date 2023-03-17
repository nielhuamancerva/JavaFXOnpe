package com.mycompany.loging.score.Repository.service;

import org.bson.Document;

/**
 *
 * @author NHuaman
 */
public interface ImageService {
     public void saveImage(Document codigoBarra) throws Exception;
     public Document findImageById(String idImagen) throws Exception;
}
