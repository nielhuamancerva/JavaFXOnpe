module onpe.com.pe.transmisionscore {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.base;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires com.rabbitmq.client;
    requires com.google.gson;
    requires java.desktop;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;

    opens onpe.com.pe.transmisionscore to javafx.fxml;
    exports onpe.com.pe.transmisionscore;
    
    opens onpe.com.pe.transmisionscore.dashboard to javafx.fxml;
    exports onpe.com.pe.transmisionscore.dashboard;
    
 
    opens onpe.com.pe.transmisionscore.core.model;
}
