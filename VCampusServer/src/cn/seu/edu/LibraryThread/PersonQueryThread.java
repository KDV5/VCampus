package cn.seu.edu.LibraryThread;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.FeedbackData;
import cn.seu.edu.message.LibraryData;
import cn.seu.edu.message.MailData;
import cn.seu.edu.message.ObjListData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;

public class PersonQueryThread  {
	private ServerReaderThread server ;
	public LibraryData lData;
	private String ID;
	private String bID;
	private String result;
	private String senderID;
	
	public PersonQueryThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.lData = (LibraryData)reqData;
		this.ID = this.lData.getID();
	}

	public void run(){
		
		ConnectAccess conAccess = new ConnectAccess();
		
		try {
			String sql = "select * from tblBookUsers where uID = ?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,ID);
			PreparedStatement stmt=null;
			ResultSet rs = conAccess.pstmt.executeQuery();
			ObjListData listData = new ObjListData("true");
			ResultSet rs1 =null;
			while(rs.next()){
			       bID=rs.getString("uBookID"); 
 			       String lendDate=rs.getString("uLendDate");
  			       String returnDate=rs.getString("uReturnDate");
//			       String  lendDate = null;  
 //			       String returnDate = null;    
				   sql="SELECT *FROM tblLibraryBooks WHERE uBookID = ?";
				   stmt = conAccess.conn.prepareStatement(sql);
				   stmt.setString(1,bID);
				   rs1=stmt.executeQuery(); 
				   if(rs1.next()){
				   LibraryData lData1 = new LibraryData("returnPersonBooks");
				   lData1.setbID(rs1.getString("uBookID"));
					lData1.setbName(rs1.getString("uBook"));
					lData1.setbAuthor(rs1.getString("uAuthor"));
					lData1.setbPlace(rs1.getString("uPlace"));
					lData1.setbStorage(rs1.getInt("uStorage"));
					lData1.setbLendDate(lendDate);
					lData1.setbReturnDate(returnDate);
					listData.getLibraryList().add(lData1);
				   }
			  }			   
			server.sendDataToClient(listData);
			}
             catch (SQLException e) {
			e.printStackTrace();
			server.sendDataToClient(new ObjListData("false"));
		}
	}

}
