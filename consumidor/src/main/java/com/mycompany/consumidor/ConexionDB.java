package com.mycompany.consumidor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author NHuaman
 */
public class ConexionDB {

    public Connection inicializarConexionDB() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/nombre_de_la_base_de_datos";
        String usuario = "tu_usuario";
        String contrasena = "tu_contrasena";

        Connection conexion = DriverManager.getConnection(url, usuario, contrasena);

        return conexion;
    }
    
       public String findTable() {
           return "";
       }
}
