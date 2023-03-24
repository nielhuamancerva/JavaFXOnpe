/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package onpe.com.pe.gestorconfiguracionactas.core.business;

import java.io.File;
import java.io.IOException;
import org.bson.Document;

/**
 *
 * @author NHuaman
 */
public interface BusinessService {

    public Document findUserBy(String username, String password) throws IOException, Exception;

    public String uploadFileOnMemory(File fileSelected);
}
