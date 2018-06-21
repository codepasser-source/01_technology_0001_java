package com.baishui.dom4j;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
/**
 * 
 * @author baishui]
 * 利用 dom4j  和    javaDB  metaData 反相生成 xml 文件
 *
 */

public class Dom4j_javaDB_metaDate_CreateXML {

	public static void main(String[] args) {
		
		//获取表数据
		TableModel tableModel = getTableInfo();
		
		
        Document document = DocumentHelper.createDocument(); 
		Element root= document.addElement("hibernate-mapping");
		//链式编程
		Element author1 = 
			  root.addElement("table")
			      .addAttribute("tableName", tableModel.getTableName());
			      
	          author1.addElement("column")
		             .addAttribute("columnName", tableModel.getColumns().get(0))
		             .addAttribute("dataType", tableModel.getDataTypes().get(0));
	          
	          author1.addElement("column")
	             .addAttribute("columnName", tableModel.getColumns().get(1))
	             .addAttribute("dataType", tableModel.getDataTypes().get(1)); 
		  
		        
		try {
			
			OutputFormat outFormat =OutputFormat.createPrettyPrint();//输出格式
			XMLWriter xmlWriter = new XMLWriter(new FileWriter("Hello.hbm.xml"),outFormat);  
			xmlWriter.write(document);//将标签写入文件
			xmlWriter.flush();//提交
			xmlWriter.close();//务必写入完成要关闭流 否则不会写入
			
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}
	
	public static TableModel getTableInfo(){
		
		TableModel tableModel = new TableModel();
		
		try {
		    Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance(); 
			System.out.println("load the mebedded driver"); 
			Connection conn = null; 
			Properties props= new Properties();
			props.put("user", "user1");
			props.put("password", "user1"); 
			//create and connect the database named hellDB 
			conn = DriverManager.getConnection("jdbc:derby:helloDB;create=false",props);//false 不需要创建  
			DatabaseMetaData m = conn.getMetaData(); 
			//获取 表的metadata数据
			//获取数据库中的表名
			//catalog 所有表  schemaPattern 某个用户  tableNamepattern 表名的表达式 types
			//参数为 null时表示所有(注意 derby中默认为 大写)
			ResultSet rs = m.getTables(null, null,null,new String[]{"TABLE"});
			 while(rs.next()){ 
				 String tableName = rs.getString("TABLE_NAME");
				System.out.println("tableName:"+tableName);
				
				tableModel.setTableName(tableName);
			}
			 
			//获取数据库中的表中 列的数据
			 rs = m.getColumns(null, null, "HELLOTABLE", null); 
			 
			 while(rs.next()){
				 String columnName = rs.getString("COLUMN_NAME");
				 System.out.println("  columnName:"+columnName);//列名
				 int dataType = rs.getInt("DATA_TYPE");//数据类型
				 String type = "";
				 switch(dataType){
				 case Types.VARCHAR: type="varchar"; System.out.println("    dataType:varchar"); break;
				 case Types.INTEGER: type="int"; System.out.println("    dataType:int"); break;	 
				 }
				  tableModel.getColumns().add(columnName);
				  tableModel.getDataTypes().add(type);
			 }
			 
			rs.close();
			conn.close();
			System.out.println("close result and connect");
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return  tableModel;
			
	}
	
}
