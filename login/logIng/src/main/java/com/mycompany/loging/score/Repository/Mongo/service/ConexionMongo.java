/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.loging.score.Repository.Mongo.service;

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
}
