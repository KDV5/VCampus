package cn.seu.edu.feedbackThread;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.FeedbackData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.message.ShopData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;


/*
 * FeedbackResponseThread
 * Ã·ΩªÕ∂Àﬂ
 *
 * @author Ge Danwei
 *
 * @Date 2016/9/4
 *
 */

public class FeedbackResponseThread{
	private ServerReaderThread server ;
	private FeedbackData fData;
	private String userID;
	private String fType;
	private String fProblem;
	private String fPhone;
	private String fTime;
	
	public FeedbackResponseThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.fData = (FeedbackData)reqData;
		this.userID = fData.getId();
		this.fType = fData.getfType();
		this.fProblem = fData.getfProblem();
		this.fPhone = fData.getfPhone();
		this.fTime = fData.getfTime();
	}

	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			String sql = "insert into tblFeedback(uID,fType,fProblem,fPhone,fTime)values(?,?,?,?,?)";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,userID);
			conAccess.pstmt.setString(2,fType);
			conAccess.pstmt.setString(3,fProblem);
			conAccess.pstmt.setString(4,fPhone);
			conAccess.pstmt.setString(5,fTime);		
			conAccess.pstmt.executeUpdate();
			server.sendDataToClient(new RequestData("true"));
		} catch (SQLException e) {
			e.printStackTrace();
			server.sendDataToClient(new RequestData("false"));
		}
		
	}
}
