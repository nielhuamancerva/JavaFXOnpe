
package com.mycompany.loging.score.negocio.service;

import java.io.IOException;
import org.bson.Document;

/**
 *
 * @author LMedina
 */
public interface NegocioService {
    public Document consultaUsuarioDb(String username, String password) throws IOException, Exception;
}
