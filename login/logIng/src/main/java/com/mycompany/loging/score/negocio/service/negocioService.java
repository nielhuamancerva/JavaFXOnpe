
package com.mycompany.loging.score.negocio.service;

import com.mycompany.loging.score.model.Actas;
import java.io.IOException;
import javafx.collections.ObservableList;
import org.bson.Document;

/**
 *
 * @author LMedina
 */
public interface NegocioService {
    public Document consultaUsuarioDb(String username, String password) throws IOException, Exception;
    public ObservableList<Actas> finAllActas() throws IOException, Exception;
}
