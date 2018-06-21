package com.baishui.frame;

import java.awt.BorderLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
 

public class MyFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	

	public static void main(String[] args) {
 		MyFrame myFrame= new MyFrame();
		myFrame.setName("");
	}
	
	public MyFrame() {
		bulidFrame();
	}
	
    private void bulidFrame(){ 
    	
    	JPanel topPanel = new JPanel();
    	topPanel.setSize(300,50);
    	JLabel topLabel = new JLabel("my info");
    	topLabel.setSize(300,50);
    	topPanel.add(topLabel,BorderLayout.CENTER);
    	
		 JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.SCROLL_TAB_LAYOUT);  
		 
		 JLabel tabLabel1  =  new JLabel("好友列表");
		 JLabel tabLabel2  =  new JLabel("好友群");
		 JLabel tabLabel3  =  new JLabel("最近联系人");
		 
		 URL resource = MyFrame.class.getResource("/cus.gif");
		 ImageIcon imageIcon = new ImageIcon(resource); 
		 
		 tabbedPane.addTab("我的好友",imageIcon,tabLabel1,"我的好友");
		 tabbedPane.addTab("好友群",imageIcon,tabLabel2,"好友群");
		 tabbedPane.addTab("最近联系人",imageIcon,tabLabel3,"最近联系人");
		 
		 tabbedPane.setSelectedIndex(0);
		 this.getContentPane().add(topPanel,BorderLayout.NORTH);
		 
		 this.getContentPane().add(tabbedPane,BorderLayout.CENTER); 
		 this.setTitle("选项卡test"); 
		
		this.setBounds(500,50,300,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
}
