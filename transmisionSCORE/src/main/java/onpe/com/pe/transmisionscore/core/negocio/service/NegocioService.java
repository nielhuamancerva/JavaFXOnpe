package onpe.com.pe.transmisionscore.core.negocio.service;

import onpe.com.pe.transmisionscore.core.model.Actas;
import onpe.com.pe.transmisionscore.core.model.Imagenes;
import onpe.com.pe.transmisionscore.core.model.Transmision;
import java.io.File;
import java.io.IOException;
import javafx.collections.ObservableList;
import onpe.com.pe.transmisionscore.core.model.TransmisionRecibidas;
import org.bson.Document;

/**
 *
 * @author LMedina
 */
public interface NegocioService {



    public ObservableList<TransmisionRecibidas> finAllActas() throws IOException, Exception;


}
