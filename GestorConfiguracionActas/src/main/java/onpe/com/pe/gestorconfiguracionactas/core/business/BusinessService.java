/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package onpe.com.pe.gestorconfiguracionactas.core.business;

import java.io.File;
import java.io.IOException;
import javafx.collections.ObservableList;
import onpe.com.pe.gestorconfiguracionactas.core.model.Setting;
import org.bson.Document;

/**
 *
 * @author NHuaman
 */
public interface BusinessService {

    public Document findUserBy(String username, String password) throws IOException, Exception;

    public String uploadFileOnMemory(File fileSelected);

    public void saveSetting(Setting fileSelected) throws Exception;

    public ObservableList<String> findAllSettingOnlyEleccion() throws Exception;

    public String findSettingWithEleccionIsActived() throws Exception;

    public void uploadSettingToIsActived(String eleccion) throws Exception;


}
