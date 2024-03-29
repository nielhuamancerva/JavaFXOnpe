/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onpe.com.pe.transmisionscore.score.repository.implementacion;


import onpe.com.pe.transmisionscore.core.repository.FactoryServiciosExternos;
import onpe.com.pe.transmisionscore.core.repository.service.SettingService;
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

}
