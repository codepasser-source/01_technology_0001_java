package com.baishui.darw.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class TestDarwImage {

	public static void main(String[] args) {
		
		long timeBegin = System.currentTimeMillis(); 
		/**创建图片*/
		BufferedImage image = new BufferedImage(100, 200,BufferedImage.TYPE_INT_RGB); //设置大小
		
		Graphics graphics = image.getGraphics();
		graphics.setColor(Color.white); 
		for (int i = 0; i < 100 ; i++) {
			/**画line*/
			graphics.drawLine(i, i, 100-i, i);
			
		}
		
		
		
		
		
		
	/*	graphics.setColor(Color.black);
		*//**画font*//*
		graphics.drawString("HelloImage", 25, 25);//设置文字 位置（font,x,y）
*/		
		graphics.dispose();
		
		try {
			ImageIO.write(image, "jpeg", new File("helloImage.jpeg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long timeEnd = System.currentTimeMillis();
		System.out.println("run time by " + (timeEnd - timeBegin) + "millis"+" file:[helloImage.jpeg]");
	}
}
