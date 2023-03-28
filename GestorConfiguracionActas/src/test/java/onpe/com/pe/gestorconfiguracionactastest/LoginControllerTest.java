/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onpe.com.pe.gestorconfiguracionactastest;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import org.junit.Before;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

/**
 *
 * @author NHuaman
 */
public class LoginControllerTest {


    
    @Before
    public void setUp() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        Parent root = loader.load();
         
    }

    @Test
    public void testButtonClick() {
       // loginController.getPasswordField();
       // assertNotNull(button);
        // Aquí puedes agregar la lógica de prueba para verificar el comportamiento del botón
    }
}
