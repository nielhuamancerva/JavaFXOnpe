/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onpe.com.pe.gestorconfiguracionactas.core.repository.impl;

import com.mongodb.client.model.Filters;
import java.io.IOException;
import onpe.com.pe.gestorconfiguracionactas.core.repository.FactoryService;
import onpe.com.pe.gestorconfiguracionactas.core.repository.service.UserService;
import org.bson.Document;

/**
 *
 * @author NHuaman
 */
public class UserServiceImpl implements UserService {

    private FactoryService factoryService;

    @Override
    public Document findByUsernameAndPassword(String username, String password) throws IOException, Exception {
        factoryService = FactoryService.getInstance();
        factoryService.MongoService().conexionMongo();
        return factoryService.MongoService().findDocumentByMultiple(
                Filters.and(Filters.eq("usuario", username),
                            Filters.eq("password", password)), "user");
    }

}
