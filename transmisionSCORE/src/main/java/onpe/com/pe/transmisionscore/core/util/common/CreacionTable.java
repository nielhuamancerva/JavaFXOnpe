package onpe.com.pe.transmisionscore.core.util.common;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import onpe.com.pe.transmisionscore.App;
import onpe.com.pe.transmisionscore.core.model.Actas;
import onpe.com.pe.transmisionscore.core.model.TransmisionRecibidas;
import onpe.com.pe.transmisionscore.core.util.constanst.VariableGlobales;

/**
 *
 * @author NHuaman
 */
public class CreacionTable {

    public TableView<TransmisionRecibidas> viewActas(TableView<TransmisionRecibidas> Tactas) {
        TableColumn<TransmisionRecibidas, Integer> columnaActa = new TableColumn<>("codigo");
        columnaActa.setCellValueFactory(new PropertyValueFactory<>("strama"));

        TableColumn<TransmisionRecibidas, String> columnaDepartamento = new TableColumn<>("Centro de Computo");
        columnaDepartamento.setCellValueFactory(new PropertyValueFactory<>("ncodtrama"));

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
        TableColumn<TransmisionRecibidas, String> columnaFechaRegistro = new TableColumn<>("Fecha de Registro");
        columnaFechaRegistro.setCellValueFactory(new PropertyValueFactory<>("dfechahora"));

        TableColumn<TransmisionRecibidas, Integer> columnaEstado = new TableColumn<>("Estado");
        columnaEstado.setCellValueFactory(new PropertyValueFactory<>("nestado"));

        columnaEstado.setCellFactory(column -> {
            return new TableCell<TransmisionRecibidas, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                        setStyle("");
                    } else {
//                        setText(item);

                        switch (item) {
                            case 1:
                                setStyle("-fx-text-fill: transparent; -fx-background-color:green; -fx-border-width:0.5px; -fx-border-color: white;");
                                break;
                            case 0:
                                setStyle("-fx-text-fill: transparent; -fx-background-color:red; -fx-border-width:0.5px; -fx-border-color: white;");
                                break;
                            default:
                                setStyle("-fx-background-color: #E0E0E0; -fx-border-width:0.5px; -fx-border-color: white;");
                                break;
                        }
                    }
                }
            };
        });

        TableColumn<TransmisionRecibidas, Void> columnaAccion = new TableColumn<>("Acción");

        Callback<TableColumn<TransmisionRecibidas, Void>, TableCell<TransmisionRecibidas, Void>> cellFactory = new Callback<TableColumn<TransmisionRecibidas, Void>, TableCell<TransmisionRecibidas, Void>>() {
            @Override
            public TableCell<TransmisionRecibidas, Void> call(final TableColumn<TransmisionRecibidas, Void> param) {
                final TableCell<TransmisionRecibidas, Void> cell = new TableCell<TransmisionRecibidas, Void>() {

                    private final Button btn = new Button("Ver");

                    {
                        btn.setOnAction((ActionEvent event) -> {
//                            VariableGlobales.viewImage = getTableView().getItems().get(getIndex()).getStrama();

                            if (getTableView().getItems().get(getIndex()).getNestado() == 1) {
                                VariableGlobales.viewImage = "file:D:\\carpetaValido\\" + getTableView().getItems().get(getIndex()).getStrama() + ".png";
                            } else {
                                VariableGlobales.viewImage = "file:D:\\carpetaInvalido\\" + getTableView().getItems().get(getIndex()).getStrama() + ".png";
                            }

                            System.out.println("imprimir: " + VariableGlobales.viewImage);
                            try {

                                App.setRoot(null, "viewImagen");
                            } catch (IOException ex) {
                                System.out.println("error:" + ex);
                            }
                            System.out.println("Editar acta " + getTableView().getItems().get(getIndex()).getNestado());

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

//                            if (getTableView().getItems().get(getIndex()).getNestado().equals(1)) {
//                                setGraphic(btn);
//                            } else {
//                                setGraphic(null);
//                            }
//                        
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
