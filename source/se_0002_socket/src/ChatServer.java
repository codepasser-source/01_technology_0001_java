import java.io.DataInputStream;
 
import java.io.EOFException;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;


public class ChatServer { 
	
	public static void main(String[] args) {
		ChatServer chatServer = new ChatServer();
		chatServer.start();
	}
	
	
	 ServerSocket server  = null;
	 boolean started = false; 
	 DataInputStream dis =null;
	 
	 
	private void start(){ 
		try {
			server = new ServerSocket(1314);
			System.out.println("服务已启动");
			started = true; 
		} catch (BindException e) { 
			 System.out.println("BindException 端口号已被占用，请先关闭进程在启动......");
			 System.exit(0);
			 e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException 端口号已被占用，请先关闭进程在启动......");
			System.exit(0);
			e.printStackTrace();
		} 
		    Socket client=null;
		    ClientThread clientListener=null;
		       while(started){ 
			     try {  
				  client = server.accept();
				  System.out.println("监听到一个客户端已连接");
				  clientListener = new ClientThread(client); 
				  new Thread(clientListener).start(); 
			     } catch (IOException e) { 
				  e.printStackTrace();
			     }   
		      }  
	}
	
	
	private class ClientThread implements Runnable{

		Socket client;
		DataInputStream dis; 
		//DataOutputStream dos;
		boolean connected = false;
		
		public ClientThread(Socket client) {
			 this.client=client;
			 if(client!=null){
				 try {
					 this.dis = new DataInputStream(this.client.getInputStream());
					 //dos = new DataOutputStream(client.getOutputStream());
					 connected = true;
					 System.out.println("一个client监听线程已启动");
				 } catch (IOException e) { 
					e.printStackTrace();
				 } 
			 }  
		}  
		
		@Override
		public void run(){ 
			try {
			  while(connected){ 
					String msg = this.dis.readUTF();
					System.out.println(msg); 
			  }  
			}catch(EOFException e){
				 System.out.println("用户退出");
			 }catch (IOException e) { 
				e.printStackTrace();
			}finally{
				try {
				  if(dis!=null){ 
					   dis.close(); 
					   dis = null;
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
