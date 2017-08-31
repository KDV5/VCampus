package cn.seu.edu.LibraryThread;

import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.FeedbackData;
import cn.seu.edu.message.IdData;
import cn.seu.edu.message.LibraryData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;

public class SearchAllThread {
	private ServerReaderThread server ;
//	private IdData idData;
//	private String userID;
	
	public SearchAllThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
//		this.idData = (IdData)reqData;
//		this.userID = idData.getId();
	}

	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			String sql = "select * from tblLibraryBooks order by uLendNumber";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			ResultSet rs = conAccess.pstmt.executeQuery();
			LibraryData lData = new LibraryData("searchAll");
			int c=0;
			while(rs.next()){
				lData.obj[c][0]=rs.getString(2);
				lData.obj[c][1]=rs.getString(3);
				lData.obj[c][2]=rs.getString(4);
				lData.obj[c][3]=rs.getString(5);
				lData.obj[c][4]=rs.getInt(6);
			}
			System.out.println(c);
			server.sendDataToClient(lData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
