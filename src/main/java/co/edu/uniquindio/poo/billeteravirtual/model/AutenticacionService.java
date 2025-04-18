package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.HashMap;
import java.util.Map;

public class AutenticacionService {
    private static AutenticacionService instancia;
    private Map<String, Usuario> usuariosRegistrados = new HashMap<>();

    private AutenticacionService() {
    }

    public static AutenticacionService getInstancia() {
        if (instancia == null) {
            instancia = new AutenticacionService();
        }
        return instancia;
    }

    public boolean registrarUsuario(Usuario usuario) {
        if (!usuariosRegistrados.containsKey(usuario.getCorreo())) {
            usuariosRegistrados.put(usuario.getCorreo(), usuario);
            return true;
        }
        return false;
    }

    public Usuario login(String correo) {
        return usuariosRegistrados.get(correo);
    }
}
