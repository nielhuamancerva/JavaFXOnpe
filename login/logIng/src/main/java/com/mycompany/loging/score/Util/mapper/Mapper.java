/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loging.score.util.mapper;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mycompany.loging.score.model.Actas;
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

    public Actas castActas(Document ss) {
        Actas acta = new Actas();
        acta.setActa(ss.getString("acta"));
        acta.setDepartamento(ss.getString("departamento"));
        acta.setProvincia(ss.getString("provincia"));
        acta.setDistrito(ss.getString("distrito"));
        return acta;

    }
}
