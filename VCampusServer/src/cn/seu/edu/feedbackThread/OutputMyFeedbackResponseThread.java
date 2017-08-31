package cn.seu.edu.feedbackThread;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.FeedbackData;
import cn.seu.edu.message.IdData;
import cn.seu.edu.message.ObjListData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.ServerReaderThread;

/*
 * OutputMyFeedbackResponseThread
 * 输出我的投诉记录
 *
 * @author Ge Danwei
 *
 * @Date 2016/9/4
 *
 */

public class OutputMyFeedbackResponseThread {
	private ServerReaderThread server ;
	private IdData idData;
	private String userID;
	
	public OutputMyFeedbackResponseThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.idData = (IdData)reqData;
		this.userID = idData.getId();
	}

	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			String sql = "select * from tblFeedback where uID = ?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,userID);
			ResultSet rs = conAccess.pstmt.executeQuery();
			
			ObjListData listData = new ObjListData("true");
			while(rs.next()){
				FeedbackData fData = new FeedbackData("myFeedback");
				fData.setfType(rs.getString("fType"));
				fData.setfProblem(rs.getString("fProblem"));
				fData.setfPhone(rs.getString("fPhone"));
				fData.setfTime(rs.getString("fTime"));
				String result = rs.getString("fResult");
				if("".equals(result)|| null == result){
				    fData.setfResult("未处理");
				}else{
					fData.setfResult(result);
				}
				
				listData.getFeedbackList().add(fData);
				
			}
			server.sendDataToClient(listData);
		} catch (SQLException e) {
			e.printStackTrace();
			server.sendDataToClient(new ObjListData("false"));
		}
	}
}
