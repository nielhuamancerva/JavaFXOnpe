package com.mycompany.loging.score.negocio.service;

import com.mycompany.loging.score.model.Actas;
import com.mycompany.loging.score.model.Component;
import com.mycompany.loging.score.model.Imagenes;
import com.mycompany.loging.score.model.Setting;
import com.mycompany.loging.score.model.Transmision;
import java.io.File;
import java.io.IOException;
import javafx.collections.ObservableList;
import org.bson.Document;

/**
 *
 * @author LMedina
 */
public interface NegocioService {

    public Document consultaUsuarioDb(String username, String password) throws IOException, Exception;

    public ObservableList<Actas> finAllActas() throws IOException, Exception;

    public ObservableList<Setting> finAllSetting() throws IOException, Exception;

    public Actas finByCodigoBarra(String codigoBarra) throws IOException, Exception;

    public String uploadFileOnMemory(File fileSelected);

    public Transmision uploadActaReadOnMemory(Actas ActaReadOnMemory) throws IOException, Exception;

    public void readAndCutBarcode(Integer cordenadaX, Integer cordenadaY, Integer cordenadaAnchoW, Integer cordenadaAltoH) throws IOException, Exception;

    public void readAndCutOrganizationsPolitical(Integer cordenadaX, Integer cordenadaY, Integer cordenadaAnchoW, Integer cordenadaAltoH) throws IOException, Exception;

    public void readAndCutObservations(Integer cordenadaX, Integer cordenadaY, Integer cordenadaAnchoW, Integer cordenadaAltoH) throws IOException, Exception;

    public Boolean readAndCutsignature(String nameCandidate, Integer cordenadaX, Integer cordenadaY, Integer cordenadaAnchoW, Integer cordenadaAltoH) throws IOException, Exception;

    public void loadSettingActa() throws Exception;

    public void readAndCutHoraInicio(String nameFile, Integer cordenadaX, Integer cordenadaY, Integer cordenadaAnchoW, Integer cordenadaAltoH) throws IOException, Exception;

    public Setting finAllSettingByName(String name) throws IOException, Exception;

    public ObservableList<Setting> finSettingByname(String name) throws IOException, Exception;

    public ObservableList<String> findAllSectionsOnCorrdinates(String idSecciont) throws Exception;

    public ObservableList<Component> finAllComponent() throws IOException, Exception;
}
