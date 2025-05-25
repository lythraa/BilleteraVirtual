package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.ArrayList;
import java.util.List;

import java.util.*;

public class GestorNotificaciones {
    private static GestorNotificaciones instancia;
    private Map<String, List<Observer>> suscriptoresPorEvento = new HashMap<>();

    /**
     * Constructor sin argumentos de la clase
     */
    private GestorNotificaciones() {}

    /**
     * Método para obtener la instancia única del gestor
     * @return instancias del gestor
     */
    public static GestorNotificaciones getInstancia() {
        if (instancia == null) {
            instancia = new GestorNotificaciones();
        }
        return instancia;
    }

    /**
     * Método para registrar un observador a un evento
     * @param evento evento deseado
     * @param obs observador a registrar
     */
    public void registrar(String evento, Observer obs) {
        suscriptoresPorEvento
                .computeIfAbsent(evento, k -> new ArrayList<>())
                .add(obs);
    }

    /**
     * Método para registrar un nuevo evento
     * @param evento evento a crear
     */
    public void registrarEvento(String evento) {
        suscriptoresPorEvento.computeIfAbsent(evento, k -> new ArrayList<>());
    }

    /**
     * Método apra eliminar un observador de un evento
     * @param evento evento deseado
     * @param obs observador a desuscribir
     */
    public void eliminar(String evento, Observer obs) {
        List<Observer> lista = suscriptoresPorEvento.get(evento);
        if (lista != null) lista.remove(obs);
    }

    /**
     * Método para notificar a los usuarios suscritos a un evento
     * @param evento evento
     * @param notificacion notificación a enviar
     */
    public void notificar(String evento, Notificacion notificacion) {
        List<Observer> lista = suscriptoresPorEvento.get(evento);
        if (lista != null) {
            for (Observer obs : lista) {
                obs.recibirNotificacion(notificacion);
            }
        }
    }

    /**
     * Método para obtener todos los eventos creados
     * @return eventos
     */
    public Set<String> obtenerEventos() {
        return suscriptoresPorEvento.keySet();
    }

    /**
     * Método apra obrener eventos por usuario
     * @param obs observador deseado
     * @return eventos del observador
     */
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
