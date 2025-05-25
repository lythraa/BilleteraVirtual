package co.edu.uniquindio.poo.billeteravirtual.model.gestores;

import co.edu.uniquindio.poo.billeteravirtual.model.Identificable;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase genérica que maneja operaciones CRUD básicas para cualquier tipo de objeto que implemente Identificable.
 */
public class GestorBaseCRUD<T extends Identificable> {

    protected List<T> listaObjetos;

    /**
     * Constructor del gestor CRUD.
     * @param listaObjetos lista inicial de objetos
     */
    public GestorBaseCRUD(List<T> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    /**
     * Agrega un nuevo objeto si no está ya en la lista (según equals).
     * @param objeto objeto a agregar
     * @return true si se agregó, false si ya existía
     */
    public boolean agregar(T objeto) {
        if (!listaObjetos.contains(objeto)) {
            listaObjetos.add(objeto);
            return true;
        }
        return false;
    }

    /**
     * Busca un objeto por su ID.
     * @param id ID del objeto a buscar
     * @return objeto encontrado o null si no existe
     */
    public T buscar(String id) {
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
     */
    public void eliminar(T objeto) {
        listaObjetos.remove(objeto);
    }

    /**
     * Reemplaza un objeto existente con uno nuevo, usando el mismo ID.
     * @param id ID del objeto a reemplazar
     * @param nuevoObjeto nuevo objeto que reemplazará al existente
     * @return true si se reemplazó, false si no se encontró el objeto a reemplazar
     */
    public boolean  reemplazar(String id, T nuevoObjeto) {
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
