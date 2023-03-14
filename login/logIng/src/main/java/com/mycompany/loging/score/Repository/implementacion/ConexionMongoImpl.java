package com.mycompany.loging.score.Repository.implementacion;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import com.mycompany.loging.score.Repository.service.ConexionMongo;
import org.bson.Document;

/**
 *
 * @author LMedina
 */
public class ConexionMongoImpl implements ConexionMongo {

    private MongoDatabase mongoDatabase;

    @Override
    public MongoDatabase conexionMongo() throws Exception {
        // Crear un objeto MongoClient
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        // Obtener una base de datos
        mongoDatabase = mongoClient.getDatabase("dbmongo");
        return mongoDatabase;
        // Obtener la collecion de mongo

    }

    @Override
    public Document findCollection(String username, String password) {

        // Obtener la collecion de mongo
        MongoCollection<Document> collection = mongoDatabase.getCollection("user");
        Document ss = collection.find(Filters.and(
                Filters.eq("usuario", username),
                Filters.eq("password", password))).first();

        return ss;

    }

    @Override
    public MongoCollection<Document> findAllCollecion(String username) throws Exception {

        return mongoDatabase.getCollection(username);
    }

    @Override
    public Document findActaByCodigoBarra(String codigoBarra) throws Exception {
        // Obtener la collecion de mongo

        MongoCollection<Document> collection = mongoDatabase.getCollection("actas");
        return collection.find(Filters.eq("acta", codigoBarra)).first();
    }

    @Override
    public UpdateResult saveConexionMongo(Document codigoBarra) throws Exception {
        MongoCollection<Document> collection = mongoDatabase.getCollection("actas");
        
        Document filter = new Document("actas",codigoBarra);
        Document update = new Document(codigoBarra);
        return collection.updateOne(filter,update);
    }

}
