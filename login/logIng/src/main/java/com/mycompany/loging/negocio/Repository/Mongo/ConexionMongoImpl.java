/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loging.negocio.Repository.Mongo;


import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClients;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.async.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

/**
 *
 * @author LMedina
 */
public class ConexionMongoImpl implements ConexionMongo{
       private MongoDatabase mongoDatabase;
        
    @Override
    public MongoDatabase conexionMongo() throws Exception{
                // Crear un objeto MongoClient
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        // Obtener una base de datos
         mongoDatabase = mongoClient.getDatabase("dbmongo");
         
         return mongoDatabase;
        // Obtener la collecion de mongo
        
    }
    
   
    @Override
    public  MongoCollection<Document> findCollection() {
// Obtener la collecion de mongo
        MongoCollection<Document> collection = mongoDatabase.getCollection("user");

        return collection;

    }
    
    
    
}
