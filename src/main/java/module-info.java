module co.edu.uniquindio.poo.billeteravirtual {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.poo.billeteravirtual.model to javafx.fxml;
    opens co.edu.uniquindio.poo.billeteravirtual.app to javafx.fxml;
    opens co.edu.uniquindio.poo.billeteravirtual.controller to javafx.fxml;
    opens views to javafx.fxml;

    exports co.edu.uniquindio.poo.billeteravirtual.app;

    exports co.edu.uniquindio.poo.billeteravirtual.controller;
    opens co.edu.uniquindio.poo.billeteravirtual.model.transaccion to javafx.fxml;
    opens co.edu.uniquindio.poo.billeteravirtual.model.builder to javafx.fxml;

}