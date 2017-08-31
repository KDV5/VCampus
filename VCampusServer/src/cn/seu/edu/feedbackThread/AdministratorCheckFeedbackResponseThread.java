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
 * AdministratorCheckFeedbackResponseThread
 * 管理员查看投诉
 *
 * @author Ge Danwei
 *
 * @Date 2016/9/4
 *
 */

public class AdministratorCheckFeedbackResponseThread {
	private ServerReaderThread server ;
	private IdData idData;
	private String userID;  //管理员ID
	private String uType;
	
	public AdministratorCheckFeedbackResponseThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.idData = (IdData)reqData;
		this.userID = idData.getId();
	}

	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			//判断管理员身份
			if(userID.startsWith("10")){
				uType = "信息管理";
			}else if(userID.startsWith("11")){
				uType = "教务处";
			}else if(userID.startsWith("12")){
				uType = "银行";
			}else if(userID.startsWith("13")){
				uType = "商店";
			}else if(userID.startsWith("14")){
				uType = "图书馆";
			}else if(userID.startsWith("15")){
				uType = "食堂";
			}else if(userID.startsWith("16")){
				uType = "宿舍";
			}else if(userID.startsWith("17")){
				uType = "公共自行车";
			}else if(userID.startsWith("18")){
				uType = "教学楼";
			}else if(userID.startsWith("19")){
				uType = "其他";
			}
			
			String sql = "select * from tblFeedback where fType = ?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,uType);
			ResultSet rs = conAccess.pstmt.executeQuery();
			
			ObjListData listData = new ObjListData("true");
	
			while(rs.next()){
				FeedbackData fData = new FeedbackData("checkFeedback");
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
				fData.setfID(rs.getInt("ID"));
				listData.getFeedbackList().add(fData);
				
			}
			server.sendDataToClient(listData);
			
		} catch (SQLException e) {
			e.printStackTrace();
			server.sendDataToClient(new ObjListData("false"));
		}
	}
}
