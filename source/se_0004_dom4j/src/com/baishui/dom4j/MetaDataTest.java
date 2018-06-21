package com.baishui.dom4j;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

public class MetaDataTest {

	
	public static void main(String[] args) {
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
			System.out.println("tableName:"+rs.getString("TABLE_NAME"));
		}
		 
		//获取数据库中的表中 列的数据
		 rs = m.getColumns(null, null, "HELLOTABLE", null); 
		 while(rs.next()){
			 System.out.println("  columnName:"+rs.getString("COLUMN_NAME"));//列名
			 int dataType = rs.getInt("DATA_TYPE");//数据类型
			 switch(dataType){
			 case Types.VARCHAR: System.out.println("    dataType:varchar"); break;
			 case Types.INTEGER: System.out.println("    dataType:int"); break;	 
			 }
			  
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
	    
	}
	
}
