package com.baishui.poi.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.ShapeTypes;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSimpleShape;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestPOI_0800_TriangleShape {
	
	public static void main(String[] args) {
		
		long timeBegin = System.currentTimeMillis();
		try {
		 
			    Workbook wb = new XSSFWorkbook(); 
			    Sheet sheet = wb.createSheet("new sheet");
			    /**创建画图对象*/
			    XSSFDrawing xssfDrawing = (XSSFDrawing) sheet.createDrawingPatriarch(); 
			    
			    
		        /**创建三角形shape*/
			    XSSFClientAnchor xssfTriangleClientAnchor = new XSSFClientAnchor(0, 0, 0, 0, 0, 0, 4, 4 );
			    XSSFSimpleShape xssfTriangleShape  = xssfDrawing.createSimpleShape(xssfTriangleClientAnchor);
			    
			    xssfTriangleShape.setShapeType(ShapeTypes.TRIANGLE);
			    xssfTriangleShape.setText(new XSSFRichTextString("轴名称")); //设置文字
			    xssfTriangleShape.setFillColor(100, 100, 100);//设置背景颜色
			    FileOutputStream fileOut = new FileOutputStream("workbook_0800.xlsx");  
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
		System.out.println("run time by " + (timeEnd - timeBegin) + "millis"+" file:[workbook_0800.xlsx]");
		
	}
}
