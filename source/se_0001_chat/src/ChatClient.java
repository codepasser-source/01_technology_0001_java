import java.awt.BorderLayout; 
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent; 
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
 
public class ChatClient extends JFrame {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */ 
	public static void main(String[] args) {
		ChatClient client = new ChatClient();
		client.setName("myClient");
	} 
	
    JTextArea chatContent= new JTextArea();
    JTextField message = new JTextField(); 
    
    Socket client = null; 
    boolean connected = false;
    
    DataInputStream dis=null;
    DataOutputStream dos=null;
    
    public ChatClient() {
		this.bulidFrame();
	}
    
    private void bulidFrame(){ 
    	
    	JLabel topLabel = new JLabel("top info");
		topLabel.setSize(540, 50);
		JPanel topPane = new JPanel();
		topPane.setSize(540, 50);
		topPane.add(topLabel,BorderLayout.CENTER);
		 
		JSplitPane vSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);//创建上下分割面板
		vSplitPane.setDividerLocation(290);           //设置上边的高度
		vSplitPane.setDividerSize(8);                 //设置分割线宽度 
		vSplitPane.setOneTouchExpandable(true);       //添加一个UI组件
		vSplitPane.setContinuousLayout(true);         //连续重绘
		
		vSplitPane.setLeftComponent(chatContent);  //在上下分割面板的上边添加 组件
		vSplitPane.setRightComponent(message); //在上下分割面板的下边添加 组件
		
		JSplitPane hSplitPane = new JSplitPane();    //创建左右分割面板
		hSplitPane.setDividerLocation(390);          //设置左边的宽度
		hSplitPane.setOneTouchExpandable(true);      //添加一个UI组件
		hSplitPane.setContinuousLayout(true);        //连续重绘
		hSplitPane.setDividerSize(15);  
		
		hSplitPane.setRightComponent(new JLabel("info"));  //在左右分割面板的右边添加 组件
		hSplitPane.setLeftComponent(vSplitPane);           //在左右分割面板的左边添加 组件
		
		this.getContentPane().add(topPane,BorderLayout.NORTH);
		this.getContentPane().add(hSplitPane,BorderLayout.CENTER);  
    	this.pack(); 
    	message.addActionListener(new messageListener());
    	
    	this.addWindowListener(new WindowAdapter() { 
			@Override
			public void windowClosing(WindowEvent arg0) { 
				disConnect();
				System.exit(0);
			} 
		});
    	
    	this.setSize(540,490);
    	this.setLocation(500, 50);
    	this.setVisible(true); 
    	this.connect();
    }
    
    private void connect(){
    	 try {
		    client = new Socket("127.0.0.1",1314); 
		    System.out.println("服务器已连接"); 
		   
		    connected=true;
			dis = new DataInputStream(client.getInputStream());
			dos = new DataOutputStream(client.getOutputStream());
			
			ServerListener serverListener = new ServerListener(); 
			new Thread(serverListener).start();
			
		} catch (UnknownHostException e) {
			System.out.println("无法连接服务器地址和指定端口");
			e.printStackTrace();
			
		} catch (ConnectException e) {
			System.out.println("服务器连接失败");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("socket ioException");
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
			    }catch(SocketException e1){
			    	System.out.println("服务器异常");
			    	chatContent.setText(chatContent.getText()+"无法连接服务器,请重启再次尝试连接服务器"+"\n");
			    	//延迟连接
			    	e1.printStackTrace();
			    } catch (IOException e1) { 
				   e1.printStackTrace();
			    }catch (NullPointerException e2) {
			    	chatContent.setText(chatContent.getText()+"无法连接服务器,请重启再次尝试连接服务器"+"\n");
					e2.printStackTrace();
				}
			    
			}  
		} 
    }
    
	private class ServerListener implements  Runnable{  
	 
		
		@Override
		public void run() {
			 try {
			    while(connected){ 
					String msg = dis.readUTF(); 
					chatContent.setText(chatContent.getText()+msg+"\n");
			    }
			 }catch(EOFException e) { 
				    System.out.println("服务器断开连接");
					e.printStackTrace();
			 } catch(IOException e) { 
				    System.out.println("server 或  client 退出"); 
			 }finally{
				 try {
					  if(dis!=null){ 
						   dis.close(); 
						   dis = null;
					  }
					  if(dos!=null){
						  dos.close();
						  dos=null;
					  }
					  if(client!=null){ 
						  client.close(); 
						  client = null;
					  }
					   try {
						this.finalize(); 
					   } catch (Throwable e) { 
						e.printStackTrace();
					   } 
					} catch (IOException e) { 
						e.printStackTrace();
				    } 
				 
			 } 
		} 
	}
}
