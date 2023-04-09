/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onpe.com.pe.gestorconfiguracionactas.core.repository.impl;

import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import javafx.collections.ObservableList;
import onpe.com.pe.gestorconfiguracionactas.core.model.Setting;
import onpe.com.pe.gestorconfiguracionactas.core.repository.FactoryService;
import onpe.com.pe.gestorconfiguracionactas.core.repository.service.SettingService;
import onpe.com.pe.gestorconfiguracionactas.core.util.mapper.Mapper;
import org.bson.Document;

/**
 *
 * @author NHuaman
 */
public class SettingServiceImpl implements SettingService {

    private FactoryService factoryService;

    @Override
    public void saveSetting(Setting setting) throws Exception {
        factoryService = FactoryService.getInstance();
        factoryService.MongoService().conexionMongo();
        factoryService.MongoService().saveDocument(Mapper.settingCastToDocument(setting), "setting");
    }

    @Override
    public ObservableList<Setting> findAllSetting() throws Exception {
        factoryService = FactoryService.getInstance();
        factoryService.MongoService().conexionMongo();
        return Mapper.actaCastToDocument(factoryService.MongoService().findCollection("setting"));
    }

    @Override
    public void updateStatusSetting(Document setting) throws Exception {

        factoryService = FactoryService.getInstance();
        factoryService.MongoService().conexionMongo();
        Document filter = new Document("name", setting.getString("name"));
        Document update = new Document("$set", new Document(setting));

        factoryService.MongoService().updateDocument(filter, update, "setting");
    }

    @Override
    public Document findOneSettingName(String setting) throws Exception {
        factoryService = FactoryService.getInstance();
        factoryService.MongoService().conexionMongo();
        return factoryService.MongoService().findDocumentBy("name", setting, "setting");
    }

    @Override
    public Document findOneSettingByStatus() throws Exception {
        factoryService = FactoryService.getInstance();
        factoryService.MongoService().conexionMongo();
        return factoryService.MongoService().findDocumentBy("statusSetting", "1", "setting");
    }

    @Override
    public DeleteResult deleteOneSetting(String setting) throws Exception {
        factoryService = FactoryService.getInstance();
        factoryService.MongoService().conexionMongo();

        return factoryService.MongoService().deleteOneDocument(Filters.and(Filters.eq("name", setting)), "setting");
    }

    @Override
    public void updateSettingCoordinates(String idSetting, String coordenates) throws Exception {
        factoryService = FactoryService.getInstance();
        factoryService.MongoService().conexionMongo();
        Document filter = new Document("id_setting", idSetting);
        Document update = new Document("$set", new Document("setting",coordenates));

        factoryService.MongoService().updateDocument(filter, update, "setting");
    }

}
