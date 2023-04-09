/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package onpe.com.pe.gestorconfiguracionactas.core.repository.service;

import com.mongodb.client.result.DeleteResult;
import javafx.collections.ObservableList;
import onpe.com.pe.gestorconfiguracionactas.core.model.Setting;
import org.bson.Document;

/**
 *
 * @author NHuaman
 */
public interface SettingService {

    public void saveSetting(Setting setting) throws Exception;

    public Document findOneSettingName(String setting) throws Exception;

    public Document findOneSettingByStatus() throws Exception;

    public DeleteResult deleteOneSetting(String setting) throws Exception;

    public ObservableList<Setting> findAllSetting() throws Exception;

    public void updateStatusSetting(Document settingDocument) throws Exception;

    public void updateSettingCoordinates(String idSetting, String coordenates) throws Exception;
}
