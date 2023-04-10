/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loging.score.util.mapper;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mycompany.loging.score.model.Actas;
import com.mycompany.loging.score.model.Imagenes;
import com.mycompany.loging.score.model.Sections;
import com.mycompany.loging.score.model.Setting;
import com.mycompany.loging.score.model.Transmision;
import com.mycompany.loging.score.model.TransmisionHeader;
import com.mycompany.loging.score.util.common.CommonMappings;
import com.mycompany.loging.score.util.constanst.VariableGlobals;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.Document;

/**
 *
 * @author NHuaman
 */
public class Mappers {

    public static Document sectionsCastToDocument(Sections ss) {
        Document document = new Document();
        document.append("setting_id", ss.getSetting_id());
        document.append("coordinates", "");
        return document;
    }

    public ObservableList<Actas> castObservableListOfActas(FindIterable<Document> arra) {
        List<Actas> listActas = new ArrayList<>();
        arra.forEach(action -> {
            Actas acta = new Actas();
            acta.setActa(action.getString("acta"));
            acta.setDepartamento(action.getString("departamento"));
            acta.setFecha_registro(action.getString("fecha_registro"));
            acta.setEstado(action.getString("estado"));
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
        document.append("estado", "Invalido");
        document.append("fecha_registro", LocalDate.now().toString());
        if (ss.getFirma1().equals("true") && ss.getFirma2().equals("true") && ss.getFirma3().equals("true")) {
            document.append("estado", "Valido");
        }
        return document;
    }

    public Document imagenCastToDocument(Imagenes ss) throws IOException {

        Document document = new Document();
        document.append("acta_id", ss.getActa_id());
        document.append("imagen", CommonMappings.convertFileToBase64());
        return document;
    }

    public Transmision dtoToTransmision(Actas ss) {

        TransmisionHeader header = new TransmisionHeader();

        Transmision transmision = new Transmision();
        transmision.setHeader(header);

        transmision.setBody(ss);

        return transmision;
    }

    public ObservableList<Setting> castObservableListOfSetting(FindIterable<Document> arra) {
        List<Setting> listSetting = new ArrayList<>();
        arra.forEach(action -> {
            Setting setting = new Setting();
            setting.setId_setting(action.getString("id_setting"));
            setting.setName(action.getString("name"));
            setting.setSetting(action.getString("setting"));
            setting.setStatusSetting(action.getString("statusSetting"));
            listSetting.add(setting);
        });
        return FXCollections.observableArrayList(listSetting);
    }

    public Setting documentCastTSetting(Document object) {
        Setting setting = new Setting();
        setting.setId_setting(object.getString("id_setting"));
        setting.setName(object.getString("name"));
        setting.setSetting(object.getString("setting"));
        setting.setStatusSetting(object.getString("provincia"));
        return setting;
    }

    public static ObservableList<Sections> sectionCastToDocument(FindIterable<Document> listDocument) {
        List<Sections> listSetting = new ArrayList();

        if (listDocument.iterator().hasNext()) {
            // Recorrer todos los documentos en el FindIterable<Document>
            for (Document document : listDocument) {
                // Realizar alguna acción con cada documento, por ejemplo:
                Sections setting = new Sections();
                setting.setSetting_id(document.getString("setting_id"));
                setting.setCoordinates(document.getString("coordinates"));
                listSetting.add(setting);
            }
        }
        return FXCollections.observableArrayList(listSetting);
    }

    public static Integer transformaTointerger(String ss) {

        double number = Double.parseDouble(ss);
        int integer = (int) Math.floor(number);
        return integer;
    }
}
