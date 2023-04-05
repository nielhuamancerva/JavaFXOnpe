package com.mycompany.loging.score.Repository.service;

import com.mycompany.loging.score.model.Sections;
import javafx.collections.ObservableList;
import org.bson.Document;

/**
 *
 * @author NHuaman
 */
public interface SectionsService {

    public ObservableList<Sections> findAllSections() throws Exception;


}
