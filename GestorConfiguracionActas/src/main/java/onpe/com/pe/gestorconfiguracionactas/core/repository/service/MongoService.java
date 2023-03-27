/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package onpe.com.pe.gestorconfiguracionactas.core.repository.service;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author NHuaman
 */
public interface MongoService {

    public MongoDatabase conexionMongo() throws Exception;
    
    public InsertOneResult saveDocument(Document codigoBarra,String Table) throws Exception;

    public UpdateResult updateDocument(Document filter, Document update, String tableOfMongo) throws Exception;

    public FindIterable<Document> findCollection(String Table) throws Exception;

    public Document findDocumentBy(String filters, String codigoDocument, String Table) throws Exception;
    
    public Document findDocumentByMultiple(Bson filters, String Table) throws Exception;
    
}
