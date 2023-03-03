package com.mycompany.loging.score.util;

import com.mycompany.loging.score.model.Actas;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

        TableColumn<Actas, String> columnaProvincia = new TableColumn<>("provincia");
        columnaProvincia.setCellValueFactory(new PropertyValueFactory<>("provincia"));

        TableColumn<Actas, String> columnaDistrito = new TableColumn<>("distrito");
        columnaDistrito.setCellValueFactory(new PropertyValueFactory<>("distrito"));

        TableColumn<Actas, String> columnaHora = new TableColumn<>("hora");
        columnaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));

        TableColumn<Actas, String> columnaLista = new TableColumn<>("lista1");
        columnaLista.setCellValueFactory(new PropertyValueFactory<>("lista1"));

        TableColumn<Actas, String> columnaVotosblancos = new TableColumn<>("votos blancos");
        columnaVotosblancos.setCellValueFactory(new PropertyValueFactory<>("votosBlanco"));

        TableColumn<Actas, String> columnaVotosNulos = new TableColumn<>("votos nulos");
        columnaVotosNulos.setCellValueFactory(new PropertyValueFactory<>("votosNulos"));

        TableColumn<Actas, String> columnaVotosInpugnados = new TableColumn<>("votos inpugnados");
        columnaVotosInpugnados.setCellValueFactory(new PropertyValueFactory<>("votosInpugnados"));

        TableColumn<Actas, String> columnaVotosEmitidos = new TableColumn<>("votos emitidos");
        columnaVotosEmitidos.setCellValueFactory(new PropertyValueFactory<>("votosEmitidos"));

        TableColumn<Actas, String> columnaObservaciones = new TableColumn<>("observaciones");
        columnaObservaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));

        TableColumn<Actas, String> columnaFirma1 = new TableColumn<>("firma1");
        columnaFirma1.setCellValueFactory(new PropertyValueFactory<>("firma1"));

        TableColumn<Actas, String> columnaFirma2 = new TableColumn<>("firma2");
        columnaFirma2.setCellValueFactory(new PropertyValueFactory<>("firma2"));

        TableColumn<Actas, String> columnaFirma3 = new TableColumn<>("firma3");
        columnaFirma3.setCellValueFactory(new PropertyValueFactory<>("firma3"));

        TableColumn<Actas, String> columnaEstado = new TableColumn<>("estado");
        columnaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        Tactas.getColumns().addAll(columnaActa, columnaDepartamento, columnaProvincia, columnaDistrito,
                 columnaHora, columnaLista, columnaVotosblancos, columnaVotosNulos, columnaVotosInpugnados, columnaVotosEmitidos,
                 columnaObservaciones, columnaFirma1, columnaFirma2, columnaFirma3, columnaEstado);
        return Tactas;
    }
}
