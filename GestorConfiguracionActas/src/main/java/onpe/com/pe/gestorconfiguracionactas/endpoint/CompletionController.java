 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onpe.com.pe.gestorconfiguracionactas.endpoint;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import onpe.com.pe.gestorconfiguracionactas.App;
import static onpe.com.pe.gestorconfiguracionactas.core.util.VariableGlobales.arrayNombresModulos;
import static onpe.com.pe.gestorconfiguracionactas.core.util.VariableGlobales.listCount;

import onpe.com.pe.gestorconfiguracionactas.core.util.commonmappings.CommonMappings;

/**
 * FXML Controller class
 *
 * @author CASSHERN
 */
public class CompletionController implements Initializable {

    @FXML
    VBox conteninerSettingButton, conteninerSettingButtonAdd, conteninerSettingButtonDelete;
    @FXML
    TextField textFieldEleccion;
    @FXML
    Button btnAddTitle, btnProcesar, btnRegresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO ingresarInicializador
//        btnAddTitle.setDisable(true);
//        btnProcesar.setDisable(true);
//        textFieldEleccion.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
//            if (!CommonMappings.valiadarSoloNumeros(keyEvent.getCharacter())) {
//                keyEvent.consume();
//            }
//            if (CommonMappings.valiadarSoloNumeros(keyEvent.getCharacter())) {
//                btnAddTitle.setDisable(false);
//                
//            }
//        });
    }

    @FXML
    private void funcionProcesar() throws IOException, Exception {
        App.setRoot(null, "inicioMenu");
    }

    @FXML
    private void actionAddTitle(ActionEvent event) throws Exception {

    }

    @FXML
    private void regresaMenu() throws IOException {

        App.setRoot(null, "inicioMenu");

    }
    TextField[] buttonEventConfiini = new TextField[0];
     ArrayList<Integer> list = new ArrayList<>();

  

    @FXML
    private void actionAddModulos(ActionEvent event) throws Exception {

        TextField[] buttonEventConfi = new TextField[1];
        Button[] buttonEventAdd = new Button[1];

        for (int i = 0; i < buttonEventConfi.length; i++) {
            buttonEventConfi[i] = new TextField(String.valueOf(list.size()));
            buttonEventConfi[i].setId(String.valueOf(list.size()));
            buttonEventConfi[i].getStylesheets().add(getClass().getResource("/onpe/com/pe/styles/Style.css").toExternalForm());
            buttonEventConfi[i].getStyleClass().add("button-initializa");
            conteninerSettingButton.getChildren().addAll(buttonEventConfi[i]);
            conteninerSettingButton.setMargin(buttonEventConfi[i], new Insets(10, 0, 0, 0));

            buttonEventAdd[i] = new Button("+");
            buttonEventAdd[i].setId("buttonAdd" + i);
            buttonEventAdd[i].getStylesheets().add(getClass().getResource("/onpe/com/pe/styles/Style.css").toExternalForm());
            buttonEventAdd[i].getStyleClass().add("button-initializa-button-add");
            conteninerSettingButtonAdd.setMargin(buttonEventAdd[i], new Insets(10, 0, 0, 0));
            conteninerSettingButtonAdd.getChildren().addAll(buttonEventAdd[i]);

        }

        for (int u = 0; u < buttonEventConfi.length; u++) {

            TextField textField = buttonEventConfi[u];
            Button btAdds = buttonEventAdd[u];


            buttonEventAdd[u].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    listCount.add(textField.getText());
                  //  insertar(textField.getText(), uu);
                    btAdds.setDisable(true);
//                    textField.setDisable(true);
//                    if (uu == buttonEventConfi.length - 1) {
//                        btnProcesar.setDisable(false);
//                    }
                }

            });

        }
//        btnAddTitle.setDisable(true);
//        textFieldEleccion.setDisable(true);
//        btnRegresar.setDisable(true);
list.add(1);
    }

    @FXML
    private void limpiarModulo() throws IOException {
        App.setRoot(null, "completion");
    }


}
