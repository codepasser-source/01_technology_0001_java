package com.baishui.proxy;

public class TestMain {
  public static void main(String[] args) {
/*
   // 利用 继承  实现代理  
   Trank trank1 = new TrankExtendGotRunTimeProxy();  
   trank1.move(); 
   
   //代理  方法二： 利用 聚合 实现较好
   MoveAble trank2 = new TrankGotRunTimeProxy(new Trank()); 
   trank2.move();
   */
	  
	  
   /**
    * 当改变需求：聚合的方法    在不增加类的同时 可以 灵活改变先后顺序，如果用继承实现的话必须增加新的子类实现
    */ 
   Trank trank3 = new  Trank();
   MoveAble trank4 = new TrankGotRunTimeProxy(trank3); 
   MoveAble trank5 = new TrankForLogProxy(trank4); 
   trank5.move();
   
   MoveAble trank6 = new TrankForLogProxy(trank3); 
   MoveAble trank7 = new TrankGotRunTimeProxy(trank6); 
   trank7.move();
   
  }
}
