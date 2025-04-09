package co.edu.uniquindio.poo.billeteravirtual.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GestorDeReportes {
    private static GestorDeReportes instancia;

    private GestorDeReportes() {
    }

    public static GestorDeReportes getInstancia() {
        if (instancia == null) {
            instancia = new GestorDeReportes();
        }
        return instancia;
    }

    public void exportarCSV(Usuario usuario, String rutaArchivo) throws IOException {
        FileWriter writer = new FileWriter(rutaArchivo);
        writer.write("Fecha,Tipo,Monto,Descripcion,Categoria\n");

        for (Transaccion t : usuario.getTransacciones()) {
            writer.write(String.format("%s,%s,%.2f,%s,%s\n",
                    t.getFecha(),
                    t.getTipo(),
                    t.getMonto(),
                    t.getDescripcion(),
                    t.getCategoria() != null ? t.getCategoria().getNombre() : "N/A"));
        }

        writer.close();
    }
}
