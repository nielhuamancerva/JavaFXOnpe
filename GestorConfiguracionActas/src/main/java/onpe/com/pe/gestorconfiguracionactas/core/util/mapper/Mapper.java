/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onpe.com.pe.gestorconfiguracionactas.core.util.mapper;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import onpe.com.pe.gestorconfiguracionactas.core.model.Sections;
import onpe.com.pe.gestorconfiguracionactas.core.model.Setting;
import org.bson.Document;

/**
 *
 * @author NHuaman
 */
public class Mapper {

    public static Document actaCastToDocument(Setting ss) {
        Document document = new Document();
        document.append("id_setting", ss.getId_setting());
        document.append("name", ss.getName());
        document.append("setting", ss.getSetting());
        document.append("statusSetting", ss.getStatusSetting());
        return document;
    }

    public static Document sectionsCastToDocument(Sections ss) {
        Document document = new Document();
        document.append("setting_id", ss.getSetting_id());
        document.append("coordinates", "");
        return document;
    }

    public static ObservableList<Setting> actaCastToDocument(FindIterable<Document> listDocument) {
        List<Setting> listSetting = new ArrayList();

        if (listDocument.iterator().hasNext()) {
            // Recorrer todos los documentos en el FindIterable<Document>
            for (Document document : listDocument) {
                // Realizar alguna acción con cada documento, por ejemplo:
                Setting setting = new Setting();
                setting.setId_setting(document.getString("id_setting"));
                setting.setName(document.getString("name"));
                setting.setSetting(document.getString("setting"));
                setting.setStatusSetting(document.getString("statusSetting"));
                listSetting.add(setting);
            }
        }
        return FXCollections.observableArrayList(listSetting);
    }

    public static ObservableList<Sections> sectionCastToDocument(FindIterable<Document> listDocument) {
        List<Sections> listSetting = new ArrayList();

        if (listDocument.iterator().hasNext()) {
            // Recorrer todos los documentos en el FindIterable<Document>
            for (Document document : listDocument) {
                // Realizar alguna acción con cada documento, por ejemplo:
                Sections setting = new Sections();
                listSetting.add(setting);
            }
        }
        return FXCollections.observableArrayList(listSetting);
    }
}
