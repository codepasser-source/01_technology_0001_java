package com.baishui.poi.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class TestPOI_0200_Iterator {

	public static void main(String[] args) {
		
		
       long timeBegin = System.currentTimeMillis();
		
		try {
			/** 1 jar包 导入 common poi3.7 ooxml */
			
			/** 创建Excel文件 */
			FileInputStream fileIS = new FileInputStream("workbook_test.xlsx");
			/** 创建workBook */
			Workbook workBook = WorkbookFactory.create(fileIS);
			/** 迭代所有row 和 cell */
			/*
			 * Sheet sheetItem = workBook.getSheetAt(0); for (Iterator<Row>
			 * rowIterator = sheetItem.rowIterator(); rowIterator.hasNext(); ) {
			 * Row rowItem = rowIterator.next(); for (Iterator<Cell>
			 * cellIterator = rowItem.cellIterator(); cellIterator.hasNext(); )
			 * { Cell cellItem = cellIterator.next();
			 * System.out.println("rowNum:["
			 * +rowItem.getRowNum()+"] cellColumnIndex:["
			 * +cellItem.getColumnIndex()+"] "+
			 * "cellType:["+cellItem.getCellType()+"]"); } }
			 */
			/** java 1.5 迭代 */
			Sheet sheetItem = workBook.getSheetAt(0);
			for (Row rowItem : sheetItem) {
				for (Cell cellItem : rowItem) {
					System.out.println("rowNum:[" + rowItem.getRowNum()
							+ "] cellColumnIndex:[" + cellItem.getColumnIndex()
							+ "] " + "cellType:[" + cellItem.getCellType()
							+ "]"+cellItem.getCellComment().getString().toString());

					CellReference cellRef = new CellReference(rowItem.getRowNum(), cellItem.getColumnIndex());
					System.out.println(cellRef.formatAsString());
					System.out.println(" - ");

					switch (cellItem.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						System.out.println(cellItem.getRichStringCellValue().getString());
						break;

					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cellItem)) {
							System.out.println(cellItem.getDateCellValue());
						} else {
							System.out.println(cellItem.getNumericCellValue());
						}
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println(cellItem.getBooleanCellValue());
						break;

					case Cell.CELL_TYPE_FORMULA:
						System.out.println(cellItem.getCellFormula());
						break;

					default:
						System.out.println();
					}

				}
			}

			 
			fileIS.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long timeEnd = System.currentTimeMillis();
		System.out.println("run time by " + (timeEnd - timeBegin) + "millis");
	}
}
