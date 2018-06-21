package com.baishui.strategy;

public class DataSorter {
     
	/**
	 * 数组排序的方法 
	 * 排序口诀: 
	 * 冒 （冒泡）择（选择） 路（插入）兮 
	 *（希尔）快（快速）归（归并）堆（堆排序） 
	 
	public static void sort(int[] a) {
		 for(int i= a.length-1 ; i >0 ;i--){
			 for(int j=0;j<i;j++){
				if(a[j]>a[j+1]){
					swap(a,j,j+1);//  判断当前项 和下一项的大小  如果大于下一项 调换位置 最终 把最大项放到最后
				} 
			 }
		 }
	} 
	private static void swap(int[] a, int x, int y) {
		 int temp = a[x];
		 a[x]=a[y];
		 a[y]=temp;
	}

	public static void println(int[] a) {
	   for(int i : a){
		   System.out.print(i+"  ");
	   }
	   System.out.println(" over");
	}
	
	
	
	public static void sort(Cat[] cats) {
		 for(int i= cats.length-1 ; i >0 ;i--){
			 for(int j=0;j<i;j++){
				if(cats[j].getHeight() >cats[j+1].getHeight()){
					swap(cats,j,j+1);//  判断当前项 和下一项的大小  如果大于下一项 调换位置 最终 把最大项放到最后
				} 
			 }
		 }
	}

	private static void swap(Cat[] cats, int x, int y) {
		 Cat temp = cats[x];
		 cats[x]=cats[y];
		 cats[y]=temp;
	}

	public static void println(Cat[] cats) {
	   for(Cat i : cats){
		   System.out.println(i);
	   }
	}
  */
	
	public static void sort(Object[] os) {  //实现Compareable 接口的类都可以利用  此方法 进行排序
		 for(int i= os.length-1 ; i >0 ;i--){
			 for(int j=0;j<i;j++){
				 Compareable o1 = (Compareable)os[j];
				 Compareable o2 = (Compareable)os[j+1];
				 if(o1.compareTo(o2)==1){
					 swap(os,j,j+1);
				 }
			 }
		 }
	} 
	private static void swap(Object[] os, int x, int y) {
		 Object temp = os[x];
		 os[x]=os[y];
		 os[y]=temp;
	} 
	public static void println(Cat[] cats) {
	   for(Cat i : cats){
		   System.out.println(i);
	   }
	}
	
}
