package seu.edu.server.dao.Library;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import seu.edu.common.DBHelper;
import seu.edu.common.message.LibraryMessage;

/*
 * 图书借阅
 * 向LendBook表中添加数据、修改tblBook表中storage
 * 信息的合法性不做判断(在客户端完成)
 * @author yyl
 * 9.11
 */
public class EditBook {
	public LibraryMessage editBook(LibraryMessage libMessage){
		DBHelper db=new DBHelper();
		PreparedStatement pst;
		db.getConnection();
		try{
			String  sql="update tblBooks set BookID = ?, BookName = ?, Author = ?, Place = ? ,Storage = ? ,TotalNumber = ? ,Introduction = ?, Type = ? ,LendTimes = ? where BookID = ? ";
			//BookID,BookName,Author,Place,Storage,TotalNumber,Introduction,Type,LendTimes
		    pst = db.conn.prepareStatement(sql);
		     pst.setString(1,libMessage.getBookID());
		     pst.setString(2,libMessage.getBookName());
		     pst.setString(3,libMessage.getAuther());
		     pst.setString(4,libMessage.getPlace());
		     pst.setInt(5,libMessage.getStorage());
		     pst.setInt(6,libMessage.getTotalNumber());
		     pst.setString(7,libMessage.getIntroduct());
		     pst.setString(8,libMessage.getType());
		     pst.setInt(9,libMessage.getLendTimes());
		     pst.setString(10,libMessage.getBookID());
		     
		    int result=pst.executeUpdate();
		    if(result>=0){
		    	return new LibraryMessage("EDIT_BOOK_SUCCEED");
		    }
		    else
		    	return new LibraryMessage("EDIT_BOOK_FAILED");	    

		}catch(SQLException e){
			e.printStackTrace();			
			return new LibraryMessage("ACCESS_FAILED");
			
			}
	}
}
