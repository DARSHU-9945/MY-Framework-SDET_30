package com.crm.GenericLibrary;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 
 * @author Deepthi
 *
 */

public class ExcellFileUtility {
	
	/**
	 * this method will read the value from excel sheet and return the value when sheetname row no cell no is specified
	 * @param SheetName
	 * @param rowNo
	 * @param cellno
	 * @return
	 * @throws Throwable 
	 */
	
	public String readDataFromExcel(String SheetName, int rowNo, int celno) throws Throwable {
		
		FileInputStream fis = new FileInputStream(IpathConstants.Excelpath);
		Workbook wb= WorkbookFactory.create(fis);
		Sheet sh= wb.getSheet(SheetName);
		Row ro= sh.getRow(rowNo);
		Cell cel=ro.getCell(celno);
		String value=cel.getStringCellValue();
		return value;
	}
	
	/**
	 * this method will return last row number
	 * @param SheetName
	 * @param rowNo
	 * @param celNO
	 * @param value
	 * @throws Throwable 
	 */
	public void writeDataIntoExcel(String SheetName, int rowNo, int celNO, String value) throws Throwable {
		
		FileInputStream fis = new FileInputStream(IpathConstants.Excelpath);
		Workbook wb= WorkbookFactory.create(fis);
		Sheet sh= wb.getSheet(SheetName);
		Row ro= sh.getRow(rowNo);
		Cell cel=ro.createCell(celNO);
		cel.setCellValue(value);
		FileOutputStream fos = new FileOutputStream(IpathConstants.Excelpath);
		wb.write(fos);
	
	}
	
	public int getRowCount(String SheetName) throws Throwable
	{
		FileInputStream fis = new FileInputStream(IpathConstants.Excelpath);
		Workbook wb= WorkbookFactory.create(fis);
		Sheet sh= wb.getSheet(SheetName);
		int row=sh.getLastRowNum();
		return row;
		
	}
	
public String readNumericDataFromExcel(String SheetName, int rowNo, int celno) throws Throwable {
		
		FileInputStream fis = new FileInputStream(IpathConstants.Excelpath);
		Workbook wb= WorkbookFactory.create(fis);
		Sheet sh= wb.getSheet(SheetName);
		Row ro= sh.getRow(rowNo);
		Cell cel=ro.getCell(celno);
		double value=cel.getNumericCellValue();
		int celvalue=(int)value;
		Integer integer=(Integer)celvalue;
		String value1=integer.toString();
		return value1;
	}

/**
 * This method will read multiple data from excel sheet with the help of sheetname
 * and return 2 dimensional object [][]
 * @param SheetName
 * @return
 * @throws Throwable
 */
public Object[][] readmultipleDataFromExcel(String SheetName, int n) throws Throwable
{
	FileInputStream fis = new FileInputStream(IpathConstants.Excelpath);
	Workbook wb = WorkbookFactory.create(fis);
	Sheet sh = wb.getSheet(SheetName);
	int lastRow = sh.getLastRowNum();
	int lastCell = sh.getRow(0).getLastCellNum();
	
	Object[][] data = new Object[lastRow][lastCell-n];
	
	for(int i = 0;i<lastRow;i++)
	{
		for(int j=0;j<lastCell-n ;j++)
		{
			data[i][j]=sh.getRow(i+1).getCell(j).getStringCellValue();
		}
	}
	
	return data;

}

}






