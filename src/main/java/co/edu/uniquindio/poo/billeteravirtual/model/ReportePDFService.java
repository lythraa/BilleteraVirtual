package co.edu.uniquindio.poo.billeteravirtual.model;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ReportePDFService implements ReporteExportable {

    /**
     * Implementación de la interface ReporteExportable para exportar reportes en pdf
     * @param usuario usuario en sesión
     * @param rutaDestino ruta del pc a la cual se descargará el archivo
     */
    @Override
    public void exportarReporte(Usuario usuario, String rutaDestino) throws IOException {
        PDDocument documento = new PDDocument();
        PDPage pagina = new PDPage(PDRectangle.LETTER);
        documento.addPage(pagina);

        PDPageContentStream contenido = new PDPageContentStream(documento, pagina);
        contenido.setLeading(20f);

        String fechaActual = java.time.LocalDate.now().toString();
        double ingresos = usuario.calcularIngresosTotales();
        double gastos = usuario.calcularGastosTotales();
        double saldo = usuario.getSaldoTotal();

        // Título centrado
        contenido.beginText();
        contenido.setFont(PDType1Font.HELVETICA_BOLD, 20);
        float titleWidth = PDType1Font.HELVETICA_BOLD.getStringWidth("Reporte de Movimientos") / 1000 * 20;
        float startX = (pagina.getMediaBox().getWidth() - titleWidth) / 2;
        contenido.newLineAtOffset(startX, 750);
        contenido.showText("Reporte de Movimientos");
        contenido.endText();

        // Información general
        contenido.beginText();
        contenido.setFont(PDType1Font.HELVETICA_BOLD, 14);
        contenido.newLineAtOffset(50, 710);
        contenido.showText("Fecha: ");
        contenido.setFont(PDType1Font.HELVETICA, 14);
        contenido.showText(fechaActual);
        contenido.newLine();

        contenido.setFont(PDType1Font.HELVETICA_BOLD, 14);
        contenido.showText("Usuario: ");
        contenido.setFont(PDType1Font.HELVETICA, 14);
        contenido.showText(usuario.getNombre());
        contenido.newLine();
        contenido.newLine();

        // Lista de movimientos
        contenido.setFont(PDType1Font.HELVETICA_BOLD, 14);
        contenido.showText("Movimientos:");
        contenido.newLine();

        for (Movimiento movimiento : usuario.getHistorialMovimientos()) {
            String linea = getString(movimiento);
            contenido.setFont(PDType1Font.HELVETICA, 12);
            contenido.showText(linea);
            contenido.newLine();
        }

        contenido.newLine();
        contenido.setFont(PDType1Font.HELVETICA_BOLD, 14);
        contenido.showText("Resumen financiero:");
        contenido.newLine();

        contenido.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contenido.showText("Ingresos totales: ");
        contenido.setFont(PDType1Font.HELVETICA, 12);
        contenido.showText(String.format("%.2f", ingresos));
        contenido.newLine();

        contenido.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contenido.showText("Egresos totales: ");
        contenido.setFont(PDType1Font.HELVETICA, 12);
        contenido.showText(String.format("%.2f", gastos));
        contenido.newLine();

        contenido.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contenido.showText(String.format("El saldo disponible a fecha de %s es de: ", fechaActual));
        contenido.setFont(PDType1Font.HELVETICA, 12);
        contenido.showText(String.format("%.2f", saldo));

        contenido.endText();
        contenido.close();
        documento.save(rutaDestino);
        documento.close();
    }


    private static @NotNull String getString(Movimiento movimiento) {
        String tipoMovimiento = "";

        if(movimiento.getEstrategia() instanceof DepositoStrategy){
            tipoMovimiento = "Deposito";
        }

        if(movimiento.getEstrategia() instanceof RetiroStrategy){
            tipoMovimiento = "Retiro";
        }

        if(movimiento.getEstrategia() instanceof TransferenciaStrategy){
            tipoMovimiento = "Transferencia";
        }

        return String.format("Fecha: %s | Tipo: %s | Monto: %.2f | Categoria: %s",
                movimiento.getFecha().toString(),
                tipoMovimiento,
                movimiento.getMonto(),
                movimiento.getCategoriaOpcional().getId());
    }

}
