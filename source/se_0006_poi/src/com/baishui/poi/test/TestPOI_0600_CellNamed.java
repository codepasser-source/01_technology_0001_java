package com.baishui.poi.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestPOI_0600_CellNamed {

	public static void main(String[] args) {
		long timeBegin = System.currentTimeMillis();
		try {
			
			//单元格命名
			Workbook wb = new XSSFWorkbook(); 
			Sheet sheet = wb.createSheet("sheetName");
			int rowNum;
			int cellNum;
			
			Row row = null;
			Cell cell = null;
			
			for (rowNum = 0; rowNum < 5; rowNum++) {
				row = sheet.createRow(rowNum);
				for (cellNum = 0; cellNum < 5; cellNum++) {
					cell = row.createCell(cellNum);
					cell.setCellValue("cell-"+rowNum+"-"+cellNum);
		
				}
			}
			Name namedCell = wb.createName();
		    namedCell.setNameName("cellNamed_0_0");
		    String reference = "sheetName!A1"; // cell reference
		    namedCell.setRefersToFormula(reference);
 
		    namedCell = wb.createName();
		    namedCell.setNameName("area_A2_C2");
		    namedCell.setRefersToFormula("sheetName!A2:C2");
		    
			FileOutputStream fileOut = new FileOutputStream("workbook_0600.xlsx");
			wb.write(fileOut);
			fileOut.close();
			
			
			//读取单元格命名
			
			/** 创建Excel文件 */
			FileInputStream fileIS = new FileInputStream("workbook_0600.xlsx");
			/** 创建workBook */
			Workbook workBook = WorkbookFactory.create(fileIS);
			
			int namedCellIndex = workBook.getNameIndex("cellNamed_0_0");
		    Name aNamedCell = workBook.getNameAt(namedCellIndex);

		    System.out.println("read cell getNameIndex('cellNamed_0_0') namedCellIdx:["+namedCellIndex+"]" +" getNameAt('namedCellIndex') aNamedCell:["+aNamedCell+"]");
		   
		    fileIS.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long timeEnd = System.currentTimeMillis();
		System.out.println("run time by " + (timeEnd - timeBegin) + "millis"+" file:[workbook_0600.xlsx]");
	}

}
