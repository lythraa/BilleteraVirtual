module co.edu.uniquindio.poo.billeteravirtual {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.poo.billeteravirtual to javafx.fxml;
    exports co.edu.uniquindio.poo.billeteravirtual.app;
    opens co.edu.uniquindio.poo.billeteravirtual.app to javafx.fxml;
    exports co.edu.uniquindio.poo.billeteravirtual.controllers;
    opens co.edu.uniquindio.poo.billeteravirtual.controllers to javafx.fxml;
    exports co.edu.uniquindio.poo.billeteravirtual.controller;
    opens co.edu.uniquindio.poo.billeteravirtual.controller to javafx.fxml;
}