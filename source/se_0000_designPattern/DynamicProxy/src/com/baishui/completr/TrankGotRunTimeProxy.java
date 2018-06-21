package com.baishui.completr;  
import com.baishui.proxy.MoveAble;
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