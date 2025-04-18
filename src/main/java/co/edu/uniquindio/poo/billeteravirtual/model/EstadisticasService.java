package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.*;
import java.util.stream.Collectors;

public class EstadisticasService {

    public String gastoMasComun(List<Usuario> usuarios) {
        Map<String, Integer> conteoCategorias = new HashMap<>();

        for (Usuario usuario : usuarios) {
            for (Transaccion t : usuario.getTransacciones()) {
                if (t.getCategoria() != null) {
                    String nombre = t.getCategoria().getNombre();
                    conteoCategorias.put(nombre, conteoCategorias.getOrDefault(nombre, 0) + 1);
                }
            }
        }

        return conteoCategorias.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Ninguna");
    }

    public Usuario usuarioConMasTransacciones(List<Usuario> usuarios) {
        return usuarios.stream()
                .max(Comparator.comparingInt(u -> u.getTransacciones().size()))
                .orElse(null);
    }

    public double saldoPromedio(List<Usuario> usuarios) {
        return usuarios.stream()
                .mapToDouble(Usuario::getSaldo)
                .average()
                .orElse(0.0);
    }
}
