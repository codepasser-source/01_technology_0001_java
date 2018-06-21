package com.baishui.strategy;

/**
 * 设计模式之 Strategy  战略策略
 * 排序   
 * 
 * 需求
 * 1 方法支持重用  对所有类型 的数组进行排序
 * 2 利用多态   程序支持   随时变动 比较规则    实现程序想要的灵活性 
 */

public class StrategyMain {
  public static void main(String[] args) {
	//数组排序 
//	int []a={9,7,5,6,8,4,3,2,1}; 
//	DataSorter.sort(a); 
//	DataSorter.println(a);
	
	//对象 cat 排序 
	Cat cats[]={new Cat(5, 5),new Cat(3, 3),new Cat(1, 1)};
	DataSorter.sort(cats);
	DataSorter.println(cats);
	
  }
}
