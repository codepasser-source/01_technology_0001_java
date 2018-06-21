package com.baishui.test;

public class TestJDKClassLoader {
   
	/**
    * bootstrap class loader    : 管理class(java 核心类被其加载的)
    * extesion class loader     : 扩展的
    * application class loader  : 自定义的 class loader
    * other class loader        : 
	*/
	public static void main(String[] args) {  
		/*   
         System.out.println(String.class.getClassLoader());//null 核心类没办法获取到 classloader
	     System.out.println(com.sun.crypto.provider.DESKeyFactory.class.getClassLoader().getClass().getName());
		 System.out.println(TestJDKClassLoader.class.getClassLoader().getClass().getName());	
		 system.out.println(ClassLoader.getSystemClassLoader());	
		*/ 
		// classloader 之间的关系  注： 不是继承
		ClassLoader c = TestJDKClassLoader.class.getClassLoader();
		while (c!=null){
			System.out.println(c.getClass().getName());
			c = c.getParent();
		}
		
	}
}
