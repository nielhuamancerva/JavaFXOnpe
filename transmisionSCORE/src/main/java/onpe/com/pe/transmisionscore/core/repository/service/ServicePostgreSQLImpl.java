/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onpe.com.pe.transmisionscore.core.repository.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author NHuaman
 */
public class ServicePostgreSQLImpl implements ServicePostgreSQL{

    private static final String URL = "jdbc:postgresql://localhost:5432/SCE";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";
    private Connection connection;
    
    @Override
    public Connection conexionPostgreSQL() throws SQLException{
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }

    @Override
    public ResultSet select(String Query) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(Query);
        return stmt.executeQuery();
    }
    
    
    
}
