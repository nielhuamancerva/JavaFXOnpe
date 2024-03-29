module onpe.com.pe.gestorconfiguracionactas {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.base;
    requires javafx.base;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;
    requires com.google.gson;
    requires java.logging;
    requires jai.imageio.core;
    requires java.desktop;
    requires tess4j;
    
    opens onpe.com.pe.gestorconfiguracionactas to javafx.fxml;
    exports onpe.com.pe.gestorconfiguracionactas;
    exports onpe.com.pe.gestorconfiguracionactas.core.model;
    opens onpe.com.pe.gestorconfiguracionactas.core.model;
    opens onpe.com.pe.gestorconfiguracionactas.endpoint to javafx.fxml;

}
