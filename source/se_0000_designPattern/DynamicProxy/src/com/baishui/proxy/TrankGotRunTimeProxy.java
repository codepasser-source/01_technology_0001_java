package com.baishui.proxy;

/** 
  * 代理方法二：  聚合
  * 通过实现接口  注入trank 对象 对 move 方法动态切入
  */
public class TrankGotRunTimeProxy implements MoveAble {
    
	private MoveAble trank ; 
	public TrankGotRunTimeProxy(MoveAble trank) { 
		this.trank = trank;
	} 
	@Override
	public void move() {
		long start = System.currentTimeMillis();
		trank.move();
		long end = System.currentTimeMillis(); 
		System.out.println("time:" + (end - start)/1000);   
	}
    
	
	
}
