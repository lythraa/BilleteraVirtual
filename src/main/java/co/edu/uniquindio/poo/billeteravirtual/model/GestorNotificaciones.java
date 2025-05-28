package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.ArrayList;
import java.util.List;

import java.util.*;

public class GestorNotificaciones {
    private static GestorNotificaciones instancia;
    private Map<String, List<Observer>> suscriptoresPorEvento = new HashMap<>();

    private GestorNotificaciones() {}

    public static GestorNotificaciones getInstancia() {
        if (instancia == null) {
            instancia = new GestorNotificaciones();
        }
        return instancia;
    }

    public void registrar(String evento, Observer obs) {
        suscriptoresPorEvento
                .computeIfAbsent(evento, k -> new ArrayList<>())
                .add(obs);
    }

    public void registrarEvento(String evento) {
        suscriptoresPorEvento.computeIfAbsent(evento, k -> new ArrayList<>());
    }

    public void eliminar(String evento, Observer obs) {
        List<Observer> lista = suscriptoresPorEvento.get(evento);
        if (lista != null) lista.remove(obs);
    }

    public void notificar(String evento, Notificacion notificacion) {
        List<Observer> lista = suscriptoresPorEvento.get(evento);
        if (lista != null) {
            for (Observer obs : lista) {
                obs.recibirNotificacion(notificacion);
            }
        }
    }

    public Set<String> obtenerEventos() {
        return suscriptoresPorEvento.keySet();
    }

    public Set<String> obtenerEventosPorUsuario(Observer obs) {
        Set<String> eventosSuscritos = new HashSet<>();
        for (Map.Entry<String, List<Observer>> entry : suscriptoresPorEvento.entrySet()) {
            if (entry.getValue().contains(obs)) {
                eventosSuscritos.add(entry.getKey());
            }
        }
        return eventosSuscritos;
    }

}
