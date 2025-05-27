package co.edu.uniquindio.poo.billeteravirtual.model.adapter;

import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import co.edu.uniquindio.poo.billeteravirtual.model.builder.Movimiento;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.IOException;

public class ReportePDFService implements ReporteExportable {

    @Override
    public void exportarReporte(Usuario usuario, String rutaDestino) throws IOException {
        PDDocument documento = new PDDocument();
        PDPage pagina = new PDPage(PDRectangle.LETTER);
        documento.addPage(pagina);

        PDPageContentStream contenido = new PDPageContentStream(documento, pagina);
        contenido.setFont(PDType1Font.HELVETICA_BOLD, 14);
        contenido.beginText();
        contenido.setLeading(20f);
        contenido.newLineAtOffset(50, 700);

        contenido.showText("Reporte de Movimientos - " + usuario.getNombre());
        contenido.newLine();
        contenido.newLine();

        for (Movimiento movimiento : usuario.getHistorialMovimientos()) {
            String linea = String.format("Fecha: %s | Tipo: %s | Monto: %.2f | Categoria: %s",
                    movimiento.getFecha().toString(),
                    movimiento.getEstrategia().getClass().getSimpleName(),
                    movimiento.getMonto(),
                    movimiento.getCategoriaOpcional().getId_Nombre());
            contenido.showText(linea);
            contenido.newLine();
        }

        contenido.endText();
        contenido.close();
        documento.save(rutaDestino);
        documento.close();
    }

}
