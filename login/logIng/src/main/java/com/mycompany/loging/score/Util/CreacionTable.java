package com.mycompany.loging.score.util;

import com.mycompany.loging.score.model.Actas;
import com.mycompany.loging.score.util.constanst.VariableGlobales;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 *
 * @author NHuaman
 */
public class CreacionTable {

    public TableView<Actas> viewActas(TableView<Actas> Tactas) {
        TableColumn<Actas, Integer> columnaActa = new TableColumn<>("acta");
        columnaActa.setCellValueFactory(new PropertyValueFactory<>("acta"));

        TableColumn<Actas, String> columnaDepartamento = new TableColumn<>("departamento");
        columnaDepartamento.setCellValueFactory(new PropertyValueFactory<>("departamento"));

//        TableColumn<Actas, String> columnaProvincia = new TableColumn<>("provincia");
//        columnaProvincia.setCellValueFactory(new PropertyValueFactory<>("provincia"));
//
//        TableColumn<Actas, String> columnaDistrito = new TableColumn<>("distrito");
//        columnaDistrito.setCellValueFactory(new PropertyValueFactory<>("distrito"));
//
//        TableColumn<Actas, String> columnaHora = new TableColumn<>("hora");
//        columnaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
//
//        TableColumn<Actas, String> columnaLista = new TableColumn<>("lista1");
//        columnaLista.setCellValueFactory(new PropertyValueFactory<>("lista1"));
//
//        TableColumn<Actas, String> columnaVotosblancos = new TableColumn<>("votos blancos");
//        columnaVotosblancos.setCellValueFactory(new PropertyValueFactory<>("votosBlanco"));
//
//        TableColumn<Actas, String> columnaVotosNulos = new TableColumn<>("votos nulos");
//        columnaVotosNulos.setCellValueFactory(new PropertyValueFactory<>("votosNulos"));
//
//        TableColumn<Actas, String> columnaVotosInpugnados = new TableColumn<>("votos inpugnados");
//        columnaVotosInpugnados.setCellValueFactory(new PropertyValueFactory<>("votosInpugnados"));
//
//        TableColumn<Actas, String> columnaVotosEmitidos = new TableColumn<>("votos emitidos");
//        columnaVotosEmitidos.setCellValueFactory(new PropertyValueFactory<>("votosEmitidos"));
//
//        TableColumn<Actas, String> columnaObservaciones = new TableColumn<>("observaciones");
//        columnaObservaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
//
//        TableColumn<Actas, String> columnaFirma1 = new TableColumn<>("firma1");
//        columnaFirma1.setCellValueFactory(new PropertyValueFactory<>("firma1"));
//
//        TableColumn<Actas, String> columnaFirma2 = new TableColumn<>("firma2");
//        columnaFirma2.setCellValueFactory(new PropertyValueFactory<>("firma2"));
//
//        TableColumn<Actas, String> columnaFirma3 = new TableColumn<>("firma3");
//        columnaFirma3.setCellValueFactory(new PropertyValueFactory<>("firma3"));
        TableColumn<Actas, String> columnaFechaRegistro = new TableColumn<>("fecha de Registro");
        columnaFechaRegistro.setCellValueFactory(new PropertyValueFactory<>("fecha_registro"));

        TableColumn<Actas, String> columnaEstado = new TableColumn<>("estado");
        columnaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
//        
        columnaEstado.setCellFactory(column -> {
            return new TableCell<Actas, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(item);
                        switch (item) {
                            case "Valido":
                                setStyle("-fx-text-fill: transparent; -fx-background-color:green; -fx-border-width:0.5px; -fx-border-color: white;");
                                break;
                            case "No Valido":
                                setStyle("-fx-text-fill: transparent; -fx-background-color:red; -fx-border-width:0.5px; -fx-border-color: white;");
                                break;
                            case "":
                                setStyle("-fx-background-color: #E0E0E0; -fx-border-width:0.5px; -fx-border-color: white;");
                                break;
                            default:
                                break;
                        }
                    }
                }
            };
        });

        TableColumn<Actas, Void> columnaAccion = new TableColumn<>("Acción");

        Callback<TableColumn<Actas, Void>, TableCell<Actas, Void>> cellFactory = new Callback<TableColumn<Actas, Void>, TableCell<Actas, Void>>() {
            @Override
            public TableCell<Actas, Void> call(final TableColumn<Actas, Void> param) {
                final TableCell<Actas, Void> cell = new TableCell<Actas, Void>() {
                    private final Button btn = new Button("Ver");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            VariableGlobales.viewImage=getTableView().getItems().get(getIndex()).getActa();
                            System.out.println("Editar acta " +getTableView().getItems().get(getIndex()).getActa() );
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        columnaAccion.setCellFactory(cellFactory);
        Tactas.getColumns().addAll(columnaActa, columnaDepartamento, columnaFechaRegistro, columnaEstado, columnaAccion);
        return Tactas;
    }
}
