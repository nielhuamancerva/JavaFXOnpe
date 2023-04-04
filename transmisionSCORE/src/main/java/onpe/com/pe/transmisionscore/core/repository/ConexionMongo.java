package onpe.com.pe.transmisionscore.core.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import onpe.com.pe.transmisionscore.core.model.Actas;
import org.bson.Document;

/**
 *
 * @author LMedina
 */
public interface ConexionMongo {

    public MongoDatabase conexionMongo() throws Exception;

    public UpdateResult updateDocument(Document codigoBarra,Document filter,Document update, String tableOfMongo) throws Exception;

    public Document findCollection(String username, String password) throws Exception;

    public MongoCollection<Document> findAllCollecion(String table) throws Exception;

    public Document findActaByCodigoBarra(String codigoBarra) throws Exception;

    public Document findDocumentBy(String filters, String codigoDocument,String Table) throws Exception;

}
