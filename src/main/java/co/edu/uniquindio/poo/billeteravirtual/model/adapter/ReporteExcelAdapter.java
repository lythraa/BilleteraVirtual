package co.edu.uniquindio.poo.billeteravirtual.model.adapter;

import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import co.edu.uniquindio.poo.billeteravirtual.model.builder.Movimiento;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ReporteExcelAdapter implements ReporteExportable {
    @Override
    public void exportarReporte(Usuario usuario, String rutaDestino) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet hoja = workbook.createSheet("Movimientos");

        Row encabezado = hoja.createRow(0);
        encabezado.createCell(0).setCellValue("Fecha");
        encabezado.createCell(1).setCellValue("Tipo");
        encabezado.createCell(2).setCellValue("Monto");
        encabezado.createCell(3).setCellValue("Categoría");

        int fila = 1;
        for (Movimiento movimiento : usuario.getHistorialMovimientos()) {
            Row row = hoja.createRow(fila++);
            row.createCell(0).setCellValue(movimiento.getFecha().toString());
            row.createCell(1).setCellValue(movimiento.getEstrategia().getClass().getSimpleName());
            row.createCell(2).setCellValue(movimiento.getMonto());
            row.createCell(3).setCellValue(movimiento.getCategoriaOpcional().getId_Nombre());
        }

        try (FileOutputStream out = new FileOutputStream(rutaDestino)) {
            workbook.write(out);
        }
        workbook.close();
    }
}
