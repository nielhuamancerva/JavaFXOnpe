/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.loging.negocio.Repository.Mongo;

import com.mongodb.async.client.MongoCollection;
import com.mongodb.async.client.MongoDatabase;

/**
 *
 * @author LMedina
 */
public interface ConexionMongo {
    public MongoDatabase conexionMongo() throws Exception;
    
     public MongoCollection<Document> findCollection() throws Exception;
}
