package com.baishui.Observer2; 
/**
 * @author  小孩睡眠 五秒 唤醒
 *  版本二 ：Child 类 本身监控，五秒后执行 Dad 的方法 feed
 */ 
class Child implements Runnable{ 
	private Dad d; 
	public Child(Dad d) { 
		this.d = d;
		System.out.println(this.toString()+"--" + d.toString());
	}
	private boolean wakenUp = false;
	void wakeUp(){
		wakenUp = true;
		d.feed(this);	
	} 
	public boolean isWakenUp() {
		return wakenUp;
	}
	public void setWakenUp(boolean wakenUp) {
		this.wakenUp = wakenUp;
	}
	@Override
	public void run() { 
		 try {
				Thread.sleep(5000);
		 } catch (InterruptedException e) { 
				e.printStackTrace();
		 }  
		this.wakeUp();	//当前睡眠 5秒后执行 wakeUp 方法
	} 
}

class Dad{  
	public Dad(){
		System.out.println(this.toString());
	}
	public void feed(Child c){
		System.out.println(this.toString());
		System.out.println("feed child");
	}
}
public class Test2 { 
	public static void main(String[] args) {
	   Dad d1 = new Dad();
	   Child c1 = new Child(d1);
	   new Thread(c1).start(); 
	   Dad d2 = new Dad();
	   Child c2 = new Child(d2);
	   new Thread(c2).start();
	   
	} 
}
