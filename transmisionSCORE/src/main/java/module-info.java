module onpe.com.pe.transmisionscore {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens onpe.com.pe.transmisionscore to javafx.fxml;
    exports onpe.com.pe.transmisionscore;
}
