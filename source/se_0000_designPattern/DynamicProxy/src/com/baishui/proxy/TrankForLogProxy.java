package com.baishui.proxy;

public class TrankForLogProxy implements MoveAble{

	private MoveAble trank ; 
	public TrankForLogProxy(MoveAble trank) { 
		this.trank = trank;
	} 
	@Override
	public void move() {
		System.out.println("log start....");   
		trank.move();
		System.out.println("log end....");  
		
	}
	
	

}
