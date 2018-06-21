import java.util.Random;


public class Producer implements Runnable {
     
	private Stack stack;
	private Random	r = new  Random(10); 
	
	public Producer() {  
	}

	
	public Stack getStack() {
		return stack;
	}


	public void setStack(Stack stack) {
		this.stack = stack;
	}

	@Override
	public void run() {
		 
		
		while (true) {
			int element = r.nextInt(10); 
			try {
				stack.push(element);
			} catch (InterruptedException e) { 
				e.printStackTrace();
			} 
			  try {
				  Thread.sleep((int)(Math.random()*500)); 
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		} 
	}

}
