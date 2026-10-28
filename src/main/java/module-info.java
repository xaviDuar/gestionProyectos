module com.sigep {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.sigep to javafx.fxml;
    opens com.sigep.controller to javafx.fxml;
    opens com.sigep.model to javafx.base;

    exports com.sigep;
    exports com.sigep.controller;
    exports com.sigep.model;
}
