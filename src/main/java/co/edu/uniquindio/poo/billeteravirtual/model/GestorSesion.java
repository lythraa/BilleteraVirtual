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
     * Método para obtener el usuario actualmente en sesión
     * @return
     */
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    /**
     * Método que permite iniciar sesión.
     * Al recibir el usuario que ingresó a la aplicación, se
     * asigna como usuario actual de la aplicación.
     *
     * NOTA (18/04): La aplicación debe manejar el inicio de sesión
     * mediante usuario y contraseña, aún no tengo claro si se modificará
     * desde aquí, por ahora, esta clase recibe el objeto Usuario
     *
     * @param usuario
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

}
