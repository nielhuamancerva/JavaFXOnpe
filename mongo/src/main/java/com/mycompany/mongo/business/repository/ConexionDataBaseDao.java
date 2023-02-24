/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mongo.business.repository;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 *
 * @author CASSHERN
 */
public interface ConexionDataBaseDao {

    public boolean mongoConexionOpen();
    
    public MongoCollection<Document> getCollection();


}
