package com.baishui.poi.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestPOI_1100_Picture {
	
public static void main(String[] args) {
		
		long timeBegin = System.currentTimeMillis();
		try {
		 
			//创建workbook
		    Workbook wb = new XSSFWorkbook(); //or new HSSFWorkbook(); 
		    //读取本地图片
		    InputStream is = new FileInputStream("image.jpg");
		    byte[] bytes = IOUtils.toByteArray(is);
		    //取得当前workbook下pirtureIndex
		    int pirtureIndex = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
		    is.close(); 
		    //CreationHelper helper = wb.getCreationHelper(); 
		    //创建Sheet
		    Sheet sheet = wb.createSheet(); 
		    Drawing drawing = sheet.createDrawingPatriarch();

		    //创建锚点
		    XSSFClientAnchor xssfClientAnchor = new XSSFClientAnchor(0, 0, 0, 0, 0, 0, 4, 4);		    
		    //画图
		    Picture picture = drawing.createPicture(xssfClientAnchor, pirtureIndex); 
		    //pict.resize(); //重置大小
		    FileOutputStream fileOut = new FileOutputStream("workbook_1100.xlsx");
		    wb.write(fileOut);
		    fileOut.close();
		    
		/**读取图片信息*/
		    FileInputStream fileIS = new FileInputStream("workbook_1100.xlsx");
		    wb = WorkbookFactory.create(fileIS);
		    
		    List picList =  wb.getAllPictures();
		    for (Iterator<PictureData> it = picList.iterator(); it.hasNext(); ) {
		        PictureData pictureData = (PictureData)it.next();
		        String ext = pictureData.suggestFileExtension();//获取图片扩展名
		        System.out.println("picture data extension:"+ext);
		    }


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
		System.out.println("run time by " + (timeEnd - timeBegin) + "millis"+" file:[workbook_1100.xlsx]");
		
	}
}
