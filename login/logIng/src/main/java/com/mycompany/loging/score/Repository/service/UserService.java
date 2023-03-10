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
