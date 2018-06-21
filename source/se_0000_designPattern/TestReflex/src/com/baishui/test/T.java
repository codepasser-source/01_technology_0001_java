package com.baishui.test;

public class T {

	static {
		System.out.println("loadering T");
	}
	
	public T(){
		System.out.println("T loaded!");
	}
	
	public int i ;
	public String s;
	
	public void setI(int i){
		this.i = i;
		
	}
	public String getS(){
		return s;
	}
	
	public void mm(){
		System.out.println("mm invoked");
	}
	
	public void m1(int i,int j){
		this.i = i + j;
		System.out.println("m1 invoked");
	}
}
