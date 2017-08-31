package cn.seu.edu.LibraryThread;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.FeedbackData;
import cn.seu.edu.message.IdData;
import cn.seu.edu.message.LibraryData;
import cn.seu.edu.message.ObjListData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;

public class SearchThread  {
	private ServerReaderThread server ;
	private LibraryData libraryData;
	private String type=null;
	private String search=null;
	public SearchThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.libraryData = (LibraryData)reqData;
		this.type=libraryData.getType();
		this.search=libraryData.getSearch();
	}

	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			String sql=null;
			if(type.equals("uBook")){
				sql="SELECT *FROM tblLibraryBooks WHERE uBook LIKE ?  ORDER BY uLendNumber DESC";
				conAccess.pstmt = conAccess.conn.prepareStatement(sql);
				conAccess.pstmt.setString(1,"%"+search+"%");}
			else if(type.equals("uAuthor")){
				sql="SELECT *FROM tblLibraryBooks WHERE uAuthor LIKE ? ORDER BY uLendNumber DESC";
				conAccess.pstmt = conAccess.conn.prepareStatement(sql);
				conAccess.pstmt.setString(1,"%"+search+"%");
				
			}	
		    else if(type.equals("uBookID")){
		    	sql="SELECT *FROM tblLibraryBooks WHERE uBookID=?  ORDER BY uLendNumber DESC";
		    	conAccess.pstmt = conAccess.conn.prepareStatement(sql);
		    	System.out.print(search);
				conAccess.pstmt.setString(1,search);}
			ResultSet rs = conAccess.pstmt.executeQuery();
			ObjListData listData = new ObjListData("true");
			while(rs.next()){
				LibraryData lData1 = new LibraryData(" ");
				lData1.setbID(rs.getString("uBookID"));
				lData1.setbName(rs.getString("uBook"));
				lData1.setbAuthor(rs.getString("uAuthor"));
				lData1.setbPlace(rs.getString("uPlace"));
				lData1.setbStorage(rs.getInt("uStorage"));
				lData1.setbTotal(rs.getInt("uTotal"));
				lData1.setbLendNumber(rs.getInt("uLendNumber"));
				lData1.setbIntro(rs.getString("uIntroduction"));
				listData.getLibraryList().add(lData1);
			}
			server.sendDataToClient(listData);
		} catch (SQLException e) {
			e.printStackTrace();
			server.sendDataToClient(new ObjListData("false"));
		}
	}
}
