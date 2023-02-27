module com.mycompany.loging {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.async.client;
    opens com.mycompany.loging to javafx.fxml;
    exports com.mycompany.loging;
}
