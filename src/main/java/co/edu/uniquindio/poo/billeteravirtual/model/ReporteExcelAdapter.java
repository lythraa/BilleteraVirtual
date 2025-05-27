package co.edu.uniquindio.poo.billeteravirtual.model;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ReporteExcelAdapter implements ReporteExportable {
    /**
     * Implementación de la interface ReporteExportable propia para hojas de cálculo
     * @param usuario usuario en sesión
     * @param rutaDestino ruta del pc a la cual se descargará el archivo
     * @throws IOException si algo sale mal
     */
    @Override
    public void exportarReporte(Usuario usuario, String rutaDestino) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet hoja = workbook.createSheet("Reporte de Movimientos");

        CellStyle estiloTitulo = workbook.createCellStyle();
        Font fuenteTitulo = workbook.createFont();
        fuenteTitulo.setBold(true);
        fuenteTitulo.setFontHeightInPoints((short) 16);
        estiloTitulo.setFont(fuenteTitulo);
        estiloTitulo.setAlignment(HorizontalAlignment.CENTER);

        CellStyle estiloNegrita = workbook.createCellStyle();
        Font fuenteNegrita = workbook.createFont();
        fuenteNegrita.setBold(true);
        estiloNegrita.setFont(fuenteNegrita);

        CellStyle estiloEncabezado = workbook.createCellStyle();
        Font fuenteEncabezado = workbook.createFont();
        fuenteEncabezado.setBold(true);
        fuenteEncabezado.setColor(IndexedColors.WHITE.getIndex());
        estiloEncabezado.setFont(fuenteEncabezado);
        estiloEncabezado.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        estiloEncabezado.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        estiloEncabezado.setAlignment(HorizontalAlignment.CENTER);
        estiloEncabezado.setBorderBottom(BorderStyle.THIN);
        estiloEncabezado.setBorderTop(BorderStyle.THIN);
        estiloEncabezado.setBorderLeft(BorderStyle.THIN);
        estiloEncabezado.setBorderRight(BorderStyle.THIN);

        CellStyle estiloCelda = workbook.createCellStyle();
        estiloCelda.setBorderBottom(BorderStyle.THIN);
        estiloCelda.setBorderTop(BorderStyle.THIN);
        estiloCelda.setBorderLeft(BorderStyle.THIN);
        estiloCelda.setBorderRight(BorderStyle.THIN);

        CellStyle estiloResumen = workbook.createCellStyle();
        estiloResumen.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        estiloResumen.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        estiloResumen.setBorderBottom(BorderStyle.THIN);
        estiloResumen.setBorderTop(BorderStyle.THIN);
        estiloResumen.setBorderLeft(BorderStyle.THIN);
        estiloResumen.setBorderRight(BorderStyle.THIN);
        Font fuenteResumen = workbook.createFont();
        fuenteResumen.setBold(true);
        estiloResumen.setFont(fuenteResumen);

        int fila = 0;

        Row filaTitulo = hoja.createRow(fila++);
        Cell celdaTitulo = filaTitulo.createCell(0);
        celdaTitulo.setCellValue("Reporte de Movimientos");
        celdaTitulo.setCellStyle(estiloTitulo);
        hoja.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, 0, 0, 3));

        Row filaFecha = hoja.createRow(fila++);
        filaFecha.createCell(0).setCellValue("Fecha:");
        filaFecha.getCell(0).setCellStyle(estiloNegrita);
        filaFecha.createCell(1).setCellValue(java.time.LocalDate.now().toString());

        Row filaUsuario = hoja.createRow(fila++);
        filaUsuario.createCell(0).setCellValue("Usuario:");
        filaUsuario.getCell(0).setCellStyle(estiloNegrita);
        filaUsuario.createCell(1).setCellValue(usuario.getNombre());

        fila++;

        Row encabezado = hoja.createRow(fila++);
        String[] titulos = {"Fecha", "Tipo", "Monto", "Categoría"};
        for (int i = 0; i < titulos.length; i++) {
            Cell celda = encabezado.createCell(i);
            celda.setCellValue(titulos[i]);
            celda.setCellStyle(estiloEncabezado);
        }

        for (Movimiento movimiento : usuario.getHistorialMovimientos()) {
            Row row = hoja.createRow(fila++);
            row.createCell(0).setCellValue(movimiento.getFecha().toString());
            row.createCell(1).setCellValue(getTipoMovimiento(movimiento));
            row.createCell(2).setCellValue(movimiento.getMonto());
            row.createCell(3).setCellValue(movimiento.getCategoriaOpcional().getId());
            for (int i = 0; i < 4; i++) {
                row.getCell(i).setCellStyle(estiloCelda);
            }
        }

        fila++;

        String fechaActual = java.time.LocalDate.now().toString();
        Row resumen1 = hoja.createRow(fila++);
        resumen1.createCell(0).setCellValue("Ingresos totales:");
        resumen1.createCell(1).setCellValue(usuario.calcularIngresosTotales());
        resumen1.getCell(0).setCellStyle(estiloResumen);
        resumen1.getCell(1).setCellStyle(estiloResumen);

        Row resumen2 = hoja.createRow(fila++);
        resumen2.createCell(0).setCellValue("Egresos totales:");
        resumen2.createCell(1).setCellValue(usuario.calcularGastosTotales());
        resumen2.getCell(0).setCellStyle(estiloResumen);
        resumen2.getCell(1).setCellStyle(estiloResumen);

        Row resumen3 = hoja.createRow(fila++);
        resumen3.createCell(0).setCellValue("El saldo disponible a fecha de " + fechaActual + " es de:");
        resumen3.createCell(1).setCellValue(usuario.getSaldoTotal());
        resumen3.getCell(0).setCellStyle(estiloResumen);
        resumen3.getCell(1).setCellStyle(estiloResumen);

        for (int i = 0; i < 4; i++) {
            hoja.autoSizeColumn(i);
        }

        try (FileOutputStream out = new FileOutputStream(rutaDestino)) {
            workbook.write(out);
        }
        workbook.close();
    }

    /**
     * Separa la lógica de obtención del tipo de movimiento según la instancia de su estrategia
     * @param movimiento por analizar
     * @return string con el tipo de movimiento
     */
    private String getTipoMovimiento(Movimiento movimiento) {
        if (movimiento.getEstrategia() instanceof DepositoStrategy) {
            return "Depósito";
        } else if (movimiento.getEstrategia() instanceof RetiroStrategy) {
            return "Retiro";
        } else if (movimiento.getEstrategia() instanceof TransferenciaStrategy) {
            return "Transferencia";
        }
        return "Otro";
    }

}
