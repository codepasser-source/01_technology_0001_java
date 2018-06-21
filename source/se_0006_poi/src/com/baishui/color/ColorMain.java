package com.baishui.color;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;

public class ColorMain {

	public static void main(String[] args) throws InvalidFormatException, IOException {
		/** 创建Excel文件 */
		FileInputStream fileIS = new FileInputStream("workbook_test.xlsx");
		/** 创建workBook */
		Workbook workBook = WorkbookFactory.create(fileIS);
		Sheet sheetItem = workBook.getSheetAt(0);
		Row eRow = sheetItem.getRow(0);
		Cell eCell = eRow.getCell(0);
		CellStyle eStyle = eCell.getCellStyle();
		Font eFont = workBook.getFontAt(eStyle.getFontIndex());
				// 字体颜色
		ColorInfo color = null;
		if (eFont instanceof XSSFFont) {
			XSSFFont f = (XSSFFont) eFont;
			color = excelColor2UOF(f.getXSSFColor());
		} else {
			color = excel97Color2UOF(workBook,eFont.getColor());
		}
		System.out.println(color.toRGB());
	}
	
	/**
	 * excel97中颜色转化为uof颜色
	 * 
	 * @param color
	 *            颜色序号
	 * @return 颜色或者null
	 */
	private static ColorInfo excel97Color2UOF(Workbook book, short color) {
		if (book instanceof HSSFWorkbook) {
			HSSFWorkbook hb = (HSSFWorkbook) book;
			HSSFColor hc = hb.getCustomPalette().getColor(color);
			ColorInfo ci = excelColor2UOF(hc);
			return ci;
		}
		return null;
	}

	/**
	 * excel(包含97和2007)中颜色转化为uof颜色
	 * 
	 * @param color
	 *            颜色序号
	 * @return 颜色或者null
	 */
	private static ColorInfo excelColor2UOF(Color color) {
		if (color == null) {
			return null;
		}
		ColorInfo ci = null;
		if (color instanceof XSSFColor) {// .xlsx
			XSSFColor xc = (XSSFColor) color;
			System.out.println(xc.getIndexed());
			byte[] b = xc.getRgb();
			if (b != null) {// 一定是argb
				ci = ColorInfo.fromARGB(b[0], b[1], b[2], b[3]);
			}
		} else if (color instanceof HSSFColor) {// .xls
			HSSFColor hc = (HSSFColor) color;
			short[] s = hc.getTriplet();// 一定是rgb
			if (s != null) {
				ci = ColorInfo.fromARGB(s[0], s[1], s[2]);
			}
		}
		return ci;
	}
}
