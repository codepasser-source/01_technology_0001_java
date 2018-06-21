package com.baishui.iterator; 

/**
 * 
 * 定义Collection接口   可延续性 可替换性   定义规范  
 *
 */ 
public interface Collection { 
	public int getSize();
	public void add(Object o);
	public Iterator iterator(); 
}
