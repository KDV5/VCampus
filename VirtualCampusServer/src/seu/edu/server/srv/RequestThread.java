package seu.edu.server.srv;

import java.io.*;
import java.net.Socket;
import seu.edu.*;
import seu.edu.common.message.BasicMessage;
import seu.edu.common.message.LibraryMessage;
import seu.edu.sever.srv.thread.LibraryThread;


public class RequestThread extends Thread {
	private Socket socket=null;	
	ObjectInputStream ois=null;
	ObjectOutputStream oos=null;
	
	public RequestThread(Socket s){
		this.socket=s;		
		try {
			oos= new ObjectOutputStream(socket.getOutputStream());
			ois= new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			//e.printStackTrace();
			System.out.println("ois创建出错");
		}
	}
	public void run(){
		try{
			BasicMessage content =null;
			content =  (BasicMessage) ois.readObject();
			if("Library".equals(content.getModuleType())){				
				new LibraryThread(this,(LibraryMessage) content).start();
			}
			else{}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//向客户端发送信息
	public void SendToClient(BasicMessage bm){
		try {
			oos.writeObject(bm);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	

}
