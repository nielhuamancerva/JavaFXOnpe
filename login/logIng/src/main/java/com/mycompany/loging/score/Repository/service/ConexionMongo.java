package com.mycompany.loging.score.Repository.service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author LMedina
 */
public interface ConexionMongo {

    public MongoDatabase conexionMongo() throws Exception;

    public Document findCollection(String username, String password) throws Exception;

    public MongoCollection<Document> findAllCollecion(String table) throws Exception;

    public Document findActaByCodigoBarra(String codigoBarra) throws Exception;
}
