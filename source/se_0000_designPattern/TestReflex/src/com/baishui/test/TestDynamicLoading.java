package com.baishui.test;

public class TestDynamicLoading {
 
/**  VM加载类的顺序
[Loaded com.baishui.test.TestDynamicLoading from file:/E:/%e7%99%bd%e6%b0%b4%e2%80%94%e2%80%94baishui_C/%e6%88%91%e7%9a%84F%e7%9b%98/workspace/baishui_points/TestReflex/bin/]
[Loaded com.baishui.test.A from file:/E:/%e7%99%bd%e6%b0%b4%e2%80%94%e2%80%94baishui_C/%e6%88%91%e7%9a%84F%e7%9b%98/workspace/baishui_points/TestReflex/bin/]
**------------------------
[Loaded com.baishui.test.B from file:/E:/%e7%99%bd%e6%b0%b4%e2%80%94%e2%80%94baishui_C/%e6%88%91%e7%9a%84F%e7%9b%98/workspace/baishui_points/TestReflex/bin/]
[Loaded com.baishui.test.C from file:/E:/%e7%99%bd%e6%b0%b4%e2%80%94%e2%80%94baishui_C/%e6%88%91%e7%9a%84F%e7%9b%98/workspace/baishui_points/TestReflex/bin/]
cccccccccccccccccccccccc
[Loaded com.baishui.test.D from file:/E:/%e7%99%bd%e6%b0%b4%e2%80%94%e2%80%94baishui_C/%e6%88%91%e7%9a%84F%e7%9b%98/workspace/baishui_points/TestReflex/bin/]
DDDDDDDDDDDDDDDDDDDDDDDD
DDDDDDDDDDDDDDDDDDDDDDDD
*/
	
	
	public static void main(String[] args) {
		 new A();
		 System.out.println("**------------------------");
         new B();
         
         
         new C();
         new C();
         
         new D();
         new D();
         
	} 
} 

class A{  
}

class B{ 
}

class C{
	static {   
		System.out.println("CCCCCCCCCCCCCCCCCC");  //动态语句块 在加载后 执行一次
	}
}

class D{
	{
		System.out.println("DDDDDDDDDDDDDDDDDDDDDDDD"); //dynamic 语句快 每次new 新对象 都会执行
	}
}