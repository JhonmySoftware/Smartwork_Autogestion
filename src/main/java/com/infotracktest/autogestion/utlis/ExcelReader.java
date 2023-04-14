package com.infotracktest.autogestion.utlis;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {

    public static List<Map<String, String>> readExcel(String filePath, String sheetName) throws IOException {
        List<Map<String, String>> excelData = new ArrayList<>();
        try (InputStream inputStream = Files.newInputStream(new File(filePath).toPath())) {
            Sheet sheet;
            if (filePath.endsWith(".xls")) { // Handle OLE2 format
                HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
                sheet = workbook.getSheet(sheetName);
            } else if (filePath.endsWith(".xlsx")) { // Handle OOXML format
                XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                sheet = workbook.getSheet(sheetName);
            } else {
                throw new IllegalArgumentException("File format is not supported: " + filePath);
            }
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet not found with name: " + sheetName);
            }
            int rowCount = sheet.getPhysicalNumberOfRows();
            Row headerRow = sheet.getRow(0);
            int columnCount = headerRow.getPhysicalNumberOfCells();
            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                Map<String, String> rowData = new LinkedHashMap<>();
                for (int j = 0; j < columnCount; j++) {
                    Cell cell = row.getCell(j);
                    String cellValue = cell == null ? "" : cell.toString();
                    String headerValue = headerRow.getCell(j).toString();
                    rowData.put(headerValue, cellValue);
                }
                excelData.add(rowData);
            }
        }
        return excelData;
    }
}




