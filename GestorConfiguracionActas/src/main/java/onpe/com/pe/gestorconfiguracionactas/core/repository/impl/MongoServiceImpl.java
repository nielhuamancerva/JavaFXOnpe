/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onpe.com.pe.gestorconfiguracionactas.core.repository.impl;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import onpe.com.pe.gestorconfiguracionactas.core.repository.service.MongoService;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author NHuaman
 */
public class MongoServiceImpl implements MongoService {

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
    public UpdateResult updateDocument(Document filter, Document update, String tableOfMongo) throws Exception {
        MongoCollection<Document> collection = mongoDatabase.getCollection(tableOfMongo);
        return collection.updateOne(filter, update);
    }

    @Override
    public FindIterable<Document> findCollection(String Table) throws Exception {
        MongoCollection<Document> collection = mongoDatabase.getCollection(Table);
        return collection.find();
    }

    @Override
    public Document findDocumentBy(String filters, String codigoDocument, String Table) throws Exception {
        MongoCollection<Document> collection = mongoDatabase.getCollection(Table);
        return collection.find(Filters.eq(filters, codigoDocument)).first();
    }

    @Override
    public InsertOneResult saveDocument(Document document, String Table) throws Exception {
        MongoCollection<Document> collection = mongoDatabase.getCollection(Table);
        return collection.insertOne(document);
    }

    @Override
    public Document findDocumentByMultiple(Bson filters, String Table) throws Exception {
        MongoCollection<Document> collection = mongoDatabase.getCollection(Table);
        return collection.find(filters).first();
    }

    @Override
    public DeleteResult deleteOneDocument(Bson filters, String Table) throws Exception {
          MongoCollection<Document> collection = mongoDatabase.getCollection(Table);
        return collection.deleteOne(filters);
    }

}
