package co.edu.uniquindio.poo.billeteravirtual.model.observer;

import java.util.ArrayList;
import java.util.List;

// GestorNotificaciones.java
import java.util.*;

public class GestorNotificaciones {
    private static GestorNotificaciones instancia;
    private Map<String, List<UsuarioObserver>> suscriptoresPorEvento = new HashMap<>();

    private GestorNotificaciones() {}

    public static GestorNotificaciones getInstancia() {
        if (instancia == null) {
            instancia = new GestorNotificaciones();
        }
        return instancia;
    }

    public void registrarUsuario(String evento, UsuarioObserver usuario) {
        suscriptoresPorEvento
                .computeIfAbsent(evento, k -> new ArrayList<>())
                .add(usuario);
    }

    public void eliminarUsuario(String evento, UsuarioObserver usuario) {
        List<UsuarioObserver> lista = suscriptoresPorEvento.get(evento);
        if (lista != null) lista.remove(usuario);
    }

    public void notificar(String evento, Notificacion notificacion) {
        List<UsuarioObserver> lista = suscriptoresPorEvento.get(evento);
        if (lista != null) {
            for (UsuarioObserver usuario : lista) {
                usuario.recibirNotificacion(notificacion);
            }
        }
    }

    public Set<String> obtenerEventos() {
        return suscriptoresPorEvento.keySet();
    }
}

