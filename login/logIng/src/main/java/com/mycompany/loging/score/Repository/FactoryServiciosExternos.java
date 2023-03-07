/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loging.score.Repository;

import com.mycompany.loging.score.Repository.implementacion.ConexionMongoImpl;
import com.mycompany.loging.score.Repository.implementacion.UserServiceImpl;
import com.mycompany.loging.score.Repository.service.ConexionMongo;
import com.mycompany.loging.score.Repository.service.UserService;

/**
 *
 * @author NHuaman
 */
public class FactoryServiciosExternos {

    private static FactoryServiciosExternos instanceFactory;
    private final ConexionMongo conexionMongo;
    private final UserService userService;

    private FactoryServiciosExternos() {
        this.conexionMongo = new ConexionMongoImpl();
        this.userService = new UserServiceImpl();
    }

    public static FactoryServiciosExternos getInstance() {
        if (instanceFactory == null) {
            instanceFactory = new FactoryServiciosExternos();
        }
        return instanceFactory;
    }

    public ConexionMongo MongoService() {
        return this.conexionMongo;
    }

    public UserService UserService() {
        return this.userService;
    }
}
