module com.mycompany.loging {
    requires javafx.controls;
    requires javafx.fxml;
    requires mongodb.driver.async;
    opens com.mycompany.loging to javafx.fxml;
    exports com.mycompany.loging;

}
