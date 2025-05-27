package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.*;

public class GestorNotificaciones {
    private static GestorNotificaciones instancia;
    private final Map<String, List<Observer>> suscriptoresPorEvento = new HashMap<>();
    private final List<Notificacion> notificacionesSoporte = new ArrayList<>();

    /**
     * Constructor sin argumentos de la clase
     */
    private GestorNotificaciones() {}

    /**
     * Método para obtener la instancia única del gestor
     * @return instancias del gestor
     */
    public static synchronized GestorNotificaciones getInstancia() {
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
                .computeIfAbsent(evento, k -> new ArrayList<>());

        List<Observer> lista = suscriptoresPorEvento.get(evento);

        if (!lista.contains(obs)) {
            lista.add(obs);
        }
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
        if (evento.equalsIgnoreCase("soporte")) {
            notificacionesSoporte.add(notificacion);
        }

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

    /**
     * Obtiene la lista de observadores registrados para un evento específico.
     *
     * @param evento El evento cuyo listado de observadores se desea obtener.
     * @return Una lista de observadores asociados al evento. Si no existen observadores
     *         para el evento, se retorna una lista vacía.
     */
    public List<Observer> obtenerObservadoresPorEvento(String evento) {
        return suscriptoresPorEvento.getOrDefault(evento, Collections.emptyList());
    }

    public List<Notificacion> getNotificacionesSoporte() {
        return notificacionesSoporte;
    }
}
