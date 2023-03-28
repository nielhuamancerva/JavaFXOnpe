/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package onpe.com.pe.transmisionscore.core.repository.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author NHuaman
 */
public interface ServicePostgreSQL {
    public Connection conexionPostgreSQL() throws SQLException;
    
    public ResultSet select(String Query)throws SQLException;
}
