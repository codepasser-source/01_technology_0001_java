package com.baishui.poi.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestPOI_0500_Comment {

	
	public static void main(String[] args) {
		
		
		
		long timeBegin = System.currentTimeMillis();
		try {
			 Workbook wb = new XSSFWorkbook(); 
			 Sheet sheet = wb.createSheet("new sheet");
			    
			 int rowNum = 0;
			 int cellNum = 0;
			 
			 
			 /**添加注释*/
			 XSSFDrawing xssfDrawing = (XSSFDrawing) sheet.createDrawingPatriarch();
			 /**创建锚点*/
			 XSSFClientAnchor xssfCommentClientAnchor = new XSSFClientAnchor( 0, 0, 0, 0, 0, 0, 2, 2);
			  
			 
			 Row row = null;
			 Cell cell =null;
			 for(rowNum = 0 ; rowNum <5 ; rowNum++ ){
				 row = sheet.createRow(rowNum);
				 for (cellNum = 0; cellNum < 5; cellNum++) {
					  cell =  row.createCell(cellNum); 
					  XSSFComment comment = xssfDrawing.createCellComment(xssfCommentClientAnchor); 
					  comment.setString(new XSSFRichTextString("注释内容")); 
					  cell.setCellComment(comment);
				} 
			 }
			 
			 FileOutputStream fileOut = new FileOutputStream("workbook_0500.xlsx");  
		     wb.write(fileOut);
		     fileOut.close();
		     
		     /**读取注释*/
		     FileInputStream fileIS = new FileInputStream("workbook_0500.xlsx");
		     Workbook workBook =  WorkbookFactory.create(fileIS);
		     Sheet sheet0 = workBook.getSheetAt(0); 
		    
		     for (Row rowItem : sheet0) {
				for(Cell cellItem : rowItem){
					System.out.println("row:["+rowItem.getRowNum()+"] cell:["+cellItem.getColumnIndex()+"] cellComment:["+cellItem.getCellComment()+"] content:"+cellItem.getCellComment().getString());
				}
			 }
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
		System.out.println("run time by " + (timeEnd - timeBegin) + "millis"+" file:[workbook_0500.xlsx]");
		
	}
}
