package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.List;

/**
 * Clase genérica que maneja operaciones CRUD básicas para cualquier tipo de objeto que implemente Identificable.
 */
public class GestorBaseCRUD<T extends Identificable> {

    protected final List<T> listaObjetos;

    /**
     * Constructor del gestor CRUD.
     * @param listaObjetos lista inicial de objetos
     *
     */
    public GestorBaseCRUD(List<T> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    /**
     * Agrega un nuevo objeto si no está ya en la lista (según equals).
     *
     * @param objeto objeto a agregar
     * @throws IllegalArgumentException si el objeto es null
     */
    public void agregar(T objeto) {
        if (objeto == null) {
            throw new IllegalArgumentException("El objeto a agregar no puede ser null.");
        }

        if (!listaObjetos.contains(objeto)) {
            listaObjetos.add(objeto);
        }
    }

    /**
     * Busca un objeto por su ID.
     * @param id ID del objeto a buscar
     * @return objeto encontrado o null si no existe
     * @throws IllegalArgumentException si el id es null o vacío
     */
    public T buscar(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("El ID a buscar no puede ser null ni vacío.");
        }

        for (T objeto : listaObjetos) {
            if (objeto.getId().equals(id)) {
                return objeto;
            }
        }
        return null;
    }

    /**
     * Elimina un objeto de la lista
     * @param objeto objeto a eliminar
     * @throws IllegalArgumentException si el objeto es null
     */
    public void eliminar(T objeto) {
        if (objeto == null) {
            throw new IllegalArgumentException("El objeto a eliminar no puede ser null.");
        }

        listaObjetos.remove(objeto);
    }

    /**
     * Reemplaza un objeto existente con uno nuevo, usando el mismo ID.
     * @param id ID del objeto a reemplazar
     * @param nuevoObjeto nuevo objeto que reemplazará al existente
     * @return true si se reemplazó, false si no se encontró el objeto a reemplazar
     * @throws IllegalArgumentException si el id es null o vacío, o si el nuevoObjeto es null
     */
    public boolean  reemplazar(String id, T nuevoObjeto) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("El ID para reemplazar no puede ser null ni vacío.");
        }
        if (nuevoObjeto == null) {
            throw new IllegalArgumentException("El nuevo objeto no puede ser null.");
        }

        for (int i = 0; i < listaObjetos.size(); i++) {
            if (listaObjetos.get(i).getId().equals(id)) {
                listaObjetos.set(i, nuevoObjeto);
                return true;
            }
        }
        return false;
    }

    /**
     * Devuelve la lista completa de objetos.
     * @return lista de objetos
     */
    public List<T> getListaObjetos() {
        return listaObjetos;
    }
}
