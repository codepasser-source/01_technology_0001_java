package com.baishui.color;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateTest {

	public static void main(String[] args) throws IOException {
		 /**1 jar包 导入  common  poi3.7  ooxml*/
		/**创建workBook*/
	    Workbook workBook = new XSSFWorkbook(); 
	    /**创建Excel文件*/
	    FileOutputStream fileOS = new FileOutputStream("workbook_test.xlsx");
	    /**创建帮助器*/
	    CreationHelper createHelper = workBook.getCreationHelper();
	    /**创建Sheet*/
	    Sheet sheet = workBook.createSheet("new sheet");
	    
	    /**创建Row*/
	    Row row = sheet.createRow((short)0); //设定行号
	    Cell cell = row.createCell(0);      
	    cell.setCellValue(1); 
	    CellStyle style = workBook.createCellStyle();
	    XSSFFont font = (XSSFFont) workBook.createFont();
	    XSSFColor color = new XSSFColor();
	    String rbg = "#FF";
	    byte[] b = {0,(byte) 255,(byte) 255,(byte) 255};
	    color.setRgb(b);
	    for (int i = 0; i < b.length; i++) {
			System.out.println(color.getRgb()[i]);
		}
	    font.setColor(color);
	    style.setFont(font);
	    cell.setCellStyle(style);
	    
	    workBook.write(fileOS);
	    fileOS.flush();
	    fileOS.close();
	    System.out.println("over");
	}
	
}
