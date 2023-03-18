package com.mycompany.loging.score.Repository.implementacion;

import com.mongodb.client.MongoCollection;
import com.mycompany.loging.score.Repository.FactoryServiciosExternos;
import com.mycompany.loging.score.Repository.service.ActaService;
import org.bson.Document;

/**
 *
 * @author NHuaman
 */
public class ActaServiceImpl implements ActaService {

    private FactoryServiciosExternos serviceFactory;

    @Override
    public void saveImage(Document codigoBarra) throws Exception {
        serviceFactory = FactoryServiciosExternos.getInstance();
        serviceFactory.MongoService().conexionMongo();

        Document filter = new Document("acta", codigoBarra.getString("acta"));
        Document update = new Document("$set", new Document(codigoBarra));
        serviceFactory.MongoService().updateDocument(codigoBarra, filter, update, "actas");

    }

    @Override
    public Document findActaBy(String idActa) throws Exception {
        serviceFactory = FactoryServiciosExternos.getInstance();
        return serviceFactory.MongoService().findDocumentBy("acta", idActa, "actas");
    }

    @Override
    public MongoCollection<Document> findAllCollection() throws Exception {
        serviceFactory = FactoryServiciosExternos.getInstance();
        return serviceFactory.MongoService().findAllCollecion("actas");
    }
}
