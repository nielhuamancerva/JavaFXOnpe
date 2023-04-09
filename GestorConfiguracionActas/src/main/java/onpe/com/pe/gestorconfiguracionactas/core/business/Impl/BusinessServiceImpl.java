/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onpe.com.pe.gestorconfiguracionactas.core.business.Impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import onpe.com.pe.gestorconfiguracionactas.core.util.VariableGlobales;
import onpe.com.pe.gestorconfiguracionactas.core.util.commonmappings.CommonMappings;
import org.bson.Document;
import onpe.com.pe.gestorconfiguracionactas.core.business.BusinessService;
import onpe.com.pe.gestorconfiguracionactas.core.model.Sections;
import onpe.com.pe.gestorconfiguracionactas.core.model.Setting;
import onpe.com.pe.gestorconfiguracionactas.core.repository.FactoryService;
import onpe.com.pe.gestorconfiguracionactas.core.util.mapper.Mapper;

public class BusinessServiceImpl implements BusinessService {

    private FactoryService factoryService;

    @Override
    public Document findUserBy(String username, String password) throws IOException, Exception {
        factoryService = FactoryService.getInstance();
        return factoryService.UserService().findByUsernameAndPassword(username, password);
    }

    @Override
    public String uploadFileOnMemory(File fileSelected) {
        VariableGlobales.lecturaActasEnMemoria = new HashMap();
        VariableGlobales.lecturaActasEnMemoria.put("lecturaPrimera", "SI");
        VariableGlobales.lecturaActasEnMemoria.put("fileNamePathOriginal", fileSelected.getPath());
        VariableGlobales.lecturaActasEnMemoria.put("fileNamePath", CommonMappings.pathOfFile(fileSelected));
        VariableGlobales.lecturaActasEnMemoria.put("fileName", fileSelected.getName());
        VariableGlobales.lecturaActasEnMemoria.put("fileNameSinExtension", CommonMappings.nameOfFileWithoutExtension(fileSelected));
        return fileSelected.getPath();
    }

    @Override
    public void saveSetting(Setting newSetting) throws Exception {
        factoryService = FactoryService.getInstance();
        factoryService.SettingService().saveSetting(newSetting);
        Sections sections = new Sections();
        sections.setSetting_id(newSetting.getId_setting());
        factoryService.SectionsService().saveSections(sections);
    }

    @Override
    public ObservableList<Setting> findAllSettingOnlyEleccion() throws Exception {
        factoryService = FactoryService.getInstance();

        return factoryService.SettingService().findAllSetting();
    }

    @Override
    public String findSettingWithEleccionIsActived() throws Exception {
        factoryService = FactoryService.getInstance();
        List<Setting> uu = factoryService.SettingService().findAllSetting().stream()
                .filter(s -> s.getStatusSetting().equals("1")).toList();
        return uu.isEmpty() ? "NINGUNO" : uu.get(0).getName();
    }

    @Override
    public void uploadSettingToIsActived(String setting) throws Exception {
        factoryService = FactoryService.getInstance();
        factoryService.SettingService().findAllSetting();

        Document document = factoryService.SettingService().findOneSettingByStatus();

        if (document == null) {

            document = factoryService.SettingService().findOneSettingName(setting);
            document.append("statusSetting", "1");
            factoryService.SettingService().updateStatusSetting(document);
        } else {
            document.append("statusSetting", "0");
            factoryService.SettingService().updateStatusSetting(document);
            document = factoryService.SettingService().findOneSettingName(setting);
            document.append("statusSetting", "1");
            factoryService.SettingService().updateStatusSetting(document);
        }

    }

    @Override
    public String readTitleActa(Integer x, Integer y, Integer h, Integer w) throws Exception {
        factoryService = FactoryService.getInstance();
        return factoryService.Tess4jService().readTitleActa(x, y, h, w);
    }

    @Override
    public ObservableList<String> findAllSections() throws Exception {
        factoryService = FactoryService.getInstance();
        return factoryService.SettingService().findAllSetting().stream()
                .map(s -> s.getName())
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    @Override
    public void deleteOneSections(String nameEleccion) throws Exception {
        factoryService = FactoryService.getInstance();
        factoryService.SettingService().deleteOneSetting(nameEleccion);
    }

    @Override
    public List<String> findAllSections1(String nameEleccion) throws Exception {
        factoryService = FactoryService.getInstance();
        return factoryService.SettingService().findAllSetting().stream()
                .filter(t -> t.getName().equals(nameEleccion))
                .map(s -> s.getSetting())
                .collect(Collectors.toList());
    }

    @Override
    public void uploadSections(String idSetings, String coordenadas) throws Exception {
        factoryService = FactoryService.getInstance();
        factoryService.SettingService().findAllSetting();
        factoryService.SectionsService().updateSections(idSetings, coordenadas);
    }

    public List<String> findSettingForNameEleccion(String nameEleccion) throws Exception {
        factoryService = FactoryService.getInstance();
        return factoryService.SettingService().findAllSetting().stream()
                .filter(t -> t.getName().equals(nameEleccion))
                .map(s -> s.getSetting())
                .collect(Collectors.toList());
    }

    @Override
    public void updateSetting(String nameEleccion, String array) throws Exception {
        factoryService = FactoryService.getInstance();
        Setting setting = factoryService.SettingService().findAllSetting().stream()
                .filter(t -> t.getName().equals(nameEleccion))
                .collect(Collectors.toList()).get(0);
        setting.setSetting(array);
        factoryService.SettingService().updateStatusSetting(Mapper.settingCastToDocument(setting));

    }

    @Override
    public ObservableList<String> findAllSectionsOnCorrdinates(String idSecciont) throws Exception {
        factoryService = FactoryService.getInstance();
        return factoryService.SectionsService().findAllSections().stream()
                .filter(s -> s.getSetting_id().equals(idSecciont))
                .map(y -> y.getCoordinates())
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    @Override
    public void uploadSectionsCoornates(String idSetings, String coordenadas) throws Exception {
        factoryService = FactoryService.getInstance();
        factoryService.SettingService().findAllSetting();
        factoryService.SettingService().updateSettingCoordinates(idSetings, coordenadas);
    }

}
