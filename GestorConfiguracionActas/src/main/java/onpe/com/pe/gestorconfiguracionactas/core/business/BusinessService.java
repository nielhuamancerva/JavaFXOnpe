/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package onpe.com.pe.gestorconfiguracionactas.core.business;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import onpe.com.pe.gestorconfiguracionactas.core.model.Component;
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

    public ObservableList<Setting> findAllSettingOnlyEleccion() throws Exception;

    public String findSettingWithEleccionIsActived() throws Exception;

    public void uploadSettingToIsActived(String eleccion) throws Exception;

    public void uploadSections(String idSetings, String coordenadas) throws Exception;

    public String readTitleActa(Integer x, Integer y, Integer h, Integer w) throws Exception;

    public ObservableList<String> findAllSections() throws Exception;

    public List<String> findAllSections1(String nameEleccion) throws Exception;

    public void deleteOneSections(String nameEleccion) throws Exception;

    public List<String> findSettingForNameEleccion(String nameEleccion) throws Exception;

    public void updateSetting(String nameEleccion, String array) throws Exception;
    
     public void uploadSectionsCoornates(String idSetings, String coordenadas) throws Exception;

    public ObservableList<String> findAllSectionsOnCorrdinates(String idSecciont) throws Exception;
    
    public void finAllComponent() throws IOException, Exception;
}
