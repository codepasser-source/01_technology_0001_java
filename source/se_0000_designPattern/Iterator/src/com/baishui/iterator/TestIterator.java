package com.baishui.iterator;

public class TestIterator {
  public static void main(String[] args) {
	 
	  /**
	   * 模拟ArrayList 的实现机制
	   */
	  Collection im = new ImArrayList();
	  for(int i = 0 ; i< 15 ;i++){
		  im.add(new Cat(i));
	  }
	  
	  Iterator it = im.iterator();
	  while(it.hasNext()){
		  Object o  = it.next();
		  System.out.println(o);
	  } 
   }
}
