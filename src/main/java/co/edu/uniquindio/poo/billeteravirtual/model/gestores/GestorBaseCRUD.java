package co.edu.uniquindio.poo.billeteravirtual.model.gestores;

import co.edu.uniquindio.poo.billeteravirtual.model.BaseDatos;
import co.edu.uniquindio.poo.billeteravirtual.model.Identificable;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase genérica que maneja operaciones CRUD básicas para cualquier tipo de objeto que implemente Identificable.
 */
public class GestorBaseCRUD<T extends Identificable> {

    protected List<T> listaObjetos;

    /**
     * Construcctor de la gestor de los crud
     * @param listaObjetos recibe un arraylist con la lista de datos
     */
    public GestorBaseCRUD(ArrayList<T> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    /**
     * Agrega un nuevo objeto si no está ya en la lista (según equals)
     * @param objeto objeto a agregar
     */
    public void agregar(T objeto) {
        if (!listaObjetos.contains(objeto)) {
            listaObjetos.add(objeto);
        }
    }

    /**
     * Busca un objeto por su ID
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
     * Reemplaza un objeto existente con uno nuevo, usando el mismo ID
     * @param id ID del objeto a reemplazar
     * @param nuevoObjeto nuevo objeto que reemplazará al existente
     */
    public void reemplazar(String id, T nuevoObjeto) {
        T existente = buscar(id);
        if (existente != null) {
            listaObjetos.remove(existente);
            listaObjetos.add(nuevoObjeto);
        }
    }

    /**
     * Devuelve la lista completa de objetos
     * @return lista de objetos
     */
    public List<T> getListaObjetos() {
        return listaObjetos;
    }
}
