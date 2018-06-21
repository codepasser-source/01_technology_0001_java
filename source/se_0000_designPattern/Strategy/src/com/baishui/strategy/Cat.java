package com.baishui.strategy;

public class Cat implements Compareable{
  private int height;
  private int weight;
  
  private Compareor compareor = new CatHeightCompareor();  //指定默认的比较器
  
  
public Compareor getCompareor() {
	return compareor;
}
public void setCompareor(Compareor compareor) {
	this.compareor = compareor;
}
public Cat(int height, int weight) {
	super();
	this.height = height;
	this.weight = weight;
}
public int getHeight() {
	return height;
}
public void setHeight(int height) {
	this.height = height;
}
public int getWeight() {
	return weight;
}
    public void setWeight(int weight) {
	   this.weight = weight;
    } 
 
	public String toString() { 
	   return  this.getHeight()+"--"+this.getWeight();
	}
	
	
	@Override
	public int compareTo(Object  o2) {
		  return this.compareor.compareTo(this, o2);  // 利用比较器 判断大小，增加程序的可复用性，便于修改 和扩展
	}

}
