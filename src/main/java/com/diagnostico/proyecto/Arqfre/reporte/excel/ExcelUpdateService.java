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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        // Declaramos headers aquí para asegurarnos de que está disponible en todo el método
        String[] headers = {"N°", "Nombre Cliente", "Número Celular", "Correo", "Tipo Proyecto", "Área", "Piso", "Estilo Fachada", "Número Integrantes", "Colores Favoritos", "Mascota", "Espacio Favorito", "Automóvil", "Referencia Vivienda", "Fecha"};

        if (googleFile != null) {
            fileId = googleFile.getId();
            inputStream = googleDriveService.downloadExcelFile(fileId);
            workbook = new XSSFWorkbook(inputStream);
        } else {
            workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Diagnostico de Proyecto");

            // Configuración de estilo y celda para el título
            Row titleRow = sheet.createRow(3);
            Cell titleCell = titleRow.createCell(4);
            titleCell.setCellValue("Diagnostico de Proyecto");
            CellStyle titleCellStyle = workbook.createCellStyle();
            Font titleFont = workbook.createFont();
            titleFont.setFontName("Arial");
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 18);
            titleCellStyle.setFont(titleFont);
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);
            sheet.addMergedRegion(new CellRangeAddress(3, 4, 4, 10));

            // Configuración de estilo y celdas para los encabezados
            Row headerRow = sheet.createRow(5);
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

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i + 1);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
            }
        }

        Sheet sheet = workbook.getSheetAt(0);

        // Limpiar filas desde la fila 6 hacia adelante
        int lastRowNum = sheet.getLastRowNum();
        if (lastRowNum >= 5) {
            for (int i = lastRowNum; i >= 5; i--) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    sheet.removeRow(row);
                }
            }
        }

        // Estilo para las celdas de datos
        CellStyle dataCellStyle = workbook.createCellStyle();
        dataCellStyle.setAlignment(HorizontalAlignment.CENTER);
        dataCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        dataCellStyle.setBorderTop(BorderStyle.THIN);
        dataCellStyle.setBorderBottom(BorderStyle.THIN);
        dataCellStyle.setBorderLeft(BorderStyle.THIN);
        dataCellStyle.setBorderRight(BorderStyle.THIN);  // Corregido aquí

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Llenar los datos nuevos comenzando en la fila 6
        int rowNum = 5;
        for (Proyecto proyecto : proyectos) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(1).setCellValue(proyectos.indexOf(proyecto) + 1);
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
            String formattedDate = proyecto.getFechaCreacion().format(formatter);
            row.createCell(15).setCellValue(formattedDate);

            for (int j = 1; j <= headers.length; j++) {
                row.getCell(j).setCellStyle(dataCellStyle);
            }
        }

        // Autoajustar el tamaño de las columnas
        for (int i = 1; i <= headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Guardar y actualizar el archivo
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(outputStream.toByteArray());
        googleDriveService.updateExcelFile(fileId, byteArrayInputStream);
    }

    /*@Autowired
    private GoogleDriveService googleDriveService;

    @Autowired
    private IProyectoService proyectoService;

    private static final String FOLDER_ID = "1dt6pOxSk0GuyKoVmUjsb1ciSftkfnPcH";
    private static final String FILE_NAME = "Reporte Diagnostico de Proyectos.xlsx";
    private String fileId;

    //@Scheduled(cron = "0 0 0 * * ?") // Ejecuta el método a medianoche cada día
    @Scheduled(fixedRate = 60000) // Actualizar cada minuto
    public void updateExcelFile() throws IOException, GeneralSecurityException {
        List<Proyecto> proyectos = proyectoService.obtenerProyectosDeHoy();
        InputStream inputStream;
        Workbook workbook;
        File googleFile = googleDriveService.getFileByNameAndFolder(FILE_NAME, FOLDER_ID);

        // Declaramos headers aquí para asegurarnos de que está disponible en todo el método
        String[] headers = {"N°", "Nombre Cliente", "Número Celular", "Correo", "Tipo Proyecto", "Área", "Piso", "Estilo Fachada", "Número Integrantes", "Colores Favoritos", "Mascota", "Espacio Favorito", "Automóvil", "Referencia Vivienda", "Fecha"};

        if (googleFile != null) {
            fileId = googleFile.getId();
            inputStream = googleDriveService.downloadExcelFile(fileId);
            workbook = new XSSFWorkbook(inputStream);
        } else {
            workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Diagnostico de Proyecto");

            // Configuración de estilo y celda para el título
            Row titleRow = sheet.createRow(3);
            Cell titleCell = titleRow.createCell(4);
            titleCell.setCellValue("Diagnostico de Proyecto");
            CellStyle titleCellStyle = workbook.createCellStyle();
            Font titleFont = workbook.createFont();
            titleFont.setFontName("Arial");
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 18);
            titleCellStyle.setFont(titleFont);
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);
            sheet.addMergedRegion(new CellRangeAddress(3, 4, 4, 10));

            // Configuración de estilo y celdas para los encabezados
            Row headerRow = sheet.createRow(5);
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

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i + 1);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
            }
        }

        Sheet sheet = workbook.getSheetAt(0);

        // Si no hay registros del día actual, limpiar filas desde la fila 6 hacia adelante
        if (proyectos.isEmpty()) {
            int lastRowNum = sheet.getLastRowNum();
            if (lastRowNum >= 5) {
                for (int i = lastRowNum; i >= 5; i--) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        sheet.removeRow(row);
                    }
                }
            }
        } else {
            // Si hay registros, agregarlos sin borrar las filas previas
            int rowNum = sheet.getLastRowNum() + 1;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Estilo para las celdas de datos
            CellStyle dataCellStyle = workbook.createCellStyle();
            dataCellStyle.setAlignment(HorizontalAlignment.CENTER);
            dataCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            dataCellStyle.setBorderTop(BorderStyle.THIN);
            dataCellStyle.setBorderBottom(BorderStyle.THIN);
            dataCellStyle.setBorderLeft(BorderStyle.THIN);
            dataCellStyle.setBorderRight(BorderStyle.THIN);

            for (Proyecto proyecto : proyectos) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(1).setCellValue(proyectos.indexOf(proyecto) + 1);
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
                String formattedDate = proyecto.getFechaCreacion().format(formatter);
                row.createCell(15).setCellValue(formattedDate);

                for (int j = 1; j <= headers.length; j++) {
                    row.getCell(j).setCellStyle(dataCellStyle);
                }
            }
        }

        // Autoajustar el tamaño de las columnas
        for (int i = 1; i <= headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Guardar y actualizar el archivo
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(outputStream.toByteArray());
        googleDriveService.updateExcelFile(fileId, byteArrayInputStream);
    }*/





}