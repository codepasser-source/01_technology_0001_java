package com.baishui.poi.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestPOI_1200_Outlining {
	public static void main(String[] args) {
		long timeBegin = System.currentTimeMillis();
		try {
			
			Workbook wb = new XSSFWorkbook();
		    Sheet sheet1 = wb.createSheet("new sheet");

		    sheet1.groupRow( 5, 14 );
		    sheet1.groupRow( 7, 14 );
		    sheet1.groupRow( 16, 19 );

		    sheet1.groupColumn( (short)4, (short)7 );
		    sheet1.groupColumn( (short)9, (short)12 );
		    sheet1.groupColumn( (short)10, (short)11 );

		    FileOutputStream fileOut = new FileOutputStream("workbook_1200.xlsx");
		    
		    wb.write(fileOut);
		    fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long timeEnd = System.currentTimeMillis();
		System.out.println("run time by " + (timeEnd - timeBegin) + "millis"+" file:[workbook_1200.xlsx]");
		
	}
	
}
