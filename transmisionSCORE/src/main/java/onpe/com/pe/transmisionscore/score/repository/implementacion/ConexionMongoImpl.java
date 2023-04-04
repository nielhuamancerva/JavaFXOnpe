package onpe.com.pe.transmisionscore.score.repository.implementacion;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

import onpe.com.pe.transmisionscore.core.repository.ConexionMongo;
import org.bson.Document;

/**
 *
 * @author LMedina
 */
public class ConexionMongoImpl implements ConexionMongo {

    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> mongoColletion;

    @Override
    public MongoDatabase conexionMongo() throws Exception {
        // Crear un objeto MongoClient
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        // Obtener una base de datos
        mongoDatabase = mongoClient.getDatabase("dbmongo");
        return mongoDatabase;
        // Obtener la collecion de mongo

    }

    @Override
    public Document findCollection(String username, String password) {

        // Obtener la collecion de mongo
        MongoCollection<Document> collection = mongoDatabase.getCollection("user");
        Document ss = collection.find(Filters.and(
                Filters.eq("usuario", username),
                Filters.eq("password", password))).first();

        return ss;

    }

    @Override
    public MongoCollection<Document> findAllCollecion(String username) throws Exception {

        return mongoDatabase.getCollection(username);
    }

    @Override
    public Document findActaByCodigoBarra(String codigoBarra) throws Exception {
        MongoCollection<Document> collection = mongoDatabase.getCollection("actas");
        return collection.find(Filters.eq("acta", codigoBarra)).first();
    }

    @Override
    public UpdateResult updateDocument(Document codigoBarra, Document filter, Document update, String tableOfMongo) throws Exception {
        MongoCollection<Document> collection = mongoDatabase.getCollection(tableOfMongo);
        return collection.updateOne(filter, update);
    }

    @Override
    public Document findDocumentBy(String filter_id, String codigo_id, String Table) throws Exception {
        MongoCollection<Document> collection = mongoDatabase.getCollection(Table);
        return collection.find(Filters.eq(filter_id, codigo_id)).first();
    }

}
