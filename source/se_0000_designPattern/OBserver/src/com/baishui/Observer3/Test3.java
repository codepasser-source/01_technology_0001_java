package com.baishui.Observer3; 

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author  
 *  版本三 ： 利用 OB server 设计模式
 *            根据Child 醒来的  时间  状态 来处理每种时间的方法
 *           添加监听者时  直接添加类 实现 WakenUpListener类 就可以 （更高的扩展性） 
 */ 
class WakenUpEven{	
	private long time;
	private String loc;
	private Child source; 
	
	public WakenUpEven(long time, String loc, Child source) {
		this.time = time;
		this.loc = loc;
		this.source = source;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public Child getSource() {
		return source;
	}
	public void setSource(Child source) {
		this.source = source;
	} 
}

interface WakenUpListener{
	public void actionToWakenUpEven(WakenUpEven wakenEven);
}

class Dad implements WakenUpListener{  
	public Dad(){
		System.out.println(this.toString());
	}
	public void actionToWakenUpEven(WakenUpEven wakenEven){
		System.out.println(this.toString());
		System.out.println("feed child");
	}
} 
class GrandFather implements WakenUpListener{   
	public GrandFather(){
		System.out.println(this.toString());
	}
	public void actionToWakenUpEven(WakenUpEven wakenEven){
		System.out.println(this.toString());
		System.out.println("hug child");
	}
}

class Child implements Runnable{ 
    private List<WakenUpListener> wakenUpListeners = new ArrayList<WakenUpListener>(); 
    public void addWakenUpListener(WakenUpListener wakenUpListener){
    	this.wakenUpListeners.add(wakenUpListener);
    } 
	void wakeUp(){  
		for (WakenUpListener wakenUpListener:wakenUpListeners) {
			wakenUpListener.actionToWakenUpEven(new WakenUpEven(System.currentTimeMillis(), "bed", this));
		}
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

class PropertiesMg{
	public static Properties properties = new Properties(); //
	static{ 
		try {
			properties.load(Test3.class.getClassLoader().getResourceAsStream("com/baishui/Observer3/observer.properties"));
			System.out.println("static 语句块 初始时 将对象放到内存中    单例模式");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	public static String getPropertiesValue(String proKey){
		return properties.getProperty(proKey);
	}
}

public class Test3 { 
	public static void main(String[] args) {

      
     
	String observers = PropertiesMg.getPropertiesValue("observers");//创建Properties 对象 读取配置文件
	System.out.println("observers all string : " + observers); 
  List<String> obs = new ArrayList<String>();
    int start = 0;
    int end = observers.indexOf(","); 
    
    String tempStr  = null;
    String tempStrEnd = null;    //截取字符串    observers.split(",");  直接返回一个数组
    
     while(end!=-1){
    	  tempStr = observers.substring(start,end+1);
          System.out.println("tempStr=" + tempStr); 
          
          obs.add(tempStr.replace(",", ""));
          
          observers=observers.substring(end+1);  
          end = observers.indexOf(","); 
          if(end == -1){
        	  tempStrEnd = observers;
        	  System.out.println("tempStrEnd="+tempStrEnd);
        	  obs.add(tempStrEnd);
          } 
     } 
    
	 Child c = new Child(); 
	   //添加监听者时 直接调用 方法 即可实现  增强了更高的扩展性 
	   //OB server 设计模式   
	 
	 for(String s : obs){
		  try {
			  c.addWakenUpListener((WakenUpListener)Class.forName(s).newInstance());//动态生成类对象 添加到 被监听的对象中去
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 new Thread(c).start();
	 
	} 
}
