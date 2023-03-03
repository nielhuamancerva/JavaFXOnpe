/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loging.score.Repository.Mongo;

import com.mycompany.loging.score.Repository.Mongo.service.ConexionMongo;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
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
        Document ss = collection.find(Filters.eq("usuario", username)).first();

        return ss;

    }

    @Override
    public  MongoCollection<Document> findAllCollecion(String username) throws Exception {

       

        return mongoDatabase.getCollection(username);
    }

}
