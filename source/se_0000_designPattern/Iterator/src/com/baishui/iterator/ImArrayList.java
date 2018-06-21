package com.baishui.iterator;


/**
 * Iterator设计模式
 * 可动态添加的 容器   数组
 *
 */ 
 class ImArrayList implements Collection{

	Object []objects = new Object[15];//初始的 长度 为15  
	int index = 0;
	static int  INCREMENT  = 20;

	public void add(Object o){
		if(index==objects.length){//当 数组长度 已满，扩展数组
			Object[] newObjects = new Object[objects.length + INCREMENT];
			System.arraycopy(objects, 0, newObjects, 0, objects.length); 
			objects = newObjects;
		}
		objects[index] = o;
		index++;
	}
	
	public int  getSize(){
		return  this.objects.length;
	} 

	
	 public Iterator iterator(){ 
		 return new arrayListIterator();//利用多态  得到Iterator 实现的对象
	 }
	
	 
	 //创建一个内部类  实现 Iterator接口  实现  hasNext()  next() 方法 用于遍历
	 private class arrayListIterator implements Iterator{
          private int currentIndex = 0;
		@Override
		public Boolean hasNext() {
			if(currentIndex>=index){
				return false;
			}else{
				return true;
			} 
		}

		@Override
		public Object next() {
			Object o = objects[currentIndex];
			currentIndex++;
			return o;
		}
		 
	 }
	 
}
 
 