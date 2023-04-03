package onpe.com.pe.gestorconfiguracionactas.endpoint;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import onpe.com.pe.gestorconfiguracionactas.App;
import onpe.com.pe.gestorconfiguracionactas.core.business.BusinessService;
import onpe.com.pe.gestorconfiguracionactas.core.business.Impl.BusinessServiceImpl;
import onpe.com.pe.gestorconfiguracionactas.core.model.Setting;

/**
 * FXML Controller class
 *
 * @author rdela
 */
public class ConfigurationDocController implements Initializable {

    private final BusinessService businessService;
    private Setting setting = new Setting();

    public ConfigurationDocController() {
        this.businessService = new BusinessServiceImpl();
    }

    @FXML
    private Button btnRegresar, btnGuardar, ico_activar;

    @FXML
    private Button btnAddDocumentos;

    @FXML
    private TextField titleDocumentSetting;

    @FXML
    private VBox containerSettingModule;

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    TextField[] buttonEventConfiini = new TextField[0];
    ArrayList<Integer> list = new ArrayList<>();
    ArrayList<String> listCount = new ArrayList<>();
    TextField[] buttonEventConfi = new TextField[1];
    Button[] buttonEventAdd = new Button[1];
    Button[] buttonEventEdit = new Button[1];
    Button[] buttonEventDelete = new Button[1];

    @FXML
    private void actionAddModulos(ActionEvent event) throws Exception {

        for (int i = 0; i < buttonEventConfi.length; i++) {
            buttonEventConfi[i] = new TextField(String.valueOf(list.size()));
            buttonEventConfi[i].setId(String.valueOf(list.size()));

            buttonEventConfi[i].setLayoutX(10);
//            buttonEventConfi[i].getStylesheets().add(getClass().getResource("/onpe/com/pe/styles/Style.css").toExternalForm());
            buttonEventConfi[i].getStyleClass().add("button-initializa");

            buttonEventAdd[i] = new Button();
            buttonEventAdd[i].setId("buttonAdd" + i);
            buttonEventAdd[i].setLayoutX(410);
            buttonEventAdd[i].getStylesheets().add(getClass().getResource("/onpe/com/pe/styles/Style.css").toExternalForm());
            buttonEventAdd[i].getStyleClass().add("button-guardar");

            buttonEventDelete[i] = new Button();
            buttonEventDelete[i].setId("buttonAdd" + i);
            buttonEventDelete[i].setLayoutX(370);
            buttonEventDelete[i].getStylesheets().add(getClass().getResource("/onpe/com/pe/styles/Style.css").toExternalForm());
            buttonEventDelete[i].getStyleClass().add("button-eliminar");

            buttonEventEdit[i] = new Button();
            buttonEventEdit[i].setId("buttonAdd" + i);
            buttonEventEdit[i].setLayoutX(450);
            buttonEventEdit[i].getStylesheets().add(getClass().getResource("/onpe/com/pe/styles/Style.css").toExternalForm());
            buttonEventEdit[i].getStyleClass().add("button-editar");

            AnchorPane conteninerTextFieldAndButton = new AnchorPane();
            conteninerTextFieldAndButton.getChildren().addAll(buttonEventConfi[i], buttonEventDelete[i], buttonEventEdit[i], buttonEventAdd[i]);

            containerSettingModule.getChildren().addAll(conteninerTextFieldAndButton);
            containerSettingModule.setMargin(conteninerTextFieldAndButton, new Insets(10, 0, 0, 0));

            
                        TextField textField = buttonEventConfi[i];
            Button btAdds = buttonEventAdd[i];

            buttonEventAdd[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    listCount.add(textField.getText());
                    //  insertar(textField.getText(), uu);
                    btAdds.setDisable(true);
                    textField.setDisable(true);
//                    if (uu == buttonEventConfi.length - 1) {
//                        btnProcesar.setDisable(false);
//                    }
                }

            });
        }



        btnRegresar.setDisable(true);
        list.add(1);
    }

    @FXML
    private void createNewSetting(ActionEvent event) {
        titleDocumentSetting.setDisable(false);
    }

    @FXML
    private void actionRegresar(ActionEvent event) {
    }

    @FXML
    private void actionGuardar(ActionEvent event) throws Exception {
        setting.setId_setting(UUID.randomUUID().toString());
        setting.setName(titleDocumentSetting.getText());
        setting.setStatusSetting("0");
        Gson gson = new Gson();
        setting.setSetting(gson.toJson(listCount));
        businessService.saveSetting(setting);
    }

    @FXML
    private void actionContinuar() throws IOException {

//        App.setRoot(null, "configuraSecciones");
    }

}
