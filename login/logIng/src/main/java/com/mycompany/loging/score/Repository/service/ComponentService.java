/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.loging.score.Repository.service;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 *
 * @author CASSHERN
 */
public interface ComponentService {
    public MongoCollection<Document> findAllCollection() throws Exception;
}
