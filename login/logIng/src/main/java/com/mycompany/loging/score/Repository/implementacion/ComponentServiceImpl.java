/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.loging.score.Repository.implementacion;

import com.mongodb.client.MongoCollection;
import com.mycompany.loging.score.Repository.FactoryServiciosExternos;
import com.mycompany.loging.score.Repository.service.ComponentService;
import org.bson.Document;

/**
 *
 * @author CASSHERN
 */
public class ComponentServiceImpl implements ComponentService{
  private FactoryServiciosExternos serviceFactory;
  
    @Override
    public MongoCollection<Document> findAllCollection() throws Exception {
               serviceFactory = FactoryServiciosExternos.getInstance();
        return serviceFactory.MongoService().findAllCollecion("modules");
    }
    
}
