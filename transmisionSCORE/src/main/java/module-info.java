module onpe.com.pe.transmisionscore {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.base;
    requires java.sql;
    requires org.postgresql.jdbc;


    opens onpe.com.pe.transmisionscore to javafx.fxml;
    exports onpe.com.pe.transmisionscore;
    
    opens onpe.com.pe.transmisionscore.dashboard to javafx.fxml;
    exports onpe.com.pe.transmisionscore.dashboard;
}
