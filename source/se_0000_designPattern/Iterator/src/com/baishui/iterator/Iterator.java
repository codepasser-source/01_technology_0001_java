package com.baishui.iterator;


/**
 * 
 * 定义 容器中元素的遍历接口
 */
public interface Iterator { 
	public Object next();  //得到下一项
	public Boolean hasNext(); //是否有下一项 
}
