package com.petstore.api.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
    public FileInputStream fi;
    public File src;
    public FileOutputStream fo;
    public XSSFWorkbook wb;
    public XSSFSheet ws;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    private final DataFormatter dataFormatter = new DataFormatter();

    public XLUtility(String path) {
        try {
            src = new File(path);
            fi = new FileInputStream(src);
            wb = new XSSFWorkbook(fi);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getRowCount(String sheetName) {
        ws = wb.getSheet(sheetName);
        int rowCount = ws.getLastRowNum();
        return rowCount;
    }

    public int getCellCount(String sheetName, int rownum) {
        ws = wb.getSheet(sheetName);
        row = ws.getRow(rownum);
        int cellCount = row.getLastCellNum();
        return cellCount;
    }

    public String getCellData(String sheetName, int rownum, int colnum) {
        ws = wb.getSheet(sheetName);
        row = ws.getRow(rownum);
        if (row == null) {
            return "";
        }

        cell = row.getCell(colnum, MissingCellPolicy.RETURN_BLANK_AS_NULL);
        if (cell == null) {
            return "";
        }

        try {
            return dataFormatter.formatCellValue(cell).trim();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public void setCellData(String sheetName, int rownum, int colnum, String data) {
        try {
            ws = wb.getSheet(sheetName);
            row = ws.getRow(rownum);
            cell = row.createCell(colnum);
            cell.setCellValue(data);
            fo = new FileOutputStream(src);
            wb.write(fo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setGreenColor(String sheetName, int rownum, int colnum) {
        try {
            ws = wb.getSheet(sheetName);
            row = ws.getRow(rownum);
            cell = row.getCell(colnum);
            style = wb.createCellStyle();
            style.setFillForegroundColor(org.apache.poi.ss.usermodel.IndexedColors.GREEN.getIndex());
            style.setFillPattern(org.apache.poi.ss.usermodel.FillPatternType.SOLID_FOREGROUND);
            cell.setCellStyle(style);
            fo = new FileOutputStream(src);
            wb.write(fo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setRedColor(String sheetName, int rownum, int colnum) {
        try {
            ws = wb.getSheet(sheetName);
            row = ws.getRow(rownum);
            cell = row.getCell(colnum);
            style = wb.createCellStyle();
            style.setFillForegroundColor(org.apache.poi.ss.usermodel.IndexedColors.RED.getIndex());
            style.setFillPattern(org.apache.poi.ss.usermodel.FillPatternType.SOLID_FOREGROUND);
            cell.setCellStyle(style);
            fo = new FileOutputStream(src);
            wb.write(fo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
