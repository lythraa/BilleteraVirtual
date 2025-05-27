package co.edu.uniquindio.poo.billeteravirtual.app;

import co.edu.uniquindio.poo.billeteravirtual.model.Administrador;
import co.edu.uniquindio.poo.billeteravirtual.model.SistemaBilleteraFacade;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import co.edu.uniquindio.poo.billeteravirtual.model.gestores.GestorAdministradores;
import co.edu.uniquindio.poo.billeteravirtual.model.gestores.GestorUsuarios;
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

        SistemaBilleteraFacade fachada = SistemaBilleteraFacade.getInstancia();

        Administrador admin1 = new Administrador("111", "111","111","111","111","111");
        Usuario user1 = new Usuario("222", "222","222","222","222","222");



        fachada.getGestorAdministradores().agregar(admin1);
        fachada.getGestorUsuarios().agregar(user1);

    }

    public static void main(String[] args) {
        launch(args);
    }
}