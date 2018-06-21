
public class Customer implements Runnable {

	private Stack stack;
	
	public Customer() {
		 
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
		   try {
			stack.pull();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		  try {
			  Thread.sleep((int)(Math.random()*5000)); 
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	  }
	}

}
