package com.baishui.poi.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.ShapeTypes;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSimpleShape;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestPOI_0700_LineShape {
	
	public static void main(String[] args) {
		
		long timeBegin = System.currentTimeMillis();
		try {
			    

			/**画一个Shape Line**/
			    Workbook wb = new XSSFWorkbook(); 
			    Sheet sheet = wb.createSheet("new sheet");
			    /**创建画图对象*/
			    XSSFDrawing xssfDrawing = (XSSFDrawing) sheet.createDrawingPatriarch();
			    /**创建锚点*/
			    XSSFClientAnchor xssfLineClientAnchor = new XSSFClientAnchor(0, 0, 0, 0, (short) 4, 4, (short) 8, 8  );  
			    /**创建线形Shape*/ 
			    XSSFSimpleShape xssfLineShape = xssfDrawing.createSimpleShape(xssfLineClientAnchor); 
			    xssfLineShape.setShapeType(ShapeTypes.LINE);  //设置轮廓类型为 LINE
			    xssfLineShape.setFillColor(100, 100, 100);
			    xssfLineShape.setLineStyleColor(100, 100, 100);
		     /**查找Shape Line**/ 
			    xssfLineClientAnchor =  (XSSFClientAnchor) xssfLineShape.getAnchor();
			    System.out.println("startAnchor:("+xssfLineClientAnchor.getCol1()+","+xssfLineClientAnchor.getRow1()+")");
			    System.out.println("endAnchor:("+xssfLineClientAnchor.getCol2()+","+xssfLineClientAnchor.getRow2()+")");
			    
			    
			    FileOutputStream fileOut = new FileOutputStream("workbook_0700.xlsx");  
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
		System.out.println("run time by " + (timeEnd - timeBegin) + "millis"+" file:[workbook_0700.xlsx]");
		
	}
}
