 


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Customer customer = new Customer();
		Producer producer = new Producer();
		Stack stack = new Stack();
		
		customer.setStack(stack);
		producer.setStack(stack);
		
		new Thread(producer).start();
		new Thread(customer).start();
		
		 
	}

}
