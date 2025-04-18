package co.edu.uniquindio.poo.billeteravirtual.model;
import java.util.List;

public class Administrador extends Usuario {

    public Administrador(Builder builder) {
        super(builder);

    }

    public static class Builder extends Usuario.Builder {
        public Builder(String idUsuario, String nombreCompleto, String correo) {
            super(idUsuario, nombreCompleto, correo);
        }

        @Override
        public Administrador build() {
            return new Administrador(this);
        }
    }

    public void crearUsuario(List<Usuario> usuarios, Usuario nuevoUsuario) {
        usuarios.add(nuevoUsuario);
    }

    public void eliminarUsuario(List<Usuario> usuarios, Usuario usuario) {
        usuarios.remove(usuario);
    }

    public void actualizarUsuario(Usuario usuario, String nuevoNombre, String nuevoCorreo, String nuevoTelefono) {
        usuario.modificarPerfil(nuevoNombre, nuevoCorreo, nuevoTelefono);
    }

    public void agregarCuentaAUsuario(Usuario usuario, Cuenta cuenta) {
        usuario.agregarCuenta(cuenta);
    }

    public void eliminarCuentaDeUsuario(Usuario usuario, Cuenta cuenta) {
        usuario.eliminarCuenta(cuenta);
    }

    public void listarUsuarios(List<Usuario> usuarios) {
        for (Usuario u : usuarios) {
            System.out.println(u.getNombreCompleto() + " | " + u.getCorreo());
        }
    }

    public void listarTransaccionesDeUsuario(Usuario usuario) {
        for (Transaccion t : usuario.getTransacciones()) {
            System.out.println(t.getTipo() + ": " + t.getMonto() + " (" + t.getDescripcion() + ")");
        }
    }
}
