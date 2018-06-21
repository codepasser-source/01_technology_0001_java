package com.baishui.testng;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Test001 {

	@BeforeSuite
	public void testBeforSuite(){
		System.out.println("///////////////////////");
		System.out.println("//    @BeforeSuite   //");
		System.out.println("///////////////////////");
	}
	@AfterSuite
	public void testAfterSuite(){
		System.out.println("///////////////////////");
		System.out.println("//    @AfterSuite    //");
		System.out.println("///////////////////////");
	}
	
	@BeforeTest
	public void testBeforeTest(){
		System.out.println("///////////////////////");
		System.out.println("//    @BeforeTest    //");
		System.out.println("///////////////////////");
	}
	@AfterTest
	public void testAfterTest(){
		System.out.println("///////////////////////");
		System.out.println("//    @AfterTest     //");
		System.out.println("///////////////////////");
	}
	@BeforeMethod
	public void testBeforeMethod(){
		System.out.println("///////////////////////");
		System.out.println("//    @BeforeMethod  //");
		System.out.println("///////////////////////");
	}
	@AfterMethod
	public void testAfterMethod(){
		System.out.println("///////////////////////");
		System.out.println("//    @AfterMethod   //");
		System.out.println("///////////////////////");
	}
	
	@Test
	public void testExecute1(){
		System.out.println("*********testExecute1*******");
	}
	@Test
	public void testExecute2(){
		System.out.println("*********testExecute2*******");
	}
}
