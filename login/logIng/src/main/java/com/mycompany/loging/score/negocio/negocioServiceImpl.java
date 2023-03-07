/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loging.score.negocio;

import com.mongodb.client.MongoCollection;
import com.mycompany.loging.score.Repository.FactoryServiciosExternos;
import com.mycompany.loging.score.Repository.service.ConexionMongo;
import com.mycompany.loging.score.Repository.implementacion.ConexionMongoImpl;
import com.mycompany.loging.score.model.Actas;
import java.io.IOException;
import org.bson.Document;
import com.mycompany.loging.score.negocio.service.NegocioService;
import com.mycompany.loging.score.util.mapper.Mapper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
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
        factoryservices.MongoService().conexionMongo();
        return mapping.castActas(factoryservices.MongoService().findActaByCodigoBarra(codigoBarra));
    }

}
