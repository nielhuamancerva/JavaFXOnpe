package com.mycompany.loging.score.negocio;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.client.MongoCollection;
import com.mycompany.loging.score.Repository.FactoryServiciosExternos;
import com.mycompany.loging.score.Repository.implementacion.SettingServiceImpl;
import com.mycompany.loging.score.model.Actas;
import com.mycompany.loging.score.model.Component;
import com.mycompany.loging.score.model.Imagenes;
import com.mycompany.loging.score.model.Setting;
import com.mycompany.loging.score.model.Transmision;
import java.io.IOException;
import org.bson.Document;
import com.mycompany.loging.score.negocio.service.NegocioService;
import com.mycompany.loging.score.util.common.CommonMappings;
import com.mycompany.loging.score.util.constanst.Constanst;
import com.mycompany.loging.score.util.constanst.VariableGlobals;
import com.mycompany.loging.score.util.mapper.Mappers;
import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author LMedina
 */
public class NegocioServiceImpl implements NegocioService {

    private FactoryServiciosExternos factoryservices;
    private final Mappers mapping;

    public NegocioServiceImpl() {
        this.mapping = new Mappers();
    }

    @Override
    public Document consultaUsuarioDb(String username, String password) throws IOException, Exception {
        factoryservices = FactoryServiciosExternos.getInstance();
        return factoryservices.UserService().findByUsernameAndPassword(username, password);
    }

    @Override
    public ObservableList<Actas> finAllActas() throws IOException, Exception {
        factoryservices = FactoryServiciosExternos.getInstance();
        factoryservices.MongoService().conexionMongo();
        return mapping.castObservableListOfActas(factoryservices.ActaServiceImpl().findAllCollection().find());
    }

    @Override
    public Actas finByCodigoBarra(String codigoBarra) throws IOException, Exception {
        factoryservices = FactoryServiciosExternos.getInstance();
        Actas acta = mapping.documentCastToActas(factoryservices.ActaServiceImpl().findActaBy(codigoBarra));
        Imagenes imagen = mapping.documentCastToImagen(factoryservices.ImageServiceImpl().findImageById(acta.getId_acta()));
        acta.setImagen(imagen);
        return acta;
    }

    @Override
    public String uploadFileOnMemory(File fileSelected) {
        VariableGlobals.lecturaActasEnMemoria = new HashMap();
        VariableGlobals.lecturaActasEnMemoria.put("lecturaPrimera", "SI");
        VariableGlobals.lecturaActasEnMemoria.put("pathTesseract", Constanst.PATH_TESSERACT);
        VariableGlobals.lecturaActasEnMemoria.put("fileNamePathOriginal", fileSelected.getPath());
        VariableGlobals.lecturaActasEnMemoria.put("fileNamePath", CommonMappings.pathOfFile(fileSelected));
        VariableGlobals.lecturaActasEnMemoria.put("fileName", fileSelected.getName());
        VariableGlobals.lecturaActasEnMemoria.put("fileNameSinExtension", CommonMappings.nameOfFileWithoutExtension(fileSelected));
        return fileSelected.getPath();
    }

    @Override
    public Transmision uploadActaReadOnMemory(Actas actaReadOnMemory) throws IOException, Exception {
        factoryservices = FactoryServiciosExternos.getInstance();
        factoryservices.ImageServiceImpl()
                .saveImage(mapping.imagenCastToDocument(actaReadOnMemory.getImagen()));
        factoryservices.ActaServiceImpl().saveImage(mapping.actaCastToDocument(actaReadOnMemory));
        return mapping.dtoToTransmision(actaReadOnMemory);
    }

    @Override
    public void readAndCutBarcode(Integer cordenadaX, Integer cordenadaY, Integer cordenadaAnchoW, Integer cordenadaAltoH) throws IOException, Exception {
        factoryservices = FactoryServiciosExternos.getInstance();
        factoryservices.Tess4jServiceImpl().leerCodigoDeBarras(cordenadaX, cordenadaY, cordenadaAnchoW, cordenadaAltoH);
    }

    @Override
    public void readAndCutOrganizationsPolitical(Integer cordenadaX, Integer cordenadaY, Integer cordenadaAnchoW, Integer cordenadaAltoH) throws IOException, Exception {
        factoryservices = FactoryServiciosExternos.getInstance();
        factoryservices.Tess4jServiceImpl().leerRegionNumeroVotos(cordenadaX, cordenadaY, cordenadaAnchoW, cordenadaAltoH);
    }

    @Override
    public void readAndCutObservations(Integer cordenadaX, Integer cordenadaY, Integer cordenadaAnchoW, Integer cordenadaAltoH) throws IOException, Exception {
        factoryservices = FactoryServiciosExternos.getInstance();
        factoryservices.Tess4jServiceImpl().leerObservaciones(cordenadaX, cordenadaY, cordenadaAnchoW, cordenadaAltoH);
    }

    @Override
    public Boolean readAndCutsignature(String nameCandidate, Integer cordenadaX, Integer cordenadaY, Integer cordenadaAnchoW, Integer cordenadaAltoH) throws IOException, Exception {
        factoryservices = FactoryServiciosExternos.getInstance();
        return factoryservices.Tess4jServiceImpl().validarFirma(nameCandidate, cordenadaX, cordenadaY, cordenadaAnchoW, cordenadaAltoH);
    }

    @Override
    public void loadSettingActa() throws Exception {
        factoryservices = FactoryServiciosExternos.getInstance();
        Document document = factoryservices.SettingService().findSetting();

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, Object> map = gson.fromJson(document.getString("setting"), type);

        //System.out.println(gson.toJson(map));
        Map<String, String> stringMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().toString();
            stringMap.put(key, value);
        }

        VariableGlobals.configuracionActa = stringMap;
    }

    @Override
    public void readAndCutHoraInicio(String nameFile, Integer cordenadaX, Integer cordenadaY, Integer cordenadaAnchoW, Integer cordenadaAltoH) throws IOException, Exception {
        factoryservices = FactoryServiciosExternos.getInstance();
        factoryservices.Tess4jServiceImpl().leerHora(nameFile, cordenadaX, cordenadaY, cordenadaAnchoW, cordenadaAltoH);
    }

    @Override
    public ObservableList<Setting> finAllSetting() throws IOException, Exception {
        factoryservices = FactoryServiciosExternos.getInstance();
        factoryservices.MongoService().conexionMongo();
        return mapping.castObservableListOfSetting(factoryservices.SettingService().findAllCollection().find());
    }

    @Override
    public ObservableList<Setting> finSettingByname(String name) throws IOException, Exception {
        factoryservices = FactoryServiciosExternos.getInstance();
        factoryservices.MongoService().conexionMongo();
        ObservableList<Setting> ListSettingAux = FXCollections.observableArrayList();
        ObservableList<Setting> ListSetting = mapping.castObservableListOfSetting(factoryservices.SettingService().findAllCollection().find());
        for (Setting setting : ListSetting) {
            if (setting.getName().equals(name)) {
                System.out.println(setting.getName());
                ListSettingAux.add(setting);
                return ListSettingAux;
            }
        }
        return mapping.castObservableListOfSetting(factoryservices.SettingService().findAllCollection().find());
    }

    @Override
    public Setting finAllSettingByName(String name) throws IOException, Exception {
        factoryservices = FactoryServiciosExternos.getInstance();
        Setting setting = mapping.documentCastTSetting(factoryservices.SettingService().findSettingBy(name));
        return setting;
    }

    @Override
    public ObservableList<String> findAllSectionsOnCorrdinates(String idSecciont) throws Exception {
    VariableGlobals.configuracionActa = new HashMap();
    factoryservices  = FactoryServiciosExternos.getInstance();

    String yy = factoryservices.SectionsService().findAllSections().stream()
            .filter(s -> s.getSetting_id().equals(idSecciont))
            .map(y -> y.getCoordinates())
            .collect(Collectors.toCollection(FXCollections::observableArrayList)).get(0);

    yy = yy.replace("{","").replace("}","").replace(" ","").replace("=","\":\"").replace(",", "\",\"");
    yy = "{\""+ yy + "\"}";
    
    Gson gson = new Gson();
    Type type = new TypeToken<Map<String, String>>() {}.getType();
    Map<String, String> map = gson.fromJson(yy, type);

    //System.out.println(gson.toJson(map));
    /*
    Map<String, String> stringMap = new HashMap<>();
    
    for (Map.Entry<String, Object> entry: map.entrySet ()) {
            String key = entry.getKey();
        String value = entry.getValue().toString();
        stringMap.put(key, value);
    }*/

    VariableGlobals.configuracionActa  = map;

    return factoryservices.SectionsService ().findAllSections().stream()
                .filter(s -> s.getSetting_id().equals(idSecciont))
                .map(y -> y.getCoordinates())
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    @Override
    public ObservableList<Component> finAllComponent() throws IOException, Exception {
                factoryservices = FactoryServiciosExternos.getInstance();
        factoryservices.MongoService().conexionMongo();
        return mapping.castObservableListOfComponent(factoryservices.ComponentService().findAllCollection().find());
    }
}
