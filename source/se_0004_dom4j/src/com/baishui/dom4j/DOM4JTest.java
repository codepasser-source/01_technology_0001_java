package com.baishui.dom4j;
import java.io.File;
import java.util.Iterator; 
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader; 

public class DOM4JTest { 
	public static void main(String[] args) { 
		//dom4j API 以流的方式读xml文件
		SAXReader reader = new SAXReader();
		try {
			Document document=reader.read(new File("User.hbm.xml"));
		System.out.println("寻找节点方法一： 遍历");
		 /**   
		  * 寻找节点方法一： 遍历
		  */
		    //取得根节点
			Element rootElement = document.getRootElement();
		    System.out.println(rootElement.getName());   
		    for(Iterator i = rootElement.elementIterator();i.hasNext();){//elementIterator 获取子节点
		    	 Element element_1= (Element)i.next();
		    	 System.out.println(element_1.getName()); 
		    	 
		    	 //获得标签中的属性
		    	 for(Iterator j = element_1.attributeIterator();j.hasNext();){//attributeIterator 获取当前节点的属性
			    	 Attribute attribute_1_1= (Attribute)j.next();
			    	 System.out.println("--"+attribute_1_1.getName());
			     }  
		    	 
		    	 for(Iterator j = element_1.elementIterator();j.hasNext();){//elementIterator 获取当前节点的子节点
		    		 Element element_1_2= (Element)j.next();
			    	 System.out.println(element_1_2.getName()); 
			    	 for(Iterator t = element_1_2.attributeIterator();t.hasNext();){//attributeIterator 获取当前节点的属性
				    	 Attribute attribute_1_2_1= (Attribute)t.next();
				    	 System.out.println("--"+attribute_1_2_1.getName());
				     } 
		    	 }
		     } 
		    
		    /**   
			  * 寻找节点方法二：xpath
			  * 添加：jaxen-1.1-beta6.jar
			  */
		    System.out.println("寻找节点方法二：xpath");
		    List<Node> nodes = document.selectNodes("//hibernate-mapping/class/property");
		    
		    for (Node node : nodes) {
				System.out.println(node.getName()); 
				//属性值获取
				System.out.println("--name="+node.valueOf("@name"));
			}
		    
		} catch (DocumentException e) { 
			e.printStackTrace();
		}
		
	} 
}
