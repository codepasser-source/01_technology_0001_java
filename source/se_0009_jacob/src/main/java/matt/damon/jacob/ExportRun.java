package matt.damon.jacob;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class ExportRun {

	public static void main(String[] args) {
		//testJab.testFindReplace();
		//testJab.testAdd();
		//testJab.testAddOK();
		ExportDOC();
	}

	public static void ExportDOC() {
		//调用ActiveX 组件，获取Word Application
		ActiveXComponent doc = new ActiveXComponent("Word.Application");
		//设置文档可见属性
		doc.setProperty("Visible", new Variant(true));
		//获取documents Word对象
		Dispatch documents = doc.getProperty("Documents").toDispatch();
		//创建当前word document文档对象
		Dispatch document = Dispatch.call(documents, "Add", "").toDispatch();
		//获取word selection 选择器对象
		Dispatch selection = Dispatch.get(doc, "Selection").toDispatch();
		//获取word font字体对象
		Dispatch font = Dispatch.get(selection, "Font").toDispatch();
		//获取word alignment段落布局对象
		Dispatch alignment = Dispatch.get(selection, "ParagraphFormat")
				.toDispatch();

		//设置文字加粗 (0:常规,1:加粗)
		Dispatch.put(font, "Bold", "1");

		//下划线
		//Dispatch.put(font, "Underline", "0");
		//文字大小
		//Dispatch.put(font, "Size", "20");

		//设置文字字体 第三参数设置样式 ： (0:常规,1:斜体)
		Dispatch.put(font, "Italic", "1");
		Dispatch.call(selection, "TypeText", "English ....... ");
		//换行
		Dispatch.call(selection, "TypeParagraph");
		//设置文字加粗
		Dispatch.put(font, "Bold", "0");
		Dispatch.put(font, "Italic", "0");
		//输出汉字
		Dispatch.call(selection, "TypeText", "汉字 ....... ");

		Dispatch.call(selection, "TypeParagraph");
		//设置颜色
		Dispatch.put(font, "color", "1,0,0,0");
		Dispatch.call(selection, "TypeText", "颜色(红色)....... ");

		Dispatch.call(selection, "TypeParagraph");
		Dispatch.put(font, "color", "0,0,0,0");
		Dispatch.put(alignment, "Alignment", "0");
		Dispatch.call(selection, "TypeText", "对齐方式（左对齐）....... ");

		Dispatch.call(selection, "TypeParagraph");
		Dispatch.put(alignment, "Alignment", "1");
		Dispatch.call(selection, "TypeText", "对齐方式（居中对齐）....... ");

		Dispatch.call(selection, "TypeParagraph");
		Dispatch.put(alignment, "Alignment", "2");
		Dispatch.call(selection, "TypeText", "对齐方式（右对齐）....... ");

		Dispatch.call(selection, "TypeParagraph");
		Dispatch.put(alignment, "Alignment", "0");
		Dispatch image = Dispatch.get(selection, "InLineShapes").toDispatch();
		Dispatch.call(image, "AddPicture", "F:\\0.jpg");

		Dispatch.call(selection, "TypeParagraph");
		Dispatch.call(selection, "TypeText", "[");
		//插入间隔
		Dispatch.call(selection, "InsertBreak");
		Dispatch.call(selection, "TypeText", "]");
		Dispatch.call(selection, "TypeText", "插入间隔....... ");

		Dispatch.call(selection, "MoveDown"); // 游标往下一行 
		Dispatch.call(selection, "TypeText", "游标往下一行....... ");

		// 建立表格
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		//获取range范围对象
		Dispatch range = Dispatch.get(selection, "Range").toDispatch();

		int colNum = 3;
		int rowNum = 5;
		// 设置列数,栏数,表格外框宽度
		Dispatch newTable = Dispatch.call(tables, "Add", range,
				new Variant(rowNum), new Variant(colNum), new Variant(1))
				.toDispatch();
		Dispatch.call(selection, "MoveRight"); // 光标移到最右边

		//获取table对象，或者使用newTable
		Dispatch table = Dispatch.call(tables, "Item", new Variant(1)) //tableIndex
				.toDispatch();
		for (int row = 1; row <= rowNum; row++) {
			for (int col = 1; col <= colNum; col++) {
				Dispatch cell = Dispatch.call(table, "Cell", new Variant(row),
						new Variant(col)).toDispatch();
				Dispatch.put(cell, "Height", new Variant(2 * row)); //设置列高
				Dispatch.call(cell, "Select");
				Dispatch.put(selection, "Text", "cell" + row + "-" + col); // 写入word的内容
			}
		}

		// 主要内容
		// Dispatch.call(selection, "TypeParagraph"); //空一行段落
		// Dispatch.put(alignment, "Alignment", "3"); //(1:置中 2:靠右 3:靠左)

		Dispatch.call(document, "SaveAs", "F:\\test.docx");
		doc.invoke("Quit", new Variant[] {});
	}

	public static void testFindReplace() {
		try {
			String sInputDoc = "E:\\work\\testWord\\file_in.doc";
			String sOutputDoc = "E:\\work\\testWord\\file_out.doc";
			String sOldText = "[label:import:1]";
			String sNewText = "I am some horribly long sentence, so long that [insert bullshit here]";
			boolean tVisible = true;
			boolean tSaveOnExit = false;
			/////////////////////////////////////////////////////////////
			ActiveXComponent oWord = new ActiveXComponent("Word.Application");
			oWord.setProperty("Visible", new Variant(tVisible));
			Dispatch oDocuments = oWord.getProperty("Documents").toDispatch();
			Dispatch oDocument = Dispatch.call(oDocuments, "Open", sInputDoc)
					.toDispatch();
			Dispatch oSelection = oWord.getProperty("Selection").toDispatch();
			Dispatch oFind = oWord.call(oSelection, "Find").toDispatch();

			//////////////////////////////////////////////////////////////////////
			Dispatch.put(oFind, "Text", sOldText);
			Dispatch.call(oFind, "Execute");
			Dispatch.put(oSelection, "Text", sNewText);

			//////////////////////////////////////////////////////////////////////
			Dispatch.call(oSelection, "MoveDown");
			Dispatch.put(oSelection, "Text",
					"\nSo we got the next line including BR.\n");

			//////////////////////////////////////////////////////////////////////
			Dispatch oFont = Dispatch.get(oSelection, "Font").toDispatch();
			Dispatch.put(oFont, "Bold", "1");
			Dispatch.put(oFont, "Italic", "1");
			Dispatch.put(oFont, "Underline", "0");
			Dispatch.put(oFont, "Size", "20");
			Dispatch.put(oFont, "Color", "1,0,0,0");
			//Dispatch.put(oFont, "centre", "1");
			//Dispatch.put(oFont, "block", "1");
			//Object range=Dispatch.get(oSelection,"Range").toDispatch();
			// Dispatch.put(range, "Color", "1,0,0,0");

			//////////////////////////////////////////////////////////////////////
			Dispatch oAlign = Dispatch.get(oSelection, "ParagraphFormat")
					.toDispatch();
			Dispatch.put(oAlign, "Alignment", "1");
			// Dispatch.put(oAlign, "Color", "1");
			//////////////////////////////////////////////////////////////////////
			Object oWordBasic = Dispatch.call(oWord, "WordBasic").getDispatch();
			Dispatch.call(oWord, "SaveAs", sInputDoc);
			//Dispatch.call(oWordBasic, "FileSave", sInputDoc);
			Dispatch.call(oDocument, "Close", new Variant(tSaveOnExit));
			oWord.invoke("Quit", new Variant[0]);

			//////////////////////////////////////////////////////////////////////
			/* String sImgFile = "e:\\jsplife.png";
			 Dispatch.call(oSelection, "MoveDown");
			 Object oImage = Dispatch.get(oSelection, "InLineShapes").toDispatch();
			 Dispatch.call(oImage, "AddPicture", sImgFile);
			 Dispatch.call(oDocument, "Close", new Variant(tSaveOnExit));
			 //////////////////////////////////////////////////////////////////////*/
			//////////////////////////////////////////////////////////////////////

		} catch (Exception eEe) {
			eEe.printStackTrace();
		}
	}

}
