package seu.edu.server.srv;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import seu.edu.common.message.BasicMessage;
import seu.edu.common.message.UserMessage;
import seu.edu.server.srv.thread.UserThread;


public class RequestThread extends Thread {
	private Socket socket=null;	
	ObjectInputStream ois=null;
	ObjectOutputStream oos=null;
	
	public RequestThread(Socket s){
		this.socket=s;		
		try {
			oos= new ObjectOutputStream(socket.getOutputStream());
			ois= new ObjectInputStream(s.getInputStream());
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			System.out.println("ois创建出错");
		}
	}
	public void run(){
		try{
			BasicMessage content =null;
			content =  (BasicMessage) ois.readObject();
			if("User".equals(content.getModuleType())){
				new UserThread(this,(UserMessage) content).start();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void SendToClient(BasicMessage bm){
		try {
			oos.writeObject(bm);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
