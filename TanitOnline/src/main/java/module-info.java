module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;
    opens models to javafx.base;
    requires javafx.graphics;
    opens com.example.demo to javafx.fxml;
    exports com.example.demo;

}