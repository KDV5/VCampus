package cn.seu.edu.LibraryThread;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.LibraryData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;

public class InsertThread {
	private ServerReaderThread server ;
	private LibraryData lData;
	private String bookID;
	private String book;
	private String author;
	private String place;
	private String intro;
	private int storage;
	public InsertThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.lData = (LibraryData)reqData;
		this.bookID = lData.getbID();
		this.book=lData.getbName();
		this.author=lData.getbAuthor();
		this.place=lData.getbPlace();
		this.storage=lData.getbNumber();	
		this.intro=lData.getbIntro();}
public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		
 		try {
 			    String   sql="select *from  tblLibraryBooks where uBookID=? and uBook=? and uAuthor=?";
			    conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			    conAccess.pstmt .setString(1, bookID);
			    conAccess.pstmt .setString(2, book);
			    conAccess.pstmt .setString(3, author);
			    ResultSet rs= conAccess.pstmt.executeQuery();
			    if(rs.next()){
			    	sql = "update tblLibraryBooks set uStorage=? , uTotal=? ,uINtroduction=? where uBookID=?";
				    conAccess.pstmt = conAccess.conn.prepareStatement(sql);
				    System.out.print(storage);
				    conAccess.pstmt.setInt(1, rs.getInt("uStorage")+storage);  
				    conAccess.pstmt.setInt(2, rs.getInt("uTotal")+storage);  
				    conAccess.pstmt.setString(3, intro);  
				    conAccess.pstmt.setString(4,bookID);
				    conAccess.pstmt.executeUpdate();
			    }
			    else 
			    {
 			     sql="insert into tblLibraryBooks(uBookID,uBook,uAuthor,uPlace,uStorage,uTotal,uIntroduction) values(?,?,?,?,?,?,?)";
 			    conAccess.pstmt = conAccess.conn.prepareStatement(sql);
 			    conAccess.pstmt.setString(1,bookID);
 			    conAccess.pstmt.setString(2,book);
 			    conAccess.pstmt.setString(3,author);
 			    conAccess.pstmt.setString(4,place);
 			    conAccess.pstmt.setInt(5,storage);
 			   conAccess.pstmt.setInt(6,storage);
 			  conAccess.pstmt.setString(7,intro);
 			    conAccess.pstmt.executeUpdate();}
			    server.sendDataToClient(new RequestData("true"));		
   	} catch (SQLException e) {
 			e.printStackTrace();
 			server.sendDataToClient(new RequestData("false"));
 		}
}

}
