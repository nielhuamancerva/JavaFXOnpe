package com.mycompany.loging.score.Repository.implementacion;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import com.mycompany.loging.score.Repository.FactoryServiciosExternos;
import com.mycompany.loging.score.Repository.service.ImageService;
import com.mycompany.loging.score.model.Imagenes;
import com.mycompany.loging.score.util.constanst.VariableGlobals;
import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import static java.util.stream.Collectors.mapping;
import org.bson.Document;

/**
 *
 * @author NHuaman
 */
public class ImageServiceImpl implements ImageService {

    private FactoryServiciosExternos serviceFactory;

    @Override
    public void saveImage(Document codigoBarra) throws Exception {

        serviceFactory = FactoryServiciosExternos.getInstance();
        serviceFactory.MongoService().conexionMongo();

        Document filter = new Document("acta_id", codigoBarra.getString("acta_id"));
        Document update = new Document("$set", new Document(codigoBarra));
        serviceFactory.MongoService().updateDocument(filter, update,"imagenes");

    }

    @Override
    public Document findImageById(String idImagen) throws Exception {
        serviceFactory = FactoryServiciosExternos.getInstance();
        return serviceFactory.MongoService().findDocumentBy("acta_id", idImagen, "imagenes");
    }

}
