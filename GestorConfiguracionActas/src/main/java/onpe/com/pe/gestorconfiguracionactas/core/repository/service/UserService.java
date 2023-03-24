/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package onpe.com.pe.gestorconfiguracionactas.core.repository.service;

import java.io.IOException;
import org.bson.Document;

/**
 *
 * @author NHuaman
 */
public interface UserService {
        public Document findByUsernameAndPassword(String username, String password) throws IOException, Exception;
}
