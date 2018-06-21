package com.baishui.strategy;


/**
 * 
 * 实现对比规则接口  在 compareTo 方法中 编写比较规则，如果规则 随时可能发生变动时 修改此类的具体实现 
 */
public class CatHeightCompareor  implements Compareor{

	@Override
	public int compareTo(Object o1, Object o2) {
         
		if(o1 instanceof Cat && o2 instanceof Cat){
			Cat c1 = (Cat)o1;
			Cat c2 = (Cat)o2; 
			if(c1.getHeight()>c2.getHeight()){
				return 1;
			}else if(c1.getHeight()==c2.getHeight()){
				return 0;
			}else{
				return -1;
			}
		}
		return -100;
	} 
}
