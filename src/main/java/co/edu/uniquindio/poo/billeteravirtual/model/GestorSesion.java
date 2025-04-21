package co.edu.uniquindio.poo.billeteravirtual.model;

public class GestorSesion {

    private static GestorSesion instance;
    private Usuario usuarioActual;

    /**
     * Constructor privado de la clase para evitar múltiples instancias de la misma
     */
    private GestorSesion() {}

    /**
     * Método para obtener la instancia única del gestor de sesión (Singleton)
     * @return instance Instancia del gestor
     */
    public static GestorSesion getInstance() {
        if (instance == null) {
            instance = new GestorSesion();
        }
        return instance;
    }

    /**
     * Método que permite iniciar sesión.
     * Al recibir el perfil que ingresó a la aplicación, se
     * asigna como perfil actual de la aplicación.
     * @param id id del perfil
     * @param contrasenia contrasenia del perfil
     * @return true en caso de iniciar sesion
     */
    public void iniciarSesion(Usuario usuario) {
        this.usuarioActual = usuario;
    }

    /**
     * Método que cierra la sesión de usuario.
     * Al ser llamado, el usuario actual regresará a ser nulo
     * hasta que se inicie una nueva sesión.
     */
    public void cerrarSesion() {
        this.usuarioActual = null;
    }

    //=========================GETTERS============================//

    /**
     * Método para obtener el perfil actualmente en sesión
     *
     * @return
     */
    public Perfil getPerfilActual() {
        return perfilActual;
    }

}
