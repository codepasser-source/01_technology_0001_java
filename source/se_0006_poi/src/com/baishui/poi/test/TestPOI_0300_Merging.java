package com.baishui.poi.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class TestPOI_0300_Merging {

	public static void main(String[] args) {

		long timeBegin = System.currentTimeMillis();
		try {
			
			    Workbook wb = new XSSFWorkbook();
			    Sheet sheet = wb.createSheet("new sheet");

			    Row row = sheet.createRow((short) 0);
			    Cell cell = row.createCell((short) 0);
			    cell.setCellValue("This is a test of merging");
			    CellRangeAddress cellRangeAddress =  new CellRangeAddress( //合并单元格
			            0, //first row (0-based)
			            0, //last row  (0-based)
			            0, //first column (0-based)
			            1  //last column  (0-based)
			    );
			    sheet.addMergedRegion(cellRangeAddress);

			    // Write the output to a file
			    FileOutputStream fileOut = new FileOutputStream("workbook_0300.xlsx");
			    wb.write(fileOut);
			    fileOut.close();


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long timeEnd = System.currentTimeMillis();
		System.out.println("run time by " + (timeEnd - timeBegin) + "millis"+" file:[workbook_0300.xlsx]");
	}

}
