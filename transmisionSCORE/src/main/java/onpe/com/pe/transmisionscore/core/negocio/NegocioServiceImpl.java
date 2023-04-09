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
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.ObservableList;
import onpe.com.pe.transmisionscore.core.model.TransmisionRecibidas;
import onpe.com.pe.transmisionscore.core.repository.FactoryServices;

/**
 *
 * @author LMedina
 */
public class NegocioServiceImpl implements NegocioService {

    private FactoryServices factoryservices;
    private final Mapper mapping;

    public NegocioServiceImpl() {
        this.mapping = new Mapper();
    }

//    @Override
//    public ObservableList<Actas> finAllActas() throws IOException, Exception {
//        factoryservices = FactoryServiciosExternos.getInstance();
//        factoryservices.MongoService().conexionMongo();
//        return mapping.castObservableListOfActas(factoryservices.ActaServiceImpl().findAllCollection().find());
//    }
    @Override
    public ObservableList<TransmisionRecibidas> finAllActas() throws IOException, Exception {
  
        
        factoryservices = FactoryServices.getInstance();
        factoryservices.ServicePostgreSQL().conexionPostgreSQL();
        return mapping.castObservableListOfActas(factoryservices.ServicePostgreSQL().select("SELECT * FROM tramasrecibidas"));
    }

//    @Override
//    public void loadSettingActa() throws Exception {
//        factoryservices = FactoryServiciosExternos.getInstance();
//        Document document = factoryservices.SettingService().findSetting();
//
//        Gson gson = new Gson();
//        Type type = new TypeToken<Map<String, Object>>() {
//        }.getType();
//        Map<String, Object> map = gson.fromJson(document.getString("setting"), type);
//        Map<String, String> stringMap = new HashMap<>();
//        for (Map.Entry<String, Object> entry : map.entrySet()) {
//            String key = entry.getKey();
//            String value = entry.getValue().toString();
//            stringMap.put(key, value);
//        }
//
//        VariableGlobales.configuracionActa = stringMap;
//    }

}
