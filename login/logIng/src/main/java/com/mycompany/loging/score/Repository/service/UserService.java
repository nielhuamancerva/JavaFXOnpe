/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.loging.score.Repository.service;

import java.io.IOException;
import org.bson.Document;

/**
 *
 * @author CASSHERN
 */
public interface UserService {
    public Document findByUsernameAndPassword(String username, String password) throws IOException, Exception;
}
