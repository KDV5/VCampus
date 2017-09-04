package seu.edu.server.dao;

import java.sql.*;
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
	boolean addBook(Book book){
		
		String sql="insert into tblBooks (BookID,BookNmae,Auther,Place,	TotalNumber,Storage,Introduction) "
				+ "values (" + book.getBookID() + book.getBookName() +book.getAuther() + book.getPlace() + book.getTotalNumber()
				+ book.getStorage() + book.getIntroduct() + ")";
		
		boolean re=dbh.executeUpdate(sql);
		return re;
		
	}
	
	boolean deleteBook(Book book){
		String sql = "delete from tblBooks where BookID = '"+book.getBookID()+"'";
		boolean re = dbh.executeUpdate(sql);
		return re;
	}
	
	public ResultSet searchBook(Book book){
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
	
	

}
