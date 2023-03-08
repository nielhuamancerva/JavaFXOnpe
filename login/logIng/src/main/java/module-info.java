module com.mycompany.loging {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;
    requires gson;
    requires java.base;
    requires opencv;
    requires com.google.zxing;
    requires com.google.zxing.javase;
    requires imgscalr.lib;
    requires tess4j;
    requires jai.imageio.core;
    requires com.rabbitmq.client;
    requires java.sql;

    
    opens com.mycompany.loging to javafx.fxml;
    exports com.mycompany.loging;
    exports com.mycompany.loging.score.model;
    //expone endpoint de controller primary
    exports com.mycompany.loging.endpoint.dashboard;
    opens com.mycompany.loging.endpoint.dashboard to javafx.fxml;
    //expone endpoint de controller secondary
    exports com.mycompany.loging.endpoint.login;
    opens com.mycompany.loging.endpoint.login to javafx.fxml;
    exports com.mycompany.loging.score.util;
    
}
