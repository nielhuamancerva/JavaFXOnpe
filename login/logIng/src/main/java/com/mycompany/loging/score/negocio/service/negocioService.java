package com.mycompany.loging.score.negocio.service;

import com.mycompany.loging.score.model.Actas;
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

    public Actas finByCodigoBarra(String codigoBarra) throws IOException, Exception;

    public void leerCodigoDeBarras(Integer CoordenaX, Integer CoordenaY, Integer Ancho, Integer Alto) throws IOException, Exception;

    public String uploadFileOnMemory(File fileSelected);
}
