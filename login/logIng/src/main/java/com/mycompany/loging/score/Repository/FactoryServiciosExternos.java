/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loging.score.Repository;

import com.mycompany.loging.score.Repository.implementacion.ConexionMongoImpl;
import com.mycompany.loging.score.Repository.service.ConexionMongo;

/**
 *
 * @author NHuaman
 */
public class FactoryServiciosExternos {

    private static FactoryServiciosExternos instanceFactory;
    private ConexionMongo conexionMongo;

    private FactoryServiciosExternos() {
        this.conexionMongo =new ConexionMongoImpl();
    }

    public static FactoryServiciosExternos getInstance() {
        if (instanceFactory == null) {
            instanceFactory = new FactoryServiciosExternos();
        }
        return instanceFactory;
    }

    public ConexionMongo ConexionMongoService() {
        return this.conexionMongo;
    }
}
