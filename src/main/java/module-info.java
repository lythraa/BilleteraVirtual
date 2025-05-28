module co.edu.uniquindio.poo.billeteravirtual {

    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;

    // Nuevos módulos requeridos
    requires org.apache.poi.ooxml;
    requires org.apache.poi.poi;
    requires org.apache.pdfbox;
    requires java.desktop;

    opens co.edu.uniquindio.poo.billeteravirtual.model to javafx.fxml;
    opens co.edu.uniquindio.poo.billeteravirtual.app to javafx.fxml;
    opens co.edu.uniquindio.poo.billeteravirtual.controller to javafx.fxml;
    opens views to javafx.fxml;
    opens co.edu.uniquindio.poo.billeteravirtual.model.builder to javafx.fxml;
    opens co.edu.uniquindio.poo.billeteravirtual.model.proxy to javafx.fxml;
    opens co.edu.uniquindio.poo.billeteravirtual.model.gestores to javafx.fxml;
    opens co.edu.uniquindio.poo.billeteravirtual.model.exception to javafx.fxml;

    exports co.edu.uniquindio.poo.billeteravirtual.app;
    exports co.edu.uniquindio.poo.billeteravirtual.controller;

    /*        OLD POM
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires org.apache.poi.ooxml;
    requires org.apache.pdfbox;


    opens co.edu.uniquindio.poo.billeteravirtual.model to javafx.fxml;
    opens co.edu.uniquindio.poo.billeteravirtual.app to javafx.fxml;
    opens co.edu.uniquindio.poo.billeteravirtual.controller to javafx.fxml;
    opens views to javafx.fxml;

    exports co.edu.uniquindio.poo.billeteravirtual.app;

    exports co.edu.uniquindio.poo.billeteravirtual.controller;
    opens co.edu.uniquindio.poo.billeteravirtual.model.builder to javafx.fxml;
    opens co.edu.uniquindio.poo.billeteravirtual.model.proxy to javafx.fxml;
    opens co.edu.uniquindio.poo.billeteravirtual.model.gestores to javafx.fxml;
    opens co.edu.uniquindio.poo.billeteravirtual.model.exception to javafx.fxml;
    */
}