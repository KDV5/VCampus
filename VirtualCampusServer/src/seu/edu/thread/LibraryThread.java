package seu.edu.thread;

import java.io.ObjectOutput;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.ArrayList;

import seu.edu.common.message.BasicMessage;
import seu.edu.common.message.LibraryMessage;
import seu.edu.common.message.ListMessage;
import seu.edu.server.dao.LibraryDAO;
import seu.edu.server.vo.Book;

public class LibraryThread extends Thread{
	private LibraryDAO ld=new LibraryDAO();
	private Socket socket=null;
	private BasicMessage lm=null;
	public LibraryThread(Socket s,BasicMessage content){
		socket=s;
		lm=content;
	}

	public void run(){
		ListAllBooks();
	}
	
	void ListAllBooks(){
		System.out.println("进入ListAllBooks函数");
		ResultSet re=ld.searchBook();
		try{
			System.out.println("请求内容 ：列出全部图书");
			ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
			ArrayList<BasicMessage> bookList =new ArrayList<BasicMessage>();
			System.out.println("进入while循环");

			while(re.next()){				

				LibraryMessage b1=new LibraryMessage("ListAllBooks",re.getString("BookID"),re.getString("BookName"),re.getString("Author"),
						re.getString("Place"),re.getInt("TotalNumber"),re.getInt("Storage"),re.getString("Introduction"),re.getString("Publisher"));
				
				bookList.add(b1);
				
			}
			//System.out.println("准备发送");
			oos.writeObject(new ListMessage("Library","ListAllBooks",bookList));
			//System.out.println("发送完成");
			
		}catch(Exception e){
			
		}
			
	}
}
