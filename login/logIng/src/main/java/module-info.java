module com.mycompany.loging {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;
    requires java.base;
    
    opens com.mycompany.loging to javafx.fxml;
    exports com.mycompany.loging;
    
    //expone endpoint de controller primary
    exports com.mycompany.loging.endpoint.dashboard;
    opens com.mycompany.loging.endpoint.dashboard to javafx.fxml;
    //expone endpoint de controller secondary
    exports com.mycompany.loging.endpoint.login;
    opens com.mycompany.loging.endpoint.login to javafx.fxml;
    
}
