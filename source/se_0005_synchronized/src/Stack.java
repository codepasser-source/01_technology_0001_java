 


public class Stack {

	
	private int size = 50;
	private int []stack=new int[size];
	private int counter=0; 
	
 
	
	public Stack() { 
	}
	
	/**
	 * 生产方法
	 * @throws InterruptedException 
	 */
	public synchronized void  push(int element) throws InterruptedException{ 
		
		if(counter<size){  
			stack[counter] = element;
			System.out.println("生产  stack["+counter+"] :"+element);
			counter ++;
		    this.notify();
		}else {
			System.out.println("堆栈已满");
		    this.wait();
		}
		
	}
	/**
	 * 消耗方法
	 * @throws InterruptedException 
	 */
    public synchronized void  pull() throws InterruptedException{ 
    	
		if(counter>0){ 
			int element = stack[counter-1];
			System.out.println("消费  stack["+(counter-1)+"] :"+element);
			counter --;
		    this.notify();
		}else { 
		   System.out.println("堆栈已空");
		   this.wait();
		}
		
	}
    
    
    
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int[] getStack() {
		return stack;
	}
	public void setStack(int[] stack) {
		this.stack = stack;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	
    
}
