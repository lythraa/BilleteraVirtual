module co.edu.uniquindio.poo.billeteravirtual {

    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;

    requires org.apache.poi.ooxml;
    requires org.apache.poi.poi;
    requires org.apache.pdfbox;
    requires java.desktop;

    opens co.edu.uniquindio.poo.billeteravirtual.model to javafx.fxml;
    opens co.edu.uniquindio.poo.billeteravirtual.app to javafx.fxml;
    opens co.edu.uniquindio.poo.billeteravirtual.controller to javafx.fxml;
    opens views to javafx.fxml;

    exports co.edu.uniquindio.poo.billeteravirtual.app;
    exports co.edu.uniquindio.poo.billeteravirtual.controller;
    exports co.edu.uniquindio.poo.billeteravirtual.model;
}
