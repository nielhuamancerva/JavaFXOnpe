package com.mycompany.loging.score.Repository.implementacion;

import com.mycompany.loging.score.Repository.FactoryServiciosExternos;
import com.mycompany.loging.score.Repository.service.SectionsService;
import com.mycompany.loging.score.model.Sections;
import com.mycompany.loging.score.util.mapper.Mappers;
import javafx.collections.ObservableList;

import org.bson.Document;

/**
 *
 * @author NHuaman
 */
public class SectionsServiceImpl implements SectionsService {

    private FactoryServiciosExternos factoryService;

    @Override
    public ObservableList<Sections> findAllSections() throws Exception {
        factoryService = FactoryServiciosExternos.getInstance();
        factoryService.MongoService().conexionMongo();
        return Mappers.sectionCastToDocument(factoryService.MongoService().findCollection("sections"));
    }



}
