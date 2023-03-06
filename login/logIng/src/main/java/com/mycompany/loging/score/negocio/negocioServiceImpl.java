/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loging.score.negocio;

import com.mycompany.loging.score.Repository.Mongo.service.ConexionMongo;
import com.mycompany.loging.score.Repository.Mongo.ConexionMongoImpl;
import com.mycompany.loging.score.model.Actas;
import java.io.IOException;
import org.bson.Document;
import com.mycompany.loging.score.negocio.service.NegocioService;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author LMedina
 */
public class NegocioServiceImpl implements NegocioService {

    private final ConexionMongo conexionMongo;

    public NegocioServiceImpl() {
        this.conexionMongo = new ConexionMongoImpl();
    }

    @Override
    public Document consultaUsuarioDb(String username, String password) throws IOException, Exception {

        conexionMongo.conexionMongo();
        return conexionMongo.findCollection(username, password);
    }

    @Override
    public ObservableList<Actas> finAllActas() throws IOException, Exception {
        List<Actas> listActas = new ArrayList<>();
        conexionMongo.conexionMongo();
        conexionMongo.findAllCollecion("actas").find()
                .forEach(action -> {
                    Actas acta = new Actas();
                    acta.setActa(action.getString("acta"));
                    acta.setDepartamento(action.getString("departamento"));
                    acta.setProvincia(action.getString("provincia"));
                    acta.setDistrito(action.getString("distrito"));
                    listActas.add(acta);
                });

        return FXCollections.observableArrayList(listActas);
    }

    @Override
    public Actas finByCodigoBarra(String codigoBarra) throws IOException, Exception {
        Actas acta = new Actas();
        conexionMongo.conexionMongo();
        Document ss = conexionMongo.findActaByCodigoBarra(codigoBarra);
        acta.setActa(ss.getString("acta"));
        acta.setDepartamento(ss.getString("departamento"));
        acta.setProvincia(ss.getString("provincia"));
        acta.setDistrito(ss.getString("distrito"));
        return acta;
    }

}
