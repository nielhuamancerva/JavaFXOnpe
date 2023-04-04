package onpe.com.pe.gestorconfiguracionactas.core.repository.impl;

import javafx.collections.ObservableList;
import onpe.com.pe.gestorconfiguracionactas.core.model.Sections;
import onpe.com.pe.gestorconfiguracionactas.core.repository.FactoryService;
import onpe.com.pe.gestorconfiguracionactas.core.repository.service.SectionsService;
import onpe.com.pe.gestorconfiguracionactas.core.util.mapper.Mapper;

/**
 *
 * @author NHuaman
 */
public class SectionsServiceImpl implements SectionsService {

    private FactoryService factoryService;

    @Override
    public ObservableList<Sections> findAllSections() throws Exception {
        factoryService = FactoryService.getInstance();
        factoryService.MongoService().conexionMongo();
        return Mapper.sectionCastToDocument(factoryService.MongoService().findCollection("sections"));
    }

    @Override
    public void saveSections(Sections sections) throws Exception {
        factoryService = FactoryService.getInstance();
        factoryService.MongoService().conexionMongo();
        factoryService.MongoService().saveDocument(Mapper.sectionsCastToDocument(sections), "sections");
    }

}
