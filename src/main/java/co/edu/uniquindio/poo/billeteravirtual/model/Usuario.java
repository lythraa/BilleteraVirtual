package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Perfil {

    private double saldo;

    public Usuario(int id, String nombre, String correo, String telefono, String direccion, double saldo){
        super(id, nombre, correo, telefono, direccion);
        this.saldo = saldo;
    }

}
