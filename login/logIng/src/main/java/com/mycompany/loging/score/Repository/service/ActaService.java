package com.mycompany.loging.score.Repository.service;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 *
 * @author NHuaman
 */
public interface ActaService {

    public void saveImage(Document codigoBarra) throws Exception;

    public Document findActaBy(String idActa) throws Exception;

    public MongoCollection<Document> findAllCollection() throws Exception;

    public void updateActa(Document settingDocument) throws Exception;
}
