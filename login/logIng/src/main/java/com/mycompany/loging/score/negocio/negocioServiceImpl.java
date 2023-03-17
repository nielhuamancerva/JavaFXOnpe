package com.mycompany.loging.score.negocio;

import com.mongodb.client.MongoCollection;
import com.mycompany.loging.score.Repository.FactoryServiciosExternos;
import com.mycompany.loging.score.model.Actas;
import com.mycompany.loging.score.model.Imagenes;
import com.mycompany.loging.score.model.Transmision;
import java.io.IOException;
import org.bson.Document;
import com.mycompany.loging.score.negocio.service.NegocioService;
import com.mycompany.loging.score.util.common.commonMappings;
import com.mycompany.loging.score.util.constanst.Constansts;
import com.mycompany.loging.score.util.constanst.VariableGlobales;
import com.mycompany.loging.score.util.mapper.Mapper;
import java.io.File;
import java.util.HashMap;
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

    @Override
    public Document consultaUsuarioDb(String username, String password) throws IOException, Exception {
        factoryservices = FactoryServiciosExternos.getInstance();
        return factoryservices.UserService().findByUsernameAndPassword(username, password);
    }

    @Override
    public ObservableList<Actas> finAllActas() throws IOException, Exception {
        factoryservices = FactoryServiciosExternos.getInstance();
        factoryservices.MongoService().conexionMongo();
        return mapping.castObservableListOfActas(factoryservices.MongoService().findAllCollecion("actas").find());
    }

    @Override
    public Actas finByCodigoBarra(String codigoBarra) throws IOException, Exception {
        factoryservices = FactoryServiciosExternos.getInstance();
        Actas acta = mapping.documentCastToActas(factoryservices.UserService().findImageById(codigoBarra));
        Imagenes imagen = mapping.documentCastToImagen(factoryservices.ImageServiceImpl().findImageById(acta.getId_acta()));
        acta.setImagen(imagen);
        return acta;
    }

    @Override
    public String uploadFileOnMemory(File fileSelected) {
        VariableGlobales.lecturaActasEnMemoria = new HashMap();
        VariableGlobales.lecturaActasEnMemoria.put("lecturaPrimera", "SI");
        VariableGlobales.lecturaActasEnMemoria.put("pathTesseract", Constansts.PATH_TESSERACT);
        VariableGlobales.lecturaActasEnMemoria.put("fileNamePathOriginal", fileSelected.getPath());
        VariableGlobales.lecturaActasEnMemoria.put("fileNamePath", commonMappings.pathOfFile(fileSelected));
        VariableGlobales.lecturaActasEnMemoria.put("fileName", fileSelected.getName());
        VariableGlobales.lecturaActasEnMemoria.put("fileNameSinExtension", commonMappings.nameOfFileWithoutExtension(fileSelected));
        return fileSelected.getPath();
    }

    @Override
    public void leerCodigoDeBarras(Integer CoordenaX, Integer CoordenaY, Integer Ancho, Integer Alto) throws IOException, Exception {
        factoryservices = FactoryServiciosExternos.getInstance();
        factoryservices.Tess4jServiceImpl().leerCodigoDeBarras(CoordenaX, CoordenaY, Ancho, Alto);
    }

    @Override
    public Transmision uploadActaReadOnMemory(Actas actaReadOnMemory) throws IOException, Exception {
        factoryservices = FactoryServiciosExternos.getInstance();
        factoryservices.ImageServiceImpl()
                .saveImage(mapping.imagenCastToDocument(actaReadOnMemory.getImagen()));
        factoryservices.ActaServiceImpl().saveImage(mapping.actaCastToDocument(actaReadOnMemory));
        return mapping.dtoToTransmision(actaReadOnMemory);
    }

}
