package co.edu.uniquindio.poo.billeteravirtual.model.adapter;

import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;

public interface ReporteExportable {
    void exportarReporte(Usuario usuario, String rutaDestino) throws Exception;

}
