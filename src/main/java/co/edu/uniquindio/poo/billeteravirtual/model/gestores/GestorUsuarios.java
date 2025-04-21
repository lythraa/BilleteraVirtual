package co.edu.uniquindio.poo.billeteravirtual.model.gestores;
import co.edu.uniquindio.poo.billeteravirtual.model.*;

public class GestorUsuarios {

    //Acceso a la base de datos del programa
    private BaseDatos baseDatos = BaseDatos.getInstancia();

    //=========================CRUD USUARIO============================//

    /**
     * Método para agregar un nuevo usuario
     * @param usuario
     */
    public void agregarUsuario(Usuario usuario) {
        baseDatos.getPerfiles().add(usuario);
    }

    /**
     * Método para buscar un usuario mediante su número de identificación único
     * @param id Número de identificación del usuario
     * @return
     */
    public Usuario buscarUsuario(String id) {
        for (Perfil perfil : baseDatos.getPerfiles()) {
            if (perfil instanceof Usuario usuario && usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Método para eliminar un usuario mediante el id
     * @param usuario usuario a eliminar
     */
    public void eliminarUsuario(Usuario usuario) {
        baseDatos.getPerfiles().remove(usuario);
    }

}
