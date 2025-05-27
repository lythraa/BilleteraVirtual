package co.edu.uniquindio.poo.billeteravirtual.model;
import java.time.LocalDateTime;

/**
 * La clase Notificacion representa un mensaje o evento asociado a una acción o actividad
 * específica dentro del sistema. Incluye información detallada sobre el evento,
 * el administrador que lo generó, un mensaje descriptivo, y un registro de la fecha y hora.
 */
public class Notificacion {
    private final String adminNombre;
    private final String mensaje;
    private final LocalDateTime fecha;

    /**
     * Constructor de la clase Notificacion. Inicializa una instancia de la notificación
     * con los detalles del evento, el nombre del administrador que lo generó,
     * un mensaje descriptivo y registra la fecha y hora actuales.
     *
     * @param evento      Nombre o descripción corta del evento asociado a la notificación.
     * @param adminNombre Nombre del administrador que generó la notificación.
     * @param mensaje     Descripción detallada del evento o mensaje de la notificación.
     */
    public Notificacion(String evento, String adminNombre, String mensaje) {
        this.adminNombre = adminNombre;
        this.mensaje = mensaje;
        this.fecha = LocalDateTime.now();  // fecha actual
    }

    public String getAdminNombre() {
        return adminNombre;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
}
