package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class XLUtility {
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;

	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	
	String path;

	public XLUtility(String path) {
		super();
		this.path = path;
	}
	
	
	public int getRowCount(String sheetName) throws IOException {
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowCount;
		
	}
	
	
	public int getCellCount(String sheetName, int rownum) throws IOException {

		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellCount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellCount;
	}
	
		
	public String getCellData(String sheetName, int rownum, int column) throws IOException {
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(column);//get cell value
		
		DataFormatter formatter = new DataFormatter();
		
		String data;
		
		
	try {
		
		 data = formatter.formatCellValue(cell);// Returns the formatted value of a cell as a String regardless of data
	}
	catch(Exception e)
	{
		 data = "";
		
	}
	workbook.close();
	fi.close();
	
		return data;
	}
	
	
	public void setCellData(String sheetName, int rownum, int column, String data) throws IOException {
		
		
		File xlfile = new File(path);
		if(!xlfile.exists())// If sheet not exists the create new sheet
		{
			workbook = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			workbook.write(fo);
			
		}
		
		fi = new FileInputStream(path);
			workbook = new XSSFWorkbook(fi);
			
			if(workbook.getSheetIndex(sheet) ==-1)
		
				workbook.createSheet(sheetName);// Create New Sheet
			sheet = workbook.getSheet(sheetName);
			
			if(sheet.getRow(rownum) == null)// if row not exists then create new row
				sheet.createRow(rownum);//Create new row
			row = sheet.getRow(rownum);
			
			cell = row.createCell(column);// create new Column
			cell.setCellValue(data);// Write new Value
			
			fo = new FileOutputStream(path);
			workbook.write(fo);
			workbook.close();
			fi.close();
			fo.close();
			
	}
	
	
	public void fillGreenColor(String sheetName, int rownum, int column) throws IOException {
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet =workbook.getSheet(sheetName);
		
		row = sheet.getRow(rownum);
		cell = row.getCell(column);
		
		
		style = workbook.createCellStyle();
		
		
				style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				cell.setCellStyle(style);
				workbook.write(fo);
				workbook.close();
				fi.close();
				fo.close();
		
		
	}
	
	
	public void fillRedColor(String sheetName, int rownum, int column) throws IOException {
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet =workbook.getSheet(sheetName);
		
		row = sheet.getRow(rownum);
		cell = row.getCell(column);
		
		
		style = workbook.createCellStyle();
		
		
				style.setFillForegroundColor(IndexedColors.RED.getIndex());
				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				cell.setCellStyle(style);
				workbook.write(fo);
				workbook.close();
				fi.close();
				fo.close();
		
		
	}
	
	
	
	
}