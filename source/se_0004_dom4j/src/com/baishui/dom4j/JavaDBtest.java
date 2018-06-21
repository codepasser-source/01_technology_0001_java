package com.baishui.dom4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * javaDB 就是  Java SE 6 之后内嵌了一个数据库   derby
*/
public class JavaDBtest {

	public static void main(String[] args) {
		
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
		    
			System.out.println("load the mebedded driver");
			
			Connection conn = null;
			
			Properties props= new Properties();
			props.put("user", "user1");
			props.put("password", "user1");
			
			//create and connect the database named hellDB
		 
			conn = DriverManager.getConnection("jdbc:derby:helloDB;create=true",props);//创建数据库
			 
			System.out.println("create and connect to helloDB success");
			
			conn.setAutoCommit(false);//禁止自动提交
			
			//create a table and insert two records
			
			Statement s = conn.createStatement();
			s.execute("create table hellotable(name varchar(40),score int)");
			System.out.println("create tabel hellotable success");
		
			s.execute("insert into hellotable(name,score) values('Ruth Cao',86)");
			s.execute("insert into hellotable(name,score) values('Flora Shi',86)");
			
			//list the two records
			
			ResultSet rs = s.executeQuery("Select name,score from hellotable order by score");
			
			System.out.println("name\t\tscore");
			
			
			while(rs.next()){
			   StringBuilder builder = new StringBuilder();
			   builder.append(rs.getString(1))
			   .append("\t")
			   .append(rs.getInt(2));
			   System.out.println(builder.toString()); 
			}
			
			//s.execute("drop table hellotable");
			//System.out.println("Dropped table hellotable success...");
			
			rs.close();
			s.close();
			System.out.println("Closed resultSet and statement success...");
			conn.commit();//提交
			conn.close(); 
			System.out.println("connect commit and Closed connect success...");
			
			//DriverManager.getConnection("jdbc:derby:;shutdown=true");
			
			System.out.println("-------over---------");
			
		}catch (SQLException e) { 
			e.printStackTrace();
		} catch (InstantiationException e) { 
			e.printStackTrace();
		} catch (IllegalAccessException e) { 
			e.printStackTrace();
		} catch (ClassNotFoundException e) { 
			e.printStackTrace();
		}
		 
	}
	
}
