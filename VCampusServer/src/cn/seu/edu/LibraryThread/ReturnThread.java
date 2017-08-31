package cn.seu.edu.LibraryThread;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.LibraryData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;

public class ReturnThread {
	private ServerReaderThread server ;
	private LibraryData lData;
	private String userID;
	private String bookID;
	public ReturnThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.lData = (LibraryData)reqData;
		this.bookID = lData.getType();
		this.userID=lData.getSearch();
	}

	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		PreparedStatement stmt=null;
		LibraryData ldata=new LibraryData("");
		try {
//			System.out.print(this.userID);
			String sql = "update tblLibraryBooks set uStorage=(uStorage+1) where uBookID=?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,bookID);
			conAccess.pstmt.executeUpdate();
			sql="delete from tblBookUsers where uBookID = ?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,bookID);
			conAccess.pstmt.executeUpdate();
			 try {
		            Thread.sleep(500);
		        } catch (InterruptedException e1) {
		            // TODO Auto-generated catch block
		            e1.printStackTrace();
		        }
			
//				  lData.perObj[0][5]=lendDate;
//					  lData.perObj[0][6]=returnDate;}
		
				server.sendDataToClient(new RequestData("true"));}
	             catch (SQLException e) {
				e.printStackTrace();
				server.sendDataToClient(new RequestData("false"));
			}
	}

}
