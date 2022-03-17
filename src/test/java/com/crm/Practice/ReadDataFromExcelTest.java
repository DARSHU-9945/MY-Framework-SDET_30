package com.crm.Practice;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class ReadDataFromExcelTest {
	
	@Test
	public void Readdatafromexcelfile() throws Throwable {
		
		// load xcell file
		
		FileInputStream fin = new FileInputStream(".\\src\\test\\resources\\Book1.xlsx");
		
		//create workbook
		
		Workbook wb = WorkbookFactory.create(fin);
		
		//get the sheet
		
		Sheet sh = wb.getSheet("Sheet1");
		
		// get the row
		
		Row ro = sh.getRow(0);
		
		// get the cell
		
		Cell ce = ro.getCell(1);
		
		// get the cell value
		
		String value= ce.getStringCellValue();
		
		System.out.println(value);
		
		
	}

}
