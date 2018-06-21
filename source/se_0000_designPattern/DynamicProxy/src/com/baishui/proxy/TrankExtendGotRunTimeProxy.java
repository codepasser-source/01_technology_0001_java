package com.baishui.proxy;
/** 
 * 代理方法一：  继承
 * 通过继承trank 对 move 方法动态切入
 */
public class TrankExtendGotRunTimeProxy extends Trank{

	@Override
	public void move() {
		long start = System.currentTimeMillis();
		super.move();
		long end = System.currentTimeMillis();
		
		System.out.println("time:" + (end - start)/1000);
	}
	
	

}
