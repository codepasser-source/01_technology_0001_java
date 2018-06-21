package com.baishui.Observer1;

/**
 * 
 * @author  小孩睡眠 五秒 唤醒
 *  版本一 ： Dad 类线程监控  Child 类  wakenUp属性 只要是  true 就执行  feed方法
 */

class Child implements Runnable{
	 
	private boolean wakenUp = false;
	void wakeUp(){
		wakenUp = true;
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
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }  
		this.wakeUp();		//当前睡眠 5秒后执行 wakeUp 方法
	} 
}

class Dad implements Runnable{
    Child c; 
	public Dad(Child c) { 
		this.c = c;
	} 
	@Override
	public void run() { 
		
		while(!c.isWakenUp()){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} 
		feed(c);
	}   
	
	private void feed(Child c){
		System.out.println("feed child");
	}
}
public class Test1 {
    
	public static void main(String[] args) {
		Child c= new Child();
		new Thread(c).start();
		new Thread(new Dad(c)).start();
	}
	
	
}
