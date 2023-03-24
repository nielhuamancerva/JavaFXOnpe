module onpe.com.pe.gestorconfiguracionactas {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.base;
    requires javafx.base;
    
    opens onpe.com.pe.gestorconfiguracionactas to javafx.fxml;
    exports onpe.com.pe.gestorconfiguracionactas;
}
