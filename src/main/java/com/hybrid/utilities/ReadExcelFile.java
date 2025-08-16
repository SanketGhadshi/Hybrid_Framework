package com.hybrid.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {

	public ArrayList<String> getData(String fileName, String sheetName, String testcaseName) throws IOException {
		
    	 ArrayList<String> a = new ArrayList<String>();
         FileInputStream fis = new FileInputStream(fileName);
         Workbook workbook = WorkbookFactory.create(fis);
         int sheets = workbook.getNumberOfSheets();
         for (int i = 0; i < sheets; i++) 
         {
             if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) 
             {
                 Sheet sheet = workbook.getSheetAt(i);
                 Iterator<Row> rows = sheet.iterator(); // Sheet is a collection of rows
                 Row firstRow = rows.next();
                 Iterator<Cell> ce = firstRow.cellIterator(); // Row is a collection of cells
                 int k = 0;            // Index counter
                 int column = 0;       // Variable to store the index of "TestCases" column

                 while (ce.hasNext()) 
                 {
                     Cell value = ce.next();
                     if (value.getCellType()== CellType.STRING && value.getStringCellValue().equalsIgnoreCase("TestCases")) // "TestCases" is cell value in excel file
                     {
                         column = k;
                     }
                     k++;
                 }
                 while (rows.hasNext()) 
                 {
                     Row r = rows.next();
                     if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) 
                     {
                         Iterator<Cell> cv = r.cellIterator();
                         while (cv.hasNext()) 
                         {
                             Cell c = cv.next();
                             if (c.getCellType() == CellType.STRING) 
                             {
                                 a.add(c.getStringCellValue());
                             }
                             else {
                                 a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                             }
                         }
                     }
                 }
             }
         }
         return a;
     }

     }