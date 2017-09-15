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
	private DeleteBook db=new DeleteBook();
	private EditBook eb=new EditBook();
	private AddBook ab=new AddBook();
	private LendBook lb=new LendBook();
	private ReturnBook rb = new ReturnBook();
	private RequestThread requestThread=null;
	private LibraryMessage lm=null;
	public LibraryThread(RequestThread rt ,LibraryMessage content){
		requestThread=rt;
		lm=content;
	}

	public void run(){
		//oos = new ObjectOutputStream(socket.getOutputStream());
	//	while(true){
			
			if("SEARCH_BY_KEYWORDS".equals(lm.getRequestType())){
					requestThread.SendToClient(sb.SearchByKeyWords(lm));
				}
			else if("ADD_BOOK".equals(lm.getRequestType())){
					requestThread.SendToClient(ab.addBook(lm));
			}
			else if("DELETE_BOOK".equals(lm.getRequestType())){
				requestThread.SendToClient(db.deleteBook(lm));
			}
			else if("EDIT_BOOK".equals(lm.getRequestType())){
				requestThread.SendToClient(eb.editBook(lm));	
			}
			else if("ADD_HOT_WORDS".equals(lm.getRequestType())){
				sb.addHotWords(lm);	
			}
			else if("GET_TOP5_KEYWORDS".equals(lm.getRequestType())){
				requestThread.SendToClient(sb.TopFiveHotKeyword());
			}
			else if("GET_HOT_BOOKS".equals(lm.getRequestType())){
				requestThread.SendToClient(sb.GetHotBooks());
			}
			else if("GET_BORROWED".equals(lm.getRequestType())){
				requestThread.SendToClient(sb.GetBorrowedBooks(lm.getBookID()));// 此处bookID实际为学生ID
			}
			else if("LEND_BOOK".equals(lm.getRequestType())){
				requestThread.SendToClient(lb.lendBook(lm));
			}
			else if("RETURN_BOOK".equals(lm.getRequestType())){
				requestThread.SendToClient(rb.returnBook(lm));
			}
			
	//	}
		
	}
	

}
