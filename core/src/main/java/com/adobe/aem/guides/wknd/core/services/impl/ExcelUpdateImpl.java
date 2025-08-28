package com.adobe.aem.guides.wknd.core.services.impl;

import com.adobe.aem.guides.wknd.core.services.ExcelUpdate;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.osgi.service.component.annotations.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Component(service=ExcelUpdate.class,immediate = true)
public class ExcelUpdateImpl implements ExcelUpdate {
    @Override
    public void UpdateExcel(List<String> paths) {

// Create a new Excel workbook
        Workbook workbook = new XSSFWorkbook();

// Create a new sheet
        Sheet sheet = workbook.createSheet("Employee Data");

// Create the header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Paths");

        for (int i = 0; i < paths.size(); i++) {
            Row row = sheet.createRow(i + 1); // Start from the second row
            // Employee Name
            row.createCell(0).setCellValue(paths.get(i));
        }

// Save the Excel file to a local directory
        try (FileOutputStream fileOut = new FileOutputStream("EmployeeData.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
