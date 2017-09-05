package seu.edu.server.dao.Library;

import java.sql.*;
import java.util.ArrayList;

import seu.edu.common.message.BasicMessage;
import seu.edu.common.message.LibraryMessage;
import seu.edu.common.message.ListMessage;
import seu.edu.server.dbHelper.*;
import seu.edu.server.vo.*;
/*
 * 图书馆表增删查改
 * 尹英伦 9.1 第一次 * 
 */
public class LibraryDAO {
	
	DBHelper dbh;
	
	/*
	 *
	 */
	boolean addBook(BookBean book){
		
		String sql="insert into tblBooks (BookID,BookNmae,Auther,Place,	TotalNumber,Storage,Introduction) "
				+ "values (" + book.getBookID() + book.getBookName() +book.getAuther() + book.getPlace() + book.getTotalNumber()
				+ book.getStorage() + book.getIntroduct() + ")";
		
		boolean re=dbh.executeUpdate(sql);
		return re;
		
	}
	
	boolean deleteBook(BookBean book){
		String sql = "delete from tblBooks where BookID = '"+book.getBookID()+"'";
		boolean re = dbh.executeUpdate(sql);
		return re;
	}
	
	public ResultSet searchBook(BookBean book){
		String sql = "select * from tblBooks where BookID= '"+book.getBookID()+"'";
		ResultSet re = dbh.executeQuery(sql);
		return re;
	}
	

	public ResultSet searchBook() {
		System.out.println("进入searchBook()");
		String sql = "select * from tblBooks";
		ResultSet re = dbh.executeQuery(sql);

		return re;
	}
	
	//列出所有书籍
	public ListMessage ListAllBooks(){
		System.out.println("进入ListAllBooks函数");
		ResultSet re=searchBook();
		try{
			System.out.println("请求内容 ：列出全部图书");			
			ArrayList<BasicMessage> bookList =new ArrayList<BasicMessage>();
			System.out.println("进入while循环");
			while(re.next()){				

				LibraryMessage b1=new LibraryMessage("ListAllBooks",re.getString("BookID"),re.getString("BookName"),re.getString("Author"),
						re.getString("Place"),re.getInt("TotalNumber"),re.getInt("Storage"),re.getString("Introduction"),re.getString("Publisher"));
				
				bookList.add(b1);				
			}
			
			return new ListMessage("Library","ListAllBooks",bookList);
			
		}catch(Exception e){
			return null;
		}			
	}
	
	//
	

}
