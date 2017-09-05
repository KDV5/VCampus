package seu.edu.thread;

import java.io.IOException;
import seu.edu.server.dao.Library.*;
import java.io.ObjectOutput;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.ArrayList;

import seu.edu.common.message.BasicMessage;
import seu.edu.common.message.LibraryMessage;
import seu.edu.common.message.ListMessage;
import seu.edu.server.dao.Library.LibraryDAO;
import seu.edu.server.vo.BookBean;

public class LibraryThread extends Thread{
	private LibraryDAO ld=new LibraryDAO();
	private Socket socket=null;
	private BasicMessage lm=null;
	public LibraryThread(Socket s,LibraryMessage content){
		socket=s;
		lm=content;
	}

	public void run(){
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			if("ListAllBooks".equals(lm.getRequestType())){
				oos.writeObject(ld.ListAllBooks());
			}
				
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	

}
