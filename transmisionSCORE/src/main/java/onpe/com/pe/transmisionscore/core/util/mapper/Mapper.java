/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onpe.com.pe.transmisionscore.core.util.mapper;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import onpe.com.pe.transmisionscore.core.model.Actas;
import onpe.com.pe.transmisionscore.core.model.Imagenes;
import onpe.com.pe.transmisionscore.core.model.Transmision;
import onpe.com.pe.transmisionscore.core.model.TransmisionHeader;
import onpe.com.pe.transmisionscore.core.util.common.commonMappings;
import onpe.com.pe.transmisionscore.core.util.constanst.VariableGlobales;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.Document;

/**
 *
 * @author NHuaman
 */
public class Mapper {

    public ObservableList<Actas> castObservableListOfActas(FindIterable<Document> arra) {
        List<Actas> listActas = new ArrayList<>();
        arra.forEach(action -> {
            Actas acta = new Actas();
            acta.setActa(action.getString("acta"));
            acta.setDepartamento(action.getString("departamento"));
            acta.setProvincia(action.getString("provincia"));
            acta.setDistrito(action.getString("distrito"));
            listActas.add(acta);
        });
        return FXCollections.observableArrayList(listActas);
    }

    public Actas documentCastToActas(Document ss) {
        Actas acta = new Actas();
        acta.setActa(ss.getString("acta"));
        acta.setDepartamento(ss.getString("departamento"));
        acta.setProvincia(ss.getString("provincia"));
        acta.setDistrito(ss.getString("distrito"));
        acta.setId_acta(ss.getString("id_acta"));
        return acta;
    }

    public Imagenes documentCastToImagen(Document ss) {
        Imagenes imagen = new Imagenes();
        imagen.setActa_id(ss.getString("acta_id"));
        imagen.setImagen(ss.getString("imagen"));
        return imagen;
    }

    public Document actaCastToDocument(Actas ss) {
        Document document = new Document();
        document.append("acta", ss.getActa());
        document.append("departamento", ss.getDepartamento());
        document.append("provincia", ss.getProvincia());
        document.append("distrito", ss.getDistrito());
        document.append("observaciones", ss.getObservaciones());
        document.append("lista1", ss.getLista1());
        document.append("firma1", ss.getFirma1());
        document.append("firma2", ss.getFirma2());
        document.append("firma3", ss.getFirma3());
        return document;
    }

    public Document imagenCastToDocument(Imagenes ss) throws IOException {

        Document document = new Document();
        document.append("acta_id", ss.getActa_id());
        document.append("imagen", commonMappings.convertFileToBase64());
        return document;
    }

    public Transmision dtoToTransmision(Actas ss) {

        TransmisionHeader header = new TransmisionHeader();
        
        Transmision transmision = new Transmision();
        transmision.setHeader(header);
    
        transmision.setBody(ss);

        return transmision;
    }
}
