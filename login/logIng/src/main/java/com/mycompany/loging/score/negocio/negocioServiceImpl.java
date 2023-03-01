/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loging.score.negocio;

import com.mycompany.loging.score.Repository.Mongo.service.ConexionMongo;
import com.mycompany.loging.score.Repository.Mongo.ConexionMongoImpl;
import java.io.IOException;
import org.bson.Document;
import com.mycompany.loging.score.negocio.service.NegocioService;

/**
 *
 * @author LMedina
 */
public class NegocioServiceImpl implements NegocioService{
    private final ConexionMongo conexionMongo;
  
    public NegocioServiceImpl() {
        this.conexionMongo = new ConexionMongoImpl();
    }

    @Override
    public Document consultaUsuarioDb(String username, String password) throws IOException, Exception{
        
        conexionMongo.conexionMongo();
        return conexionMongo.findCollection(username, password);
 }
    
}
