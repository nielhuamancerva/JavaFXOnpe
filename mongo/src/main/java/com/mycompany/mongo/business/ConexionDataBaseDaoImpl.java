/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mongo.business;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mycompany.mongo.business.repository.ConexionDataBaseDao;
import org.bson.Document;

/**
 *
 * @author CASSHERN
 */
public class ConexionDataBaseDaoImpl implements ConexionDataBaseDao {

    public static ConexionDataBaseDaoImpl conexionDataBase;
    
    
    MongoDatabase database;
        /**
     * Return a instance of DataBaseDao class.
     *
     * @return a instance of DataBaseDao
     */
    public static ConexionDataBaseDaoImpl getInstance() {
        if (conexionDataBase == null) {
            conexionDataBase = new ConexionDataBaseDaoImpl();
        }
        return conexionDataBase;
    }

    @Override
    public boolean mongoConexionOpen() {
                // Crear un objeto MongoClient
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        // Obtener una base de datos
        MongoDatabase database = mongoClient.getDatabase("dbmongo");

        // filtar
/*        user = collection.find(Filters.eq("user", "niel")).first();
        System.out.println(user.get("password"));*/
        
        
        return true; 
    }

    @Override
    public MongoCollection<Document> getCollection() {
        
        // Obtener la collecion de mongo
        MongoCollection<Document> collection = database.getCollection("user");
        
        return collection;
    }

}
