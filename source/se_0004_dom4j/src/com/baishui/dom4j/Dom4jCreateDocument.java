package com.baishui.dom4j;

import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class Dom4jCreateDocument {

	public static void main(String[] args) {
		
		
		Document document = DocumentHelper.createDocument();
		
		Element root= document.addElement("hibernate-mapping");
		   //链式编程
		  Element author1 = 
			  root.addElement("class")
			      .addAttribute("name", "com.baishui.User");
			      
	          author1.addElement("property")
		             .addAttribute("name", "username")
		             .addElement("property")
		             .addAttribute("name","password");
		  
		        
		try {
			
			OutputFormat outFormat =OutputFormat.createPrettyPrint();//输出格式
			XMLWriter xmlWriter = new XMLWriter(new FileWriter("User1.hbm.xml"),outFormat);  
			xmlWriter.write(document);//将标签写入文件
			xmlWriter.flush();//提交
			xmlWriter.close();//务必写入完成要关闭流 否则不会写入
			
		} catch (IOException e) { 
			e.printStackTrace();
		}
		
	}
}
