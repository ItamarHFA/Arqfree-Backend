package com.diagnostico.proyecto.Arqfre.reporte.excel;

import com.diagnostico.proyecto.Arqfre.model.Proyecto;
import com.diagnostico.proyecto.Arqfre.reporte.excel.service.GoogleDriveService;
import com.diagnostico.proyecto.Arqfre.service.IProyectoService;
import com.google.api.services.drive.model.File;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
public class ExcelUpdateService {

    @Autowired
    private GoogleDriveService googleDriveService;

    @Autowired
    private IProyectoService proyectoService;

    private static final String FOLDER_ID = "1dt6pOxSk0GuyKoVmUjsb1ciSftkfnPcH";
    private static final String FILE_NAME = "Reporte Diagnostico de Proyectos.xlsx";
    private String fileId;

    @Scheduled(fixedRate = 60000) // Actualizar cada minuto
    public void updateExcelFile() throws IOException, GeneralSecurityException {
        List<Proyecto> proyectos = proyectoService.obtenerProyectosDeHoy();

        InputStream inputStream;
        Workbook workbook;
        File googleFile = googleDriveService.getFileByNameAndFolder(FILE_NAME, FOLDER_ID);
        if (googleFile != null) {
            fileId = googleFile.getId();
            inputStream = googleDriveService.downloadExcelFile(fileId);
            workbook = new XSSFWorkbook(inputStream);
        } else {
            workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Diagnostico de Proyecto");

            // Estilo para el título
            CellStyle titleCellStyle = workbook.createCellStyle();
            Font titleFont = workbook.createFont();
            titleFont.setFontName("Aptos Navarrow");
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 18);
            titleCellStyle.setFont(titleFont);
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            // Crear fila de título
            Row titleRow1 = sheet.createRow(2);
            Row titleRow2 = sheet.createRow(3);
            Cell titleCell = titleRow1.createCell(4);
            titleCell.setCellValue("Diagnostico de Proyecto");
            titleCell.setCellStyle(titleCellStyle);

            // Fusionar celdas para el título
            sheet.addMergedRegion(new CellRangeAddress(2, 3, 4, 10));

            // Estilo para la cabecera
            CellStyle headerCellStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setFontName("Calibri");
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12);
            headerCellStyle.setFont(headerFont);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerCellStyle.setBorderTop(BorderStyle.THIN);
            headerCellStyle.setBorderBottom(BorderStyle.THIN);
            headerCellStyle.setBorderLeft(BorderStyle.THIN);
            headerCellStyle.setBorderRight(BorderStyle.THIN);

            // Crear fila de cabecera
            Row headerRow = sheet.createRow(5);

            String[] headers = {"N°", "Nombre Cliente", "Número Celular", "Correo", "Tipo Proyecto", "Área", "Piso", "Estilo Fachada", "Número Integrantes", "Colores Favoritos", "Mascota", "Espacio Favorito", "Automóvil", "Referencia Vivienda"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i + 1);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
            }

            // Escribir en un ByteArrayOutputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();

            // Convertir a ByteArrayInputStream
            byte[] excelBytes = outputStream.toByteArray();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(excelBytes);

            // Crear el archivo en Google Drive
            fileId = googleDriveService.createExcelFile(FOLDER_ID, FILE_NAME, byteArrayInputStream);
        }

        Sheet sheet = workbook.getSheetAt(0);

        // Limpiar las filas existentes
        int rowNum = sheet.getLastRowNum();
        for (int i = 6; i <= rowNum; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                sheet.removeRow(row);
            }
        }

        // Estilo para las celdas de datos
        CellStyle dataCellStyle = workbook.createCellStyle();
        dataCellStyle.setAlignment(HorizontalAlignment.CENTER);
        dataCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        dataCellStyle.setBorderTop(BorderStyle.THIN);
        dataCellStyle.setBorderBottom(BorderStyle.THIN);
        dataCellStyle.setBorderLeft(BorderStyle.THIN);
        dataCellStyle.setBorderRight(BorderStyle.THIN);

        // Headers para la tabla
        String[] headers = {"N°", "Nombre Cliente", "Número Celular", "Correo", "Tipo Proyecto", "Área", "Piso", "Estilo Fachada", "Número Integrantes", "Colores Favoritos", "Mascota", "Espacio Favorito", "Automóvil", "Referencia Vivienda"};

        // Llenar los datos nuevos
        rowNum = 6;
        for (int i = 0; i < proyectos.size(); i++) {
            Proyecto proyecto = proyectos.get(i);
            Row row = sheet.createRow(rowNum++);
            row.createCell(1).setCellValue(i + 1);
            row.createCell(2).setCellValue(proyecto.getNombreCliente());
            row.createCell(3).setCellValue(proyecto.getNumeroCelular());
            row.createCell(4).setCellValue(proyecto.getCorreo());
            row.createCell(5).setCellValue(proyecto.getTipoProyecto().getDescripcion());
            row.createCell(6).setCellValue(proyecto.getArea());
            row.createCell(7).setCellValue(proyecto.getPiso().getDescripcion());
            row.createCell(8).setCellValue(proyecto.getEstiloFachada().getDescripcion());
            row.createCell(9).setCellValue(proyecto.getNumeroIntegrantes());
            row.createCell(10).setCellValue(proyecto.getColoresFavoritos());
            row.createCell(11).setCellValue(proyecto.getMascota());
            row.createCell(12).setCellValue(proyecto.getEspacioFavorito());
            row.createCell(13).setCellValue(proyecto.getAutomovil());
            row.createCell(14).setCellValue(proyecto.getReferenciaVivienda());

            for (int j = 1; j <= headers.length; j++) {
                row.getCell(j).setCellStyle(dataCellStyle);
            }
        }

        // Autoajustar el tamaño de las columnas
        for (int i = 1; i <= headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Escribir en un ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        // Convertir a ByteArrayInputStream
        byte[] excelBytes = outputStream.toByteArray();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(excelBytes);

        // Actualizar el archivo en Google Drive
        googleDriveService.updateExcelFile(fileId, byteArrayInputStream);
    }
}