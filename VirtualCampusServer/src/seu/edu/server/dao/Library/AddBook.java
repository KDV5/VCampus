package seu.edu.server.dao.Library;

import seu.edu.common.message.LibraryMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import seu.edu.common.DBHelper;

public class AddBook {
	
	//图书入库
	public LibraryMessage addBook(LibraryMessage libMessage){
		
		DBHelper db=new DBHelper();
		PreparedStatement pst;
		db.getConnection();
		try{
			String   sql="select *from  tblBooks where BookID=? and BookName=? and Author=?";
		    pst = db.conn.prepareStatement(sql);
		    pst.setString(1, libMessage.getBookID());
		    pst.setString(2, libMessage.getBookName());
		    pst.setString(3, libMessage.getAuther());
		    ResultSet rs= pst.executeQuery();
		    
		    if(rs.next()){
		    	//若入库图书已存在
		    	sql = "update tblBooks set Storage=? , TotalNumber=?  where BookID=?";
			    pst = db.conn.prepareStatement(sql);
			    //System.out.print(storage);
			    int t=rs.getInt("Storage")+libMessage.getStorage();
			    int t2= rs.getInt("TotalNumber")+libMessage.getTotalNumber();
			    pst.setInt(1, t);  
			    pst.setInt(2,t2);  
			    //pst.setString(3, intro);  
			    pst.setString(3,libMessage.getBookID());
			    pst.executeUpdate();
			    return new LibraryMessage("ADD_BOOK_NUMBER_SUCCEED");
		    }else{
		    	//若入库图书未存在
			     sql="insert into tblBooks(BookID,BookName,Author,Place,Storage,TotalNumber,Introduction,Type,LendTimes) values(?,?,?,?,?,?,?,?,?)";
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
			     pst.executeUpdate();
			     return new LibraryMessage("ADD_NEW_BOOK_SUCCEED");
			     }	  
		}catch(SQLException e){
			e.printStackTrace();			
			return new LibraryMessage("ADD_BOOK_FAILED");
			
		}
	    }
		

	}

