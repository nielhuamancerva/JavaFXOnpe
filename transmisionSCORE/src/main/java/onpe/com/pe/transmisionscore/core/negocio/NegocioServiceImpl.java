package onpe.com.pe.transmisionscore.core.negocio;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.client.MongoCollection;
import onpe.com.pe.transmisionscore.core.repository.FactoryServiciosExternos;
import onpe.com.pe.transmisionscore.core.model.Actas;
import onpe.com.pe.transmisionscore.core.model.Imagenes;
import onpe.com.pe.transmisionscore.core.model.Transmision;
import java.io.IOException;
import org.bson.Document;
import onpe.com.pe.transmisionscore.core.negocio.service.NegocioService;
import onpe.com.pe.transmisionscore.core.util.common.commonMappings;
import onpe.com.pe.transmisionscore.core.util.constanst.Constansts;
import onpe.com.pe.transmisionscore.core.util.constanst.VariableGlobales;
import onpe.com.pe.transmisionscore.core.util.mapper.Mapper;
import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.ObservableList;

/**
 *
 * @author LMedina
 */
public class NegocioServiceImpl implements NegocioService {

    private FactoryServiciosExternos factoryservices;
    private final Mapper mapping;

    public NegocioServiceImpl() {
        this.mapping = new Mapper();
    }

//    @Override
//    public Document consultaUsuarioDb(String username, String password) throws IOException, Exception {
//        factoryservices = FactoryServiciosExternos.getInstance();
//        return factoryservices.UserService().findByUsernameAndPassword(username, password);
//    }

    @Override
    public ObservableList<Actas> finAllActas() throws IOException, Exception {
        factoryservices = FactoryServiciosExternos.getInstance();
        factoryservices.MongoService().conexionMongo();
        return mapping.castObservableListOfActas(factoryservices.ActaServiceImpl().findAllCollection().find());
    }

//    @Override
//    public Actas finByCodigoBarra(String codigoBarra) throws IOException, Exception {
//        factoryservices = FactoryServiciosExternos.getInstance();
//        Actas acta = mapping.documentCastToActas(factoryservices.ActaServiceImpl().findActaBy(codigoBarra));
//        Imagenes imagen = mapping.documentCastToImagen(factoryservices.ImageServiceImpl().findImageById(acta.getId_acta()));
//        acta.setImagen(imagen);
//        return acta;
//    }

//    @Override
//    public String uploadFileOnMemory(File fileSelected) {
//        VariableGlobales.lecturaActasEnMemoria = new HashMap();
//        VariableGlobales.lecturaActasEnMemoria.put("lecturaPrimera", "SI");
//        VariableGlobales.lecturaActasEnMemoria.put("pathTesseract", Constansts.PATH_TESSERACT);
//        VariableGlobales.lecturaActasEnMemoria.put("fileNamePathOriginal", fileSelected.getPath());
//        VariableGlobales.lecturaActasEnMemoria.put("fileNamePath", commonMappings.pathOfFile(fileSelected));
//        VariableGlobales.lecturaActasEnMemoria.put("fileName", fileSelected.getName());
//        VariableGlobales.lecturaActasEnMemoria.put("fileNameSinExtension", commonMappings.nameOfFileWithoutExtension(fileSelected));
//        return fileSelected.getPath();
//    }

//    @Override
//    public Transmision uploadActaReadOnMemory(Actas actaReadOnMemory) throws IOException, Exception {
//        factoryservices = FactoryServiciosExternos.getInstance();
//        factoryservices.ImageServiceImpl()
//                .saveImage(mapping.imagenCastToDocument(actaReadOnMemory.getImagen()));
//        factoryservices.ActaServiceImpl().saveImage(mapping.actaCastToDocument(actaReadOnMemory));
//        return mapping.dtoToTransmision(actaReadOnMemory);
//    }

//    @Override
//    public void readAndCutBarcode(Integer cordenadaX, Integer cordenadaY, Integer cordenadaAnchoW, Integer cordenadaAltoH) throws IOException, Exception {
//        factoryservices = FactoryServiciosExternos.getInstance();
//        factoryservices.Tess4jServiceImpl().leerCodigoDeBarras(cordenadaX, cordenadaY, cordenadaAnchoW, cordenadaAltoH);
//    }
//
//    @Override
//    public void readAndCutOrganizationsPolitical(Integer cordenadaX, Integer cordenadaY, Integer cordenadaAnchoW, Integer cordenadaAltoH) throws IOException, Exception {
//        factoryservices = FactoryServiciosExternos.getInstance();
//        factoryservices.Tess4jServiceImpl().leerRegionNumeroVotos(cordenadaX, cordenadaY, cordenadaAnchoW, cordenadaAltoH);
//    }
//
//    @Override
//    public void readAndCutObservations(Integer cordenadaX, Integer cordenadaY, Integer cordenadaAnchoW, Integer cordenadaAltoH) throws IOException, Exception {
//        factoryservices = FactoryServiciosExternos.getInstance();
//        factoryservices.Tess4jServiceImpl().leerObservaciones(cordenadaX, cordenadaY, cordenadaAnchoW, cordenadaAltoH);
//    }
//
//    @Override
//    public Boolean readAndCutsignature(String nameCandidate, Integer cordenadaX, Integer cordenadaY, Integer cordenadaAnchoW, Integer cordenadaAltoH) throws IOException, Exception {
//        factoryservices = FactoryServiciosExternos.getInstance();
//        return factoryservices.Tess4jServiceImpl().validarFirma(nameCandidate, cordenadaX, cordenadaY, cordenadaAnchoW, cordenadaAltoH);
//    }
//
    @Override
    public void loadSettingActa() throws Exception {
        factoryservices = FactoryServiciosExternos.getInstance();
        Document document = factoryservices.SettingService().findSetting();

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, Object> map = gson.fromJson(document.getString("setting"), type);
        Map<String, String> stringMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().toString();
            stringMap.put(key, value);
        }

        VariableGlobales.configuracionActa = stringMap;
    }

//    @Override
//    public void readAndCutHoraInicio(String nameFile, Integer cordenadaX, Integer cordenadaY, Integer cordenadaAnchoW, Integer cordenadaAltoH) throws IOException, Exception {
//        factoryservices = FactoryServiciosExternos.getInstance();
//        factoryservices.Tess4jServiceImpl().leerHora(nameFile, cordenadaX, cordenadaY, cordenadaAnchoW, cordenadaAltoH);
//    }


}