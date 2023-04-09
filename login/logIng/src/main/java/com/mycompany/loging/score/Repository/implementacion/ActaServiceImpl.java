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
        serviceFactory.MongoService().updateDocument(filter, update, "actas");

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

    @Override
    public void updateActa(Document settingDocument) throws Exception {
        serviceFactory = FactoryServiciosExternos.getInstance();
        serviceFactory.MongoService().conexionMongo();
        Document filter = new Document("name", settingDocument.getString("name"));
        Document update = new Document("$set", new Document(settingDocument));

        serviceFactory.MongoService().updateDocument(filter, update, "setting");
    }
}
