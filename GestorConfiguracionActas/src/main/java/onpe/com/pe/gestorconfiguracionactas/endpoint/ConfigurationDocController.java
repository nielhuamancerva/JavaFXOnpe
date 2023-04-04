package onpe.com.pe.gestorconfiguracionactas.endpoint;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    private VBox containerSettingModule, containerDocumentsModule;

    ArrayList<Integer> list = new ArrayList<>();
    ArrayList<String> listCount = new ArrayList<>();
    private TextField[] buttonEventConfi = new TextField[1];
    Button[] buttonEventAdd = new Button[1];
    Button[] buttonEventEdit = new Button[1];
    Button[] buttonEventDelete = new Button[1];

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {

        try {
            Label[] labelText = new Label[businessService.findAllSections().size()];
            for (int i = 0; i < businessService.findAllSections().size(); i++) {
                Button buttonEventDocumentDelete = new Button();
                buttonEventDocumentDelete.setId("buttonAdd" + i);
                buttonEventDocumentDelete.setLayoutX(10);
                buttonEventDocumentDelete.getStylesheets().add(getClass().getResource("/onpe/com/pe/styles/Style.css").toExternalForm());
                buttonEventDocumentDelete.getStyleClass().add("button-eliminar");

                Button buttonEventDocumentEdit = new Button();
                buttonEventDocumentEdit.setId("buttonAdd" + i);
                buttonEventDocumentEdit.setLayoutX(60);
                buttonEventDocumentEdit.getStylesheets().add(getClass().getResource("/onpe/com/pe/styles/Style.css").toExternalForm());
                buttonEventDocumentEdit.getStyleClass().add("button-editar");

                labelText[i] = new Label(businessService.findAllSections().get(i));
                labelText[i].setLayoutX(130);
                labelText[i].getStylesheets().add(getClass().getResource("/onpe/com/pe/styles/Style.css").toExternalForm());
                labelText[i].getStyleClass().add("text-initializa");
                AnchorPane conteninerDocuments = new AnchorPane();
                conteninerDocuments.getChildren().addAll(labelText[i], buttonEventDocumentDelete, buttonEventDocumentEdit);

                containerDocumentsModule.getChildren().addAll(conteninerDocuments);
                containerDocumentsModule.setMargin(conteninerDocuments, new Insets(10, 0, 0, 0));

                String name = businessService.findAllSections().get(i);
                buttonEventDocumentDelete.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            businessService.deleteOneSections(name);
                            App.setRoot(null, "configurationDoc");
                        } catch (IOException ex) {
                            Logger.getLogger(ConfigurationDocController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(ConfigurationDocController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

                buttonEventDocumentEdit.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            Gson gson = new Gson();
                            String[] arrString = gson.fromJson(businessService.findAllSections1(name).get(0), String[].class);
                            System.out.println(arrString);
                            containerSettingModule.getChildren().clear();
                            titleDocumentSetting.setText(name);
                            titleDocumentSetting.setDisable(false);
                            for (int i = 0; i < arrString.length; i++) {
                                TextField textField = new TextField(String.valueOf(i));
                                textField.setId(String.valueOf(i));
                                textField.setDisable(true);
                                textField.setLayoutX(10);

                                textField.getStyleClass().add("button-initializa");

                                Button button = new Button();
                                button.setId("buttonAdd" + i);
                                button.setLayoutX(410);
                                button.getStylesheets().add(getClass().getResource("/onpe/com/pe/styles/Style.css").toExternalForm());
                                button.setDisable(true);
                                button.getStyleClass().add("button-guardar");

                                Button buttonde = new Button();
                                buttonde.setId("buttonAdd" + i);
                                buttonde.setLayoutX(370);
                                buttonde.getStylesheets().add(getClass().getResource("/onpe/com/pe/styles/Style.css").toExternalForm());
                                buttonde.getStyleClass().add("button-eliminar");

                                Button buttonedit = new Button();
                                buttonedit.setId("buttonAdd" + i);
                                buttonedit.setLayoutX(450);
                                buttonedit.getStylesheets().add(getClass().getResource("/onpe/com/pe/styles/Style.css").toExternalForm());
                                buttonedit.getStyleClass().add("button-editar");

                                AnchorPane conteninerTextFieldAndButton = new AnchorPane();
                                conteninerTextFieldAndButton.getChildren().addAll(textField, buttonde, buttonedit, button);

                                containerSettingModule.getChildren().addAll(conteninerTextFieldAndButton);
                                containerSettingModule.setMargin(conteninerTextFieldAndButton, new Insets(10, 0, 0, 0));

                                buttonedit.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        listCount.add(textField.getText());
                                        button.setDisable(false);
                                        textField.setDisable(false);
                                    }
                                });

                                button.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        listCount.add(textField.getText());
                                        button.setDisable(true);
                                        textField.setDisable(true);
                                    }
                                });

                                btnGuardar.setDisable(false);
                            }

                        } catch (Exception ex) {
                            Logger.getLogger(ConfigurationDocController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(GestorActaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void actionAddModulos(ActionEvent event) throws Exception {

        for (int i = 0; i < buttonEventConfi.length; i++) {
            buttonEventConfi[i] = new TextField(String.valueOf(list.size()));
            buttonEventConfi[i].setId(String.valueOf(list.size()));

            buttonEventConfi[i].setLayoutX(10);
//          buttonEventConfi[i].getStylesheets().add(getClass().getResource("/onpe/com/pe/styles/Style.css").toExternalForm());
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
            Button btDelete = buttonEventDelete[i];
            Button btEdit = buttonEventEdit[i];
            Button btAdds = buttonEventAdd[i];

            buttonEventAdd[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    listCount.add(textField.getText());
                    btAdds.setDisable(true);
                    textField.setDisable(true);
                    if (Integer.parseInt(textField.getId()) == list.size() - 1) {
                        btnGuardar.setDisable(false);
                    }
                }
            });

            ////falta eliminar el que ha sido guardado
            buttonEventEdit[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    listCount.add(textField.getText());
                    btAdds.setDisable(false);
                    textField.setDisable(false);
                }
            });

            buttonEventDelete[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    conteninerTextFieldAndButton.getChildren().removeAll(textField,btDelete,btEdit, btAdds);
                    containerSettingModule.getChildren().remove(conteninerTextFieldAndButton);
                }
            });

        }

        list.add(1);
    }

    @FXML
    private void createNewSetting(ActionEvent event) {
        titleDocumentSetting.setDisable(false);
    }

    @FXML
    private void actionRegresar(ActionEvent event) throws IOException {
        App.setRoot(null, "inicioMenu");
    }

    @FXML
    private void actionGuardar(ActionEvent event) throws Exception {
        setting.setId_setting(UUID.randomUUID().toString());
        setting.setName(titleDocumentSetting.getText());
        setting.setStatusSetting("0");
        Gson gson = new Gson();
        setting.setSetting(gson.toJson(listCount));
        businessService.saveSetting(setting);
        App.setRoot(null, "configurationDoc");
    }

    @FXML
    private void actionContinuar() throws IOException {

        App.setRoot(null, "configuraSecciones");
    }

}
