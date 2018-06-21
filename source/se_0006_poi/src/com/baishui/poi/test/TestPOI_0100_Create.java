package com.baishui.poi.test;

import java.io.FileOutputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

 

public class TestPOI_0100_Create {
	
	
	public static void main(String[] args) { 
		
			long  timeBegin = System.currentTimeMillis();
		try {
		    /**1 jar包 导入  common  poi3.7  ooxml*/
			/**创建workBook*/
		    Workbook workBook = new XSSFWorkbook(); 
		    /**创建Excel文件*/
		    FileOutputStream fileOS = new FileOutputStream("workbook_0100.xlsx");
		    /**创建帮助器*/
		    CreationHelper createHelper = workBook.getCreationHelper();
		    /**创建Sheet*/
		    Sheet sheet = workBook.createSheet("new sheet");
		    
		    /**创建Row*/
		    Row row = sheet.createRow((short)0); //设定行号
		    row.setHeightInPoints((short)30);    //设置行高 
		    /**创建Cell*/
		    Cell cell = row.createCell(0);      
		    /**输入cell内容*/
		    row.createCell(0).setCellValue(1);//设定列号
		    row.createCell(1).setCellValue(new XSSFRichTextString("Align It"));
		    row.createCell(2).setCellValue(createHelper.createRichTextString("This is a string"));
		    row.createCell(3).setCellValue(true);
		    
		    
		    cell = row.createCell(4); 
		    
		    cell.setCellValue(new Date()); 
		   // cell.setCellType(Cell.CELL_TYPE_NUMERIC); //设置数据类型
		    /**使用CellStyle*/ 
		    CellStyle cellStyle = workBook.createCellStyle();
		    cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));//设置格式
		    
		    cellStyle.setAlignment(CellStyle.ALIGN_CENTER); //设置对齐方式
		    cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		    
		   
		    cellStyle.setBorderBottom(CellStyle.BORDER_THIN);  //设置边框
	        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
	        cellStyle.setLeftBorderColor(IndexedColors.GREEN.getIndex());
	        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
	        cellStyle.setRightBorderColor(IndexedColors.BLUE.getIndex());
	        cellStyle.setBorderTop(CellStyle.BORDER_MEDIUM_DASHED);
	        cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
	        
	        cellStyle.setFillBackgroundColor(IndexedColors.GREEN.getIndex()); //设置背景颜色
	        cellStyle.setFillPattern(CellStyle.BIG_SPOTS);
	        
	        /**使用字体*/
	        Font font = workBook.createFont();
	        font.setFontHeightInPoints((short)24); //设置字体
	        font.setFontName("Courier New");
	        font.setItalic(true);
	        font.setStrikeout(true);
	        font.setColor(IndexedColors.BLUE.getIndex());
	        cellStyle.setFont(font);
	        
	        cell.setCellStyle(cellStyle);		   
	        
	        /**创建hyperLink*/
	        cell = row.createCell(5);
	        CellStyle hlink_style = workBook.createCellStyle(); 
	        Font hlink_font = workBook.createFont();
	        hlink_font.setUnderline(Font.U_SINGLE);
	        hlink_font.setColor(IndexedColors.BLUE.getIndex());
	        hlink_style.setFont(hlink_font); 
	        cell.setCellValue("hyperLink");
	        Hyperlink link = createHelper.createHyperlink(Hyperlink.LINK_URL);//创建link
	        link.setAddress("http://poi.apache.org/"); //设置连接地址
	        cell.setHyperlink(link);
            cell.setCellStyle(hlink_style);
	        if(cell.getHyperlink()!=null){
	        	System.out.println("Cell have a Hyperlink");
	        }
            
	        sheet.getFooter().setRight("page # of " + 0);
	        sheet.setZoom(3,4);   // 设置缩放比例
	        sheet.setSelected(true);//设置选中

		    workBook.write(fileOS);
		    fileOS.flush();
		    fileOS.close();
		    
		} catch (Exception e) {
			e.printStackTrace();
		} 
		long  timeEnd = System.currentTimeMillis();
		System.out.println("run time by " + (timeEnd - timeBegin) + "millis"+" file:[workbook_0100.xlsx]");
		                    	 
	}
}
