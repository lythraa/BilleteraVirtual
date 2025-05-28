package co.edu.uniquindio.poo.billeteravirtual.model;

public interface ReporteExportable {
    /**
     * Método para exportar un reporte de los movimientos del usuario
     * @param usuario usuario en sesión
     * @param rutaDestino ruta del pc a la cual se descargará el archivo
     * @throws Exception si algo falla
     */
    void exportarReporte(Usuario usuario, String rutaDestino) throws Exception;

}
