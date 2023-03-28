/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onpe.com.pe.transmisionscore.core.repository;

import onpe.com.pe.transmisionscore.core.repository.service.ServicePostgreSQL;
import onpe.com.pe.transmisionscore.core.repository.service.ServicePostgreSQLImpl;

/**
 *
 * @author NHuaman
 */
public class FactoryServices {

    private static FactoryServices InstanceFactory;
    private final ServicePostgreSQL servicePostgreSQL;

    private FactoryServices() {
        this.servicePostgreSQL = new ServicePostgreSQLImpl();
    }

    public static FactoryServices getInstance() {
        if (InstanceFactory == null) {
            InstanceFactory = new FactoryServices();
        }
        return InstanceFactory;
    }

    public ServicePostgreSQL ServicePostgreSQL() {
        return this.servicePostgreSQL;
    }
}
