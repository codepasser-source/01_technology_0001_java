package com.baishui.Observer3; 

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * 模拟 AWT 事件添加 的过程
 *
 */
public class ImitateAWT {
  public static void main(String[] args) {
	
	  Button bt = new Button();
	  bt.setName("mybt");
	  bt.addActionListener(new MyActionLesstener1());
	  bt.addActionListener(new MyActionLesstener2());
	  bt.buttonPressed();
	  
}
}   
class Button { 
	
	private String name ; //按钮的属性
	public String getName() {
		return name;
	} 
	public void setName(String name) {
		this.name = name;
	}

	private List<ActionListener>  actionListeners = new ArrayList<ActionListener>();
	
	public void addActionListener(ActionListener actionListener){ //当前对象 添加监听的方法
		this.actionListeners.add(actionListener);
	}
	
	public void buttonPressed() { 
		ActionEven av = new ActionEven(System.currentTimeMillis(),this.name);//点击时 添加 一个 事件对象 
		for(ActionListener al : actionListeners){
			al.actionPerformed(av); //执行每个  事件坚挺处理
		}
		
	} 
}

class ActionEven{
	
	long when;
	Object source;
	public ActionEven(long when,Object source) {
		 this.when = when;
		 this.source=source;
	}
	public long getWhen(){
	 return when;
	}
	
	public Object getSource(){
		return source;
	}
}
interface ActionListener{
	public void actionPerformed(ActionEven even);
}

class  MyActionLesstener1 implements ActionListener{

	public void actionPerformed(ActionEven even) {
	   System.out.println("pressed me 1");
	} 
}

class  MyActionLesstener2 implements ActionListener{

	public void actionPerformed(ActionEven even) {
	   System.out.println("pressed me 2");
	} 
}