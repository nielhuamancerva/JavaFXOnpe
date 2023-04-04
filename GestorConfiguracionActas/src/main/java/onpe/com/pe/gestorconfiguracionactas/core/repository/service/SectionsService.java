package onpe.com.pe.gestorconfiguracionactas.core.repository.service;

import javafx.collections.ObservableList;
import onpe.com.pe.gestorconfiguracionactas.core.model.Sections;

/**
 *
 * @author NHuaman
 */
public interface SectionsService {

    public ObservableList<Sections> findAllSections() throws Exception;

    public void saveSections(Sections sections) throws Exception;
}
