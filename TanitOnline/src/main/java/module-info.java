module ProjetPidev {
    exports test;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.fxml;
    opens controllers to javafx.fxml;
    opens models to javafx.base;
    requires org.controlsfx.controls;
    requires javafx.controls;
    requires mail;


}