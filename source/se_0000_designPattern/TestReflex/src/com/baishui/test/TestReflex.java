package com.baishui.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestReflex { 
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException { 
		String str = "com.baishui.test.T"; 
		Class c  = 	Class.forName(str);
		Object o = c.newInstance(); 
		Method[] m = c.getMethods();
		System.out.println("method________");
		//动态加载执行对象的方法
		for (int i = 0; i < m.length; i++) {
			System.out.println(m[i].getName());
			if(m[i].getName().equals("mm")){
				m[i].invoke(o);     //反射
			}
			if(m[i].getName().equals("m1")){
				m[i].invoke(o,1,2); //反射  传参数
				 T ot = (T)o;
				 System.out.println(ot.i);
			}
			//动态获取 类的 方法中 参数类型
			for(Class paramType : m[i].getParameterTypes()){
			  System.out.println("parameterTypes :　" + paramType);
			}			
		} 
	} 
}
   