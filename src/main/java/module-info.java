module co.edu.uniquindio.poo.billeteravirtual {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.poo.billeteravirtual to javafx.fxml;
    exports co.edu.uniquindio.poo.billeteravirtual;
}