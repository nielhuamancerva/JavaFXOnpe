/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onpe.com.pe.gestorconfiguracionactas.core.repository.impl;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import onpe.com.pe.gestorconfiguracionactas.core.repository.FactoryService;
import onpe.com.pe.gestorconfiguracionactas.core.repository.service.ComponentService;
import org.bson.Document;

/**
 *
 * @author CASSHERN
 */
public class ComponentServiceImpl implements ComponentService{
  private FactoryService serviceFactory;
  
    @Override
    public FindIterable<Document> findAllCollection() throws Exception {
               serviceFactory = FactoryService.getInstance();
        return serviceFactory.MongoService().findCollection("modules");
    }
    
}
