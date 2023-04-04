/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.loging.score.Repository.implementacion;

import com.mongodb.client.MongoCollection;
import com.mycompany.loging.score.Repository.FactoryServiciosExternos;
import com.mycompany.loging.score.Repository.service.SettingService;
import org.bson.Document;

/**
 *
 * @author CASSHERN
 */
public class SettingServiceImpl implements SettingService {

    private FactoryServiciosExternos serviceFactory;

    @Override
    public Document findSetting() throws Exception{
        serviceFactory = FactoryServiciosExternos.getInstance();
        serviceFactory.MongoService().conexionMongo();
        return serviceFactory.MongoService().findDocumentBy("statusSetting", "1", "setting");
    }

    @Override
    public MongoCollection<Document> findAllCollection() throws Exception {
        serviceFactory = FactoryServiciosExternos.getInstance();
        return serviceFactory.MongoService().findAllCollecion("setting");
    }

    @Override
    public Document findSettingBy(String name) throws Exception {
        serviceFactory = FactoryServiciosExternos.getInstance();
        return serviceFactory.MongoService().findDocumentBy("name", name, "setting");
    }
}
