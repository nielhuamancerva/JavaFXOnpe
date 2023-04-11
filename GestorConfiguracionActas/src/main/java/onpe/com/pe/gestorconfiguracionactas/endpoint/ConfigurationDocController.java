package onpe.com.pe.gestorconfiguracionactas.endpoint;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import onpe.com.pe.gestorconfiguracionactas.App;
import onpe.com.pe.gestorconfiguracionactas.core.business.BusinessService;
import onpe.com.pe.gestorconfiguracionactas.core.business.Impl.BusinessServiceImpl;
import onpe.com.pe.gestorconfiguracionactas.core.model.Modules;
import onpe.com.pe.gestorconfiguracionactas.core.model.Setting;

public class ConfigurationDocController implements Initializable {

    private final BusinessService businessService;
    private Setting setting = new Setting();
    @FXML
    private ScrollPane DocumentosElectorales;
    @FXML
    private Button btnAddSeccion;

    public ConfigurationDocController() {
        this.businessService = new BusinessServiceImpl();
    }

    @FXML
    private Button btnRegresar, btnGuardar;

    @FXML
    private Button btnAddDocumentos;

    @FXML
    private TextField titleDocumentSetting;

    @FXML
    private VBox containerSettingModule, containerDocumentsModule;

    ArrayList<Integer> list = new ArrayList<>();

    String[] listTypeModule = {"Codigo Barra", "Hora", "Regiones", "Observaciones", "Firma"};
    ArrayList<Modules> listModule = new ArrayList<>();
    ArrayList<String> listCount = new ArrayList<>();
    TextField[] buttonEventConfi = new TextField[1];
    Button[] buttonEventAdd = new Button[1];
    Button[] buttonEventEdit = new Button[1];
    Button[] buttonEventDelete = new Button[1];

    TextField[] TextFieldEventEdit;
    Button[] buttonEditEventEdit;
    Button[] buttonAddEventEdit;
    Button[] buttonDeleteEventEdit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnGuardar.setDisable(true);
        btnAddSeccion.setDisable(true);
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
                            btnAddSeccion.setDisable(false);
                            Gson gson = new Gson();
                            Type listType = new TypeToken<List<Modules>>() {
                            }.getType();
                            listModule = gson.fromJson(businessService.findAllSections1(name).get(0), listType);
                            containerSettingModule.getChildren().clear();
                            titleDocumentSetting.setText(name);
                            titleDocumentSetting.setDisable(false);
                            TextFieldEventEdit = new TextField[listModule.size()];
                            buttonEditEventEdit = new Button[listModule.size()];
                            buttonAddEventEdit = new Button[listModule.size()];
                            buttonDeleteEventEdit = new Button[listModule.size()];
                            for (int i = 0; i < listModule.size(); i++) {
                                TextFieldEventEdit[i] = new TextField(listModule.get(i).getNameModule());
                                TextFieldEventEdit[i].setId(listModule.get(i).getCodemodule());
                                TextFieldEventEdit[i].setDisable(true);
                                TextFieldEventEdit[i].setLayoutX(10);

                                TextFieldEventEdit[i].getStyleClass().add("button-initializa");

                                buttonAddEventEdit[i] = new Button();
                                buttonAddEventEdit[i].setId("buttonAdd" + i);
                                buttonAddEventEdit[i].setLayoutX(410);
                                buttonAddEventEdit[i].getStylesheets().add(getClass().getResource("/onpe/com/pe/styles/Style.css").toExternalForm());
                                buttonAddEventEdit[i].setDisable(true);
                                buttonAddEventEdit[i].getStyleClass().add("button-guardar");

                                buttonDeleteEventEdit[i] = new Button();
                                buttonDeleteEventEdit[i].setId("buttonAdd" + i);
                                buttonDeleteEventEdit[i].setLayoutX(370);
                                buttonDeleteEventEdit[i].getStylesheets().add(getClass().getResource("/onpe/com/pe/styles/Style.css").toExternalForm());
                                buttonDeleteEventEdit[i].getStyleClass().add("button-eliminar");

                                buttonEditEventEdit[i] = new Button();
                                buttonEditEventEdit[i].setId("buttonAdd" + i);
                                buttonEditEventEdit[i].setLayoutX(450);
                                buttonEditEventEdit[i].getStylesheets().add(getClass().getResource("/onpe/com/pe/styles/Style.css").toExternalForm());
                                buttonEditEventEdit[i].getStyleClass().add("button-editar");

                                AnchorPane conteninerTextFieldAndButton = new AnchorPane();
                                conteninerTextFieldAndButton.getChildren().addAll(TextFieldEventEdit[i], buttonDeleteEventEdit[i], buttonEditEventEdit[i], buttonAddEventEdit[i]);

                                containerSettingModule.getChildren().addAll(conteninerTextFieldAndButton);
                                containerSettingModule.setMargin(conteninerTextFieldAndButton, new Insets(10, 0, 0, 0));
                                Button btAdds = buttonAddEventEdit[i];
                                TextField textField = TextFieldEventEdit[i];
                                buttonEditEventEdit[i].setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {

                                        btAdds.setDisable(false);
                                        textField.setDisable(false);
//                                          listCount.add(textField.getText());

                                    }
                                });

                                buttonAddEventEdit[i].setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {

                                        listModule.forEach(action -> {
                                            if (action.getCodemodule().equals(textField.getId())) {
                                                action.setNameModule(textField.getText());
                                            }
                                        });
                                        btAdds.setDisable(true);
                                        textField.setDisable(true);
                                        if (verificarButton()) {
                                            btnGuardar.setDisable(false);
                                        }
                                    }
                                });

                                buttonDeleteEventEdit[i].setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        conteninerTextFieldAndButton.getChildren().removeAll(textField, btAdds);
                                        containerSettingModule.getChildren().remove(conteninerTextFieldAndButton);
                                        listModule.removeIf(action -> action.getCodemodule().equals(textField.getId()));
                                        if (verificarButton()) {
                                            btnGuardar.setDisable(false);
                                        }
                                    }
                                });

                                if (i == 0) {
                                    buttonDeleteEventEdit[i].setVisible(false);
                                }
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

    String tipoModule = new String();

    @FXML
    private void actionAddModulos(ActionEvent event) throws Exception {
        tipoModule = "";
        Modules module = new Modules();
        if (listModule.isEmpty()) {
            module.setCodemodule(String.valueOf(listModule.size()));
            module.setTypeModule("Codigo Barra");
            module.setOrdenCreation(String.valueOf(0));
            buttonEventConfi[0] = new TextField("Codigo Barra");
            buttonEventConfi[0].setId(String.valueOf(listModule.size()));

            buttonEventConfi[0].setLayoutX(10);
            buttonEventConfi[0].getStyleClass().add("button-initializa");

            buttonEventAdd[0] = new Button();
            buttonEventAdd[0].setId("buttonAdd" + 0);
            buttonEventAdd[0].setLayoutX(410);
            buttonEventAdd[0].getStylesheets().add(getClass().getResource("/onpe/com/pe/styles/Style.css").toExternalForm());
            buttonEventAdd[0].getStyleClass().add("button-guardar");

            buttonEventDelete[0] = new Button();
            buttonEventDelete[0].setId("buttonAdd" + 0);
            buttonEventDelete[0].setLayoutX(370);
            buttonEventDelete[0].getStylesheets().add(getClass().getResource("/onpe/com/pe/styles/Style.css").toExternalForm());
            buttonEventDelete[0].getStyleClass().add("button-eliminar");

            buttonEventEdit[0] = new Button();
            buttonEventEdit[0].setId("buttonAdd" + 0);
            buttonEventEdit[0].setLayoutX(450);
            buttonEventEdit[0].setDisable(true);
            buttonEventEdit[0].getStylesheets().add(getClass().getResource("/onpe/com/pe/styles/Style.css").toExternalForm());
            buttonEventEdit[0].getStyleClass().add("button-editar");

            AnchorPane conteninerTextFieldAndButton = new AnchorPane();
            conteninerTextFieldAndButton.getChildren().addAll(buttonEventConfi[0], buttonEventDelete[0], buttonEventEdit[0], buttonEventAdd[0]);

            containerSettingModule.getChildren().addAll(conteninerTextFieldAndButton);
            containerSettingModule.setMargin(conteninerTextFieldAndButton, new Insets(10, 0, 0, 0));

            TextField textField = buttonEventConfi[0];
            Button btDelete = buttonEventDelete[0];
            Button btEdit = buttonEventEdit[0];
            Button btAdds = buttonEventAdd[0];

            buttonEventAdd[0].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (textField.getText().equals("") || textField.getText().isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("VALIDAR MODULO");
                        alert.setHeaderText("No Existe un nombre para el modulo");
                        alert.setContentText("Por favor, verifique que ha escrito un nombre al modulo");
                        alert.showAndWait();
                    } else {
                        btAdds.setDisable(true);
                        textField.setDisable(true);
                        btEdit.setDisable(false);
                        if (verificarButton()) {
                            btnGuardar.setDisable(false);
                        }

                        listModule.forEach(action -> {
                            if (action.getCodemodule().equals(textField.getId())) {
                                action.setNameModule(textField.getText());
                            }
                        });
                    }

                }
            });

            buttonEventEdit[0].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    btAdds.setDisable(false);
                    textField.setDisable(false);
                    btnGuardar.setDisable(true);
                }
            });

            buttonEventDelete[0].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    conteninerTextFieldAndButton.getChildren().removeAll(textField, btDelete, btEdit, btAdds);
                    containerSettingModule.getChildren().remove(conteninerTextFieldAndButton);
                    listModule.removeIf(action -> action.getCodemodule().equals(textField.getId()));
                    if (verificarButton()) {
                        btnGuardar.setDisable(false);
                    }
                }
            });

            buttonEventDelete[0].setVisible(false);
//                        buttonEventAdd[i].setDisable(true);
            buttonEventEdit[0].setDisable(true);

            listModule.add(module);
            list.add(1);
        } else {
            Dialog<String> dialog = new Dialog<>();
            dialog.initStyle(StageStyle.UNDECORATED);
            dialog.getDialogPane().setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THIN)));
            VBox vbox = new VBox();
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing(20);
            for (int buttonModal = 0; buttonModal <= listTypeModule.length - 1; buttonModal++) {
                Button button = new Button(listTypeModule[buttonModal]);
                button.setOnAction(e -> {
                    tipoModule = button.getText();
                    for (int i = 0; i < buttonEventConfi.length; i++) {
                        module.setCodemodule(String.valueOf(listModule.size()));
                        module.setTypeModule(tipoModule);
                        module.setOrdenCreation(String.valueOf(listModule.size()));
//                    buttonEventConfi[i] = new TextField(String.valueOf(list.size()));
                        buttonEventConfi[i] = new TextField(tipoModule);
                        buttonEventConfi[i].setId(String.valueOf(listModule.size()));

                        buttonEventConfi[i].setLayoutX(10);
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
                        buttonEventEdit[i].setDisable(true);
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
                                if (textField.getText().equals("") || textField.getText().isEmpty()) {
                                    Alert alert = new Alert(Alert.AlertType.WARNING);
                                    alert.setTitle("VALIDAR MODULO");
                                    alert.setHeaderText("No Existe un nombre para el modulo");
                                    alert.setContentText("Por favor, verifique que ha escrito un nombre al modulo");
                                    alert.showAndWait();
                                } else {
                                    btAdds.setDisable(true);
                                    textField.setDisable(true);
                                    btEdit.setDisable(false);
                                    if (verificarButton()) {
                                        btnGuardar.setDisable(false);
                                    }

                                    listModule.forEach(action -> {
                                        if (action.getCodemodule().equals(textField.getId())) {
                                            action.setNameModule(textField.getText());
                                        }
                                    });
                                }

                            }
                        });

                        buttonEventEdit[i].setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                btAdds.setDisable(false);
                                textField.setDisable(false);
                                btnGuardar.setDisable(true);
                            }
                        });

                        buttonEventDelete[i].setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                conteninerTextFieldAndButton.getChildren().removeAll(textField, btDelete, btEdit, btAdds);
                                containerSettingModule.getChildren().remove(conteninerTextFieldAndButton);
                                listModule.removeIf(action -> action.getCodemodule().equals(textField.getId()));
                                if (verificarButton()) {
                                    btnGuardar.setDisable(false);
                                }
                            }
                        });

                    }
                    listModule.add(module);
                    list.add(1);
                    dialog.close();

                });
                vbox.getChildren().add(button);
            }

            dialog.getDialogPane().setContent(vbox);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
            dialog.initOwner(containerSettingModule.getScene().getWindow());
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.show();
        }

    }

    @FXML
    private void createNewSetting(ActionEvent event) throws IOException {
        btnAddSeccion.setDisable(false);
        listModule = new ArrayList<>();
        list = new ArrayList<>();
        listCount = new ArrayList<>();
        containerSettingModule.getChildren().clear();
        titleDocumentSetting.clear();
        titleDocumentSetting.setDisable(false);

    }

    @FXML
    private void actionRegresar(ActionEvent event) throws IOException {
        App.setRoot(null, "inicioMenu");
    }

    @FXML
    private void actionGuardar(ActionEvent event) throws Exception {

        if (titleDocumentSetting.getText().equals("") || titleDocumentSetting.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("VALIDAR REGISTRO");
            alert.setHeaderText("No Existe un Titulo");
            alert.setContentText("Por favor, verifique que ha escrito un titulo para configuracion");
            alert.showAndWait();
        } else {
            if (businessService.findSettingForNameEleccion(titleDocumentSetting.getText()).isEmpty()) {
                setting.setId_setting(UUID.randomUUID().toString());
                setting.setName(titleDocumentSetting.getText());
                setting.setStatusSetting("0");
                Gson gson = new Gson();
                setting.setSetting(gson.toJson(listModule));
                businessService.saveSetting(setting);
                App.setRoot(null, "configurationDoc");
            } else {
                Gson gson = new Gson();
                businessService.updateSetting(titleDocumentSetting.getText(), gson.toJson(listModule));
            }
            btnGuardar.setDisable(true);
        }
    }

    private void actionContinuar() throws IOException {

        App.setRoot(null, "configuraSecciones");
    }

    public Boolean verificarButton() {
        Boolean uu = null;
        for (Node node : containerSettingModule.getChildren()) {
            AnchorPane ap = (AnchorPane) node;
            for (Node button : ap.getChildren()) {
                if (button instanceof TextField) {
                    if (button.isDisabled()) {
                        uu = Boolean.TRUE;
                    } else {
                        return Boolean.FALSE;
                    }
                }
            }
        }
        return uu;
    }
}
