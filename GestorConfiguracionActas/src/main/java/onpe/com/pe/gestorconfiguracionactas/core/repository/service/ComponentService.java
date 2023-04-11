/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onpe.com.pe.gestorconfiguracionactas.core.repository.service;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 *
 * @author CASSHERN
 */
public interface ComponentService {
    public FindIterable<Document> findAllCollection() throws Exception;
}
