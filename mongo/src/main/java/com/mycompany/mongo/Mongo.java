package com.mycompany.mongo;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

/**
 *
 * @author NHuaman
 */
public class Mongo {
    
    public static void main(String[] args) {
        // Crear un objeto MongoClient
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        // Obtener una base de datos
        MongoDatabase database = mongoClient.getDatabase("dbmongo");
        // Obtener la collecion de mongo
        MongoCollection<Document> collection = database.getCollection("user");
        // filtar
        Document user = new Document();
        user = collection.find(Filters.eq("user", "niel")).first();
        System.out.println(user.get("password"));
    }
}
