package seu.edu.server.srv;

import java.io.*;
import java.net.Socket;
import seu.edu.*;
import seu.edu.common.message.BasicMessage;
import seu.edu.common.message.LibraryMessage;
import seu.edu.thread.LibraryThread;


public class RequestThread extends Thread {
	private Socket socket=null;	
	ObjectInputStream ois=null;
	
	public RequestThread(Socket s){
		this.socket=s;		
		try {
			ois= new ObjectInputStream(s.getInputStream());
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
				
				new LibraryThread(socket,(LibraryMessage) content).start();
			}
			else{}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

}
