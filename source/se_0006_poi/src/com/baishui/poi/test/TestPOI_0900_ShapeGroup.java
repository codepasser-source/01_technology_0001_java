package com.baishui.poi.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.ShapeTypes;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFChildAnchor;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFShapeGroup;
import org.apache.poi.xssf.usermodel.XSSFSimpleShape;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestPOI_0900_ShapeGroup {
	
	public static void main(String[] args) {
		
		long timeBegin = System.currentTimeMillis();
		try {
		 
			    Workbook wb = new XSSFWorkbook(); 
			    Sheet sheet = wb.createSheet("new sheet");
			    /**创建画图对象*/
			    XSSFDrawing xssfDrawing = (XSSFDrawing) sheet.createDrawingPatriarch(); 
		        XSSFClientAnchor xssfClientAnchor = new XSSFClientAnchor(0,0,900,200,2,2,2,2);
		        // Create a shape group.
		        XSSFShapeGroup xssfShapeGroup = xssfDrawing.createGroup(xssfClientAnchor);
   
			    // Create a couple of lines in the group.
			    XSSFSimpleShape shape1 = xssfShapeGroup.createSimpleShape(new XSSFChildAnchor(3,3,500,500));
			    shape1.setShapeType(ShapeTypes.RT_TRIANGLE);  

			 /*   XSSFSimpleShape shape2 = xssfShapeGroup.createSimpleShape(new XSSFChildAnchor(1,200,400,600));
			    shape2.setShapeType(ShapeTypes.LINE);
			                    */
			    
			    FileOutputStream fileOut = new FileOutputStream("workbook_0900.xlsx");  
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
		System.out.println("run time by " + (timeEnd - timeBegin) + "millis"+" file:[workbook_0900.xlsx]");
		
	}
}
