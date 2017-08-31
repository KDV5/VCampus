package cn.seu.edu.feedbackThread;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.FeedbackData;
import cn.seu.edu.message.MailData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.ServerReaderThread;

/*
 * DealWithFeedbackResponseThread
 * 管理员处理投诉
 *
 * @author Ge Danwei
 *
 * @Date 2016/9/4
 *
 */

public class DealWithFeedbackResponseThread{
	private ServerReaderThread server ;
	private FeedbackData fData;
	private int fID;            //投诉事件的ID
	private String result;      //处理结果
	private String senderID;    //投诉者ID
	
	public DealWithFeedbackResponseThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.fData = (FeedbackData)reqData;
		this.fID = fData.getfID();
		this.result = fData.getfResult();
		this.senderID = fData.getId();
	}

	public void run(){
		
		ConnectAccess conAccess = new ConnectAccess();
		try {
			String sql = "select * from tblFeedback where ID = ?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setInt(1,fID);
			ResultSet rs = conAccess.pstmt.executeQuery();
			if(rs.next()){
				sql = "update tblFeedback set fResult = ?  where ID = ?";
				conAccess.pstmt = conAccess.conn.prepareStatement(sql);
				conAccess.pstmt.setString(1,result);
				conAccess.pstmt.setInt(2,fID);
				conAccess.pstmt.executeUpdate();
				
			
				//若投诉处理成功，给投诉者发送反馈邮件
				MailData mData = new MailData("sendMail");
				mData.setmSenderID(senderID);
				mData.setmTopic("投诉处理结果通知");
				mData.setmContent("您好，您在"+rs.getString("fTime")+"关于"+rs.getString("fType")+"的投诉已得到处理结果：\n"+result+"\n详情可在权益服务处查看");
				mData.setmRecipientID(rs.getString("uID"));
				Date date = new Date();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				mData.setmTime(format.format(date));
				server.sendDataToClient(mData);
			
			}else{
				server.sendDataToClient(new RequestData("false"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			server.sendDataToClient(new RequestData("false"));
		}
	}
}
