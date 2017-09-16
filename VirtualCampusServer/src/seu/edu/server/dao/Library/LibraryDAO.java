package seu.edu.server.dao.Library;

import java.sql.*;
import java.util.ArrayList;

import seu.edu.common.message.BasicMessage;
import seu.edu.common.message.LibraryMessage;
import seu.edu.common.message.ListMessage;
import seu.edu.common.DBHelper;
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
	

	
	//
	

}
