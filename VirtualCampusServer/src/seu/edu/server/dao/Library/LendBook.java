package seu.edu.server.dao.Library;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import seu.edu.common.DBHelper;
import seu.edu.common.message.LibraryMessage;

public class LendBook {
	public LibraryMessage lendBook(LibraryMessage libMessage){
		DBHelper db=new DBHelper();
		PreparedStatement pst;
		db.getConnection();
		try{
			//向借书表中添加数据
			String  sql="insert into tblLendBooks (BookName,BookID,stuNumber,stuName,LendDate) values(?,?,?,?,?)";
		    pst = db.conn.prepareStatement(sql);
		    pst.setString(1, libMessage.getBookName());
		    pst.setString(2, libMessage.getBookID());
		    pst.setString(3, libMessage.getStuName());
		    pst.setString(4, libMessage.getStuNumber());
		    pst.setString(5, libMessage.getLendDate());
		    int result=pst.executeUpdate();
		    if(result>=0){
		    	//return new LibraryMessage("LEND_BOOK_SUCCEED");
		    }
		    else
		    	return new LibraryMessage("LEND_BOOK_FAILED");	 
		    
		    //修改书籍表中的库存量
		    sql= "update tblBooks set Storage = ? where BookID = ? ";
		    pst.setInt(1, libMessage.getStorage());
		    pst.setString(2, libMessage.getBookID());
		    result= pst.executeUpdate();
		    if(result>=0){
		    	return new LibraryMessage("LEND_BOOK_SUCCEED");
		    }
		    else
		    	return new LibraryMessage("LEND_BOOK_FAILED");	 
		    

		}catch(SQLException e){
			e.printStackTrace();			
			return new LibraryMessage("DELETE_BOOK_FAILED");
			
			}
	}

}
