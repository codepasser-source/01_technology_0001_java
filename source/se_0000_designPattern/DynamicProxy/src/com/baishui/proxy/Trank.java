package com.baishui.proxy;

import java.util.Random;

public class Trank implements MoveAble{ 
	@Override
	public void move() { 
		System.out.println("moving ...."); 
		try {
			 Thread.sleep(new Random().nextInt(10000));//休眠   时间 是 十秒内 的随机数
			 System.out.println("moved ....");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
	} 
}
