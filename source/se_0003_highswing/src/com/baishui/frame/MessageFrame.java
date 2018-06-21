package com.baishui.frame;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class MessageFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public static void main(String[] args) {
		MessageFrame myFrame = new MessageFrame();
		myFrame.setName("myClient");
	}

	public MessageFrame() {
		bulidFrame();
	}
	
	private void bulidFrame(){  
		
		JLabel topLabel = new JLabel("top");
		topLabel.setSize(540, 50);
		JPanel topPane = new JPanel();
		topPane.setSize(540, 50);
		topPane.add(topLabel,BorderLayout.CENTER);
		 
		JSplitPane vSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);//创建上下分割面板
		vSplitPane.setDividerLocation(290);           //设置上边的高度
		vSplitPane.setDividerSize(8);                 //设置分割线宽度 
		vSplitPane.setOneTouchExpandable(true);       //添加一个UI组件
		vSplitPane.setContinuousLayout(true);         //连续重绘
		
		vSplitPane.setLeftComponent(new JLabel("content"));  //在上下分割面板的上边添加 组件
		vSplitPane.setRightComponent(new JLabel("message")); //在上下分割面板的下边添加 组件
		
		JSplitPane hSplitPane = new JSplitPane();    //创建左右分割面板
		hSplitPane.setDividerLocation(390);          //设置左边的宽度
		hSplitPane.setOneTouchExpandable(true);      //添加一个UI组件
		hSplitPane.setContinuousLayout(true);        //连续重绘
		
		hSplitPane.setRightComponent(new JLabel("info"));  //在左右分割面板的右边添加 组件
		hSplitPane.setLeftComponent(vSplitPane);           //在左右分割面板的左边添加 组件
		
		this.getContentPane().add(topPane,BorderLayout.NORTH);
		this.getContentPane().add(hSplitPane,BorderLayout.CENTER);
		this.setTitle("分割面板test"); 
		this.setBounds(500,50,540,490);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
