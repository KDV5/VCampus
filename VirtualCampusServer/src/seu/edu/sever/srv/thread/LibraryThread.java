package seu.edu.sever.srv.thread;

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
import seu.edu.server.vo.BookBean;
import seu.edu.server.dao.Library.SearchBook;
import seu.edu.server.srv.RequestThread;


public class LibraryThread extends Thread{
	private LibraryDAO ld=new LibraryDAO();
	private SearchBook sb=new SearchBook();
	private RequestThread requestThread=null;
	private BasicMessage lm=null;
	public LibraryThread(RequestThread rt ,LibraryMessage content){
		requestThread=rt;
		lm=content;
	}

	public void run(){
		//oos = new ObjectOutputStream(socket.getOutputStream());
		if("ListAllBooks".equals(lm.getRequestType())){
			requestThread.SendToClient(sb.ListAllBooks());
		}
		
	}
	

}
