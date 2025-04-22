package co.edu.uniquindio.poo.billeteravirtual.app;

import co.edu.uniquindio.poo.billeteravirtual.model.Administrador;
import co.edu.uniquindio.poo.billeteravirtual.model.BaseDatos;
import co.edu.uniquindio.poo.billeteravirtual.model.Perfil;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Datos de prueba
        BaseDatos baseDatos = BaseDatos.getInstancia();
        ArrayList<Perfil> perfiles = new ArrayList<>();
        Usuario usuario = new Usuario("123","123","123","123","123","123");
        Administrador administrador = new Administrador("000","000","000","000","000","000");
        perfiles.add(usuario);
        perfiles.add(administrador);
        baseDatos.setPerfiles(perfiles);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/IniciarSesionView.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setTitle("Billetera Virtual");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}