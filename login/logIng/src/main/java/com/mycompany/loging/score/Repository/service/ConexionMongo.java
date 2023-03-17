package com.mycompany.loging.score.Repository.service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import com.mycompany.loging.score.model.Actas;
import org.bson.Document;

/**
 *
 * @author LMedina
 */
public interface ConexionMongo {

    public MongoDatabase conexionMongo() throws Exception;

    public UpdateResult updateDocument(MongoCollection<Document> yy,Document codigoBarra,Document filter,Document update) throws Exception;

    public Document findCollection(String username, String password) throws Exception;

    public MongoCollection<Document> findAllCollecion(String table) throws Exception;

    public Document findActaByCodigoBarra(String codigoBarra) throws Exception;

    public MongoCollection<Document> getCollection(String tableOfMongo) throws Exception;

    public Document findDocumentBy(String filters, String codigoDocument,String Table) throws Exception;

}
