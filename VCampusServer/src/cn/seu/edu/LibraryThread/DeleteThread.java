package cn.seu.edu.LibraryThread;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.LibraryData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;

public class DeleteThread {
	private ServerReaderThread server ;
	private LibraryData lData;
	private String bookID;
	private String book;
	private String author;
	private int number=0;
	private int flag=1;
	public DeleteThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.lData = (LibraryData)reqData;
		this.bookID = lData.getbID();
		this.book=lData.getbName();
		this.author=lData.getbAuthor();
		this.number=lData.getbNumber();
		this.flag=lData.getbFlag();
		}
public void run(){
		try{
			
		    	ConnectAccess conAccess = new ConnectAccess();
				String sql = "select * from tblLibraryBooks where ubookID = ? and uBook=? and uAuthor=?";
				conAccess.pstmt = conAccess.conn.prepareStatement(sql);
				conAccess.pstmt.setString(1,bookID);
				conAccess.pstmt.setString(2,book);
				conAccess.pstmt.setString(3,author);
			    ResultSet rs= conAccess.pstmt.executeQuery();
			    if(flag==1){
				if(rs.next()){
					int s=rs.getInt("uStorage")-number;
		    		int t =rs.getInt("uTotal")-number;		    		
					sql = "update tblLibraryBooks set uStorage=? , uTotal=? where uBookID=?";
					conAccess.pstmt = conAccess.conn.prepareStatement(sql);
				    conAccess.pstmt.setInt(1, s);  
				    conAccess.pstmt.setInt(2, t);  
				    conAccess.pstmt.setString(3,bookID);
				    conAccess.pstmt.executeUpdate();
				    server.sendDataToClient(new RequestData("true"));
				}
				else
					 server.sendDataToClient(new RequestData("no"));
	 		}
			    else if(flag==2){
			    	if(rs.next()){
			    		int s=rs.getInt("uStorage")-number;
			    		int t =rs.getInt("uTotal")-number;
						sql = "update tblLibraryBooks set uStorage=? , uTotal=? where uBookID=?";
						conAccess.pstmt = conAccess.conn.prepareStatement(sql);
					    conAccess.pstmt.setInt(1, s);  
					    conAccess.pstmt.setInt(2, t);  
					    conAccess.pstmt.setString(3,bookID);
					    conAccess.pstmt.executeUpdate();
					    server.sendDataToClient(new RequestData("true"));
					}
					else
						 server.sendDataToClient(new RequestData("no"));
			    	
			    }
			    	}catch (SQLException e) {
	 			e.printStackTrace();
	 			server.sendDataToClient(new RequestData("false"));
	 		}
}
}
		
	

