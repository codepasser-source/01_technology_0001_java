import java.awt.BorderLayout;
import java.awt.Frame; 
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
 
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
 
public class ChatClient extends Frame {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */ 
	public static void main(String[] args) {
		ChatClient client = new ChatClient();
		client.setName("myClient");
	}
	
	
    TextArea chatContent= new TextArea();
    TextField message = new TextField();
    
    Socket client = null;
    
    boolean connected = false;
    
    //DataInputStream dis=null;
    DataOutputStream dos=null;
    
    public ChatClient() {
		this.bulidFrame();
	}
    
    private void bulidFrame(){ 
    	this.add(chatContent,BorderLayout.NORTH);
    	this.add(message,BorderLayout.SOUTH);
    	this.pack();
    	
    	message.addActionListener(new messageListener());
    	
    	this.addWindowListener(new WindowAdapter() { 
			@Override
			public void windowClosing(WindowEvent arg0) { 
				disConnect();
				System.exit(0);
			} 
		});
    	this.setSize(400,300);
    	this.setLocation(500, 50);
    	this.setVisible(true); 
    	this.connect();
    }
    
    private void connect(){
    	 try {
		    client = new Socket("127.0.0.1",1314); 
		    System.out.println("服务器已连接");
			
		    connected=true;
			 //dis = new DataInputStream(client.getInputStream());
			 dos = new DataOutputStream(client.getOutputStream());
		} catch (UnknownHostException e) {
			System.out.println("无法连接服务器地址和指定端口");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("服务器连接失败");
			e.printStackTrace();
		}
    }
    
    private void disConnect(){
    	try {
    		if(dos!=null){
    			dos.close();
    			dos=null;
    		}
    		if(client!=null){
    			client.close();
    			client=null;
    		}
    		System.out.println("退出...");
    		//System.out.println(dos);
    		//System.out.println(client);
			
		} catch (IOException e) { 
			e.printStackTrace();
		}    	
    }
    
    private class messageListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String msg = message.getText();
			if(msg!=""){
			   try {
				   dos.writeUTF(msg);
				   dos.flush();
				   message.setText("");
				   chatContent.setText(chatContent.getText()+msg+"\n");
			    }catch(SocketException e1){
			    	System.out.println("服务器异常");
			    	chatContent.setText(chatContent.getText()+"无法连接服务器,请重启再次尝试连接服务器"+"\n");
			    	//延迟连接
			    	e1.printStackTrace();
			    } catch (IOException e1) { 
				   e1.printStackTrace();
			    }
			    
			}  
		} 
    }
    
	
}
