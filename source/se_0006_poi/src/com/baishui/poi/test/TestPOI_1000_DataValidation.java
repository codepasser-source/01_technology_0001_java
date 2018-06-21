package com.baishui.poi.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation;
 

public class TestPOI_1000_DataValidation {

	
public static void main(String[] args) {
		
		long timeBegin = System.currentTimeMillis();
		try {
			Workbook workbook = new XSSFWorkbook();
			
			Sheet sheet = workbook.createSheet("Data Validation");

            XSSFDataValidationConstraint constraint = new XSSFDataValidationConstraint(new String[]{"11","22","33"});
              
            CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(0, 0, 0, 0);
              
			XSSFDataValidation dataValidation = new XSSFDataValidation(constraint,cellRangeAddressList,CTDataValidation.Factory.newInstance());
			
			dataValidation.setSuppressDropDownArrow(false);
			  
			sheet.addValidationData(dataValidation);

			//sheet.getDataValidationHelper().createValidation(constraint, cellRangeAddressList);

			FileOutputStream fileOut = new FileOutputStream("workbook_1000.xlsx");   
			workbook.write(fileOut);
	        fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long timeEnd = System.currentTimeMillis();
		System.out.println("run time by " + (timeEnd - timeBegin) + "millis"+" file:[workbook_1000.xlsx]");
		
	}
}
