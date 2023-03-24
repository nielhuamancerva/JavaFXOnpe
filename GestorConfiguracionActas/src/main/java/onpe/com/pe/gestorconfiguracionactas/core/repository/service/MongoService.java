/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package onpe.com.pe.gestorconfiguracionactas.core.repository.service;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

/**
 *
 * @author NHuaman
 */
public interface MongoService {

    public MongoDatabase conexionMongo() throws Exception;

    public UpdateResult updateDocument(Document codigoBarra, Document filter, Document update, String tableOfMongo) throws Exception;

    public Document findCollection(String username, String password) throws Exception;

    public Document findDocumentBy(String filters, String codigoDocument, String Table) throws Exception;
}
