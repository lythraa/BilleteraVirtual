package co.edu.uniquindio.poo.billeteravirtual.app;

import co.edu.uniquindio.poo.billeteravirtual.model.*;
import co.edu.uniquindio.poo.billeteravirtual.model.CuentaBancaria;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/IniciarSesionView.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setTitle("Billetera Virtual");
        primaryStage.setScene(scene);
        primaryStage.show();

        //=====================OBJETOS DE PRUEBA========================//

        //FACHADA
        SistemaBilleteraFacade fachada = SistemaBilleteraFacade.getInstancia();

        //ADMINS
        Administrador admin1 = new Administrador("111", "111","111","111","111","111");
        Administrador admin2 = new Administrador("333", "333","Eduardo","eduardo@admin.com","12345678","Armenia");
        Administrador admin3 = new Administrador("444", "444","Juliana","juliana@admin.com","34567890","Pereira");

        //USUARIOS
        Usuario user1 = new Usuario("222", "222","222","222","222","222");
        Usuario user2 = new Usuario("555", "555","Andres","andres@email.com","87654321","Bogota");
        Usuario user3 = new Usuario("666", "666","Tatiana","tatiana@email.com","09876543","Cartagena");

        //CUENTAS
        CuentaBancaria cuenta1 = new CuentaBancaria("123", "Bancolombia", TipoCuenta.AHORRO);
        CuentaBancaria cuenta2 = new CuentaBancaria("456", "Davivienda", TipoCuenta.AHORRO);
        CuentaBancaria cuenta3 = new CuentaBancaria("789", "Colpatria", TipoCuenta.AHORRO);

        cuenta1.agregarSaldo(6000000);
        cuenta2.agregarSaldo(7000000);
        cuenta3.agregarSaldo(8000000);

        user1.agregarCuenta(cuenta1);
        user2.agregarCuenta(cuenta2);
        user3.agregarCuenta(cuenta3);

        //CATEGORIAS
        user1.obtenerOCrearCategoria("VIAJE", 1000000);
        user1.obtenerOCrearCategoria("COMIDA", 900000);
        user2.obtenerOCrearCategoria("VIAJE", 1000000);
        user2.obtenerOCrearCategoria("COMIDA", 900000);
        user3.obtenerOCrearCategoria("VIAJE", 1000000);
        user3.obtenerOCrearCategoria("ESTUDIOS", 5000000);

        //MOVIMIENTOS
        fachada.realizarRetiro(user1, cuenta1, 30000, user1.buscarCategoria("VIAJE"), "");
        fachada.realizarRetiro(user1, cuenta1, 20000, user1.buscarCategoria("VIAJE"), "");
        fachada.realizarRetiro(user1, cuenta1, 10000, user1.buscarCategoria("COMIDA"), "");
        fachada.realizarRetiro(user2, cuenta2, 30000, user2.buscarCategoria("VIAJE"), "");
        fachada.realizarRetiro(user2, cuenta2, 20000, user2.buscarCategoria("COMIDA"), "");
        fachada.realizarRetiro(user3, cuenta3, 100000, user3.buscarCategoria("ESTUDIOS"), "");

        //AGREGAR A LA FACHADA
        fachada.getGestorAdministradores().agregar(admin1);
        fachada.getGestorAdministradores().agregar(admin2);
        fachada.getGestorAdministradores().agregar(admin3);

        fachada.getGestorUsuarios().agregar(user1);
        fachada.getGestorUsuarios().agregar(user2);
        fachada.getGestorUsuarios().agregar(user3);


    }

    public static void main(String[] args) {
        launch(args);
    }
}