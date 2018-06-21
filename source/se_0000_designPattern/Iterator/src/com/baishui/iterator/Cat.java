package com.baishui.iterator;

public class Cat{
	 private int id;
	 public Cat() { 
	 }
	 
	public Cat(int id) {
		 
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		 
		return super.toString()+"id="+this.id;
	}
	 
}


