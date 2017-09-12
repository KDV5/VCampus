package seu.edu.server.dao.Library;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import seu.edu.common.DBHelper;
import seu.edu.common.message.LibraryMessage;

/*
 * 在确定图书存在的情况下，从数据库中直接删除图书
 * @author yyl
 * 9.10
 */
public class DeleteBook {
	
	public LibraryMessage deleteBook(LibraryMessage lm){
	DBHelper db=new DBHelper();
	PreparedStatement pst;
	db.getConnection();
	try{
		String  sql="delete from tblBooks where BookID=?";
	    pst = db.conn.prepareStatement(sql);
	    pst.setString(1, lm.getBookID());
	    int result=pst.executeUpdate();
	    if(result>=0){
	    	return new LibraryMessage("DELETE_BOOK_SUCCEED");
	    }
	    else
	    	return new LibraryMessage("DELETE_BOOK_FAILED");	    

	}catch(SQLException e){
		e.printStackTrace();			
		return new LibraryMessage("DELETE_BOOK_FAILED");
		
		}
    }

}
