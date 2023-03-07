package com.mycompany.loging.score.Repository.implementacion;

import com.mycompany.loging.score.Repository.FactoryServiciosExternos;
import com.mycompany.loging.score.Repository.service.UserService;
import java.io.IOException;
import org.bson.Document;

/**
 *
 * @author NHuaman
 */
public class UserServiceImpl implements UserService {

    private FactoryServiciosExternos serviceFactory;

    @Override
    public Document findByUsernameAndPassword(String username, String password) throws IOException, Exception{
        serviceFactory = FactoryServiciosExternos.getInstance();
        serviceFactory.MongoService().conexionMongo();
        Document oUser= serviceFactory.MongoService().findCollection(username, password);
        return oUser;
    }

}
