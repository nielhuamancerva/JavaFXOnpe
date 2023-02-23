package com.mycompany.mavenproject2;



import java.io.IOException;
import javafx.fxml.FXML;
import com.mycompany.;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
        
    }
}
