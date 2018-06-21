package com.baishui.Observer3;

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AWT extends Frame{ 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void launch(){
		Button bt = new Button("press me"); 
		
		bt.addActionListener(new MyActionListener1());
		
		this.add(bt);
		this.pack();
		
		this.addWindowListener(new WindowAdapter(){  
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			} 
		});
		
		this.setVisible(true);
		
	} 
	
	public static void main(String[] args) {
		new AWT().launch();
	} 
	
	class MyActionListener1 implements ActionListener{ 
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("pressed me 1");
			
		} 
	}
	
	class MyActionListener2 implements ActionListener{ 
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("pressed me 2");
			
		} 
	}
	
}

 
