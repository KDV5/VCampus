package cn.seu.edu.mailThread;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.MailData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.ServerReaderThread;

/*
 * SendMailResponseThread
 * ·¢ÓÊ¼þ
 *
 * @author Ge Danwei
 *
 * @Date 2016/9/6
 *
 */
public class SendMailResponseThread {
	private ServerReaderThread server ;
	private MailData mData;
	private String recipientID;
	private String senderID;
	private String topic;
	private String content;
	private String time;
	private String recipientName = null;
	private String senderName = null;
	
	public SendMailResponseThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.mData = (MailData)reqData;
		this.recipientID = mData.getmRecipientID();
		this.senderID = mData.getmSenderID();
		this.topic = mData.getmTopic();
		this.content = mData.getmContent();
		this.time = mData.getmTime();
	}
	
	public void run(){
		ConnectAccess conAccess = new ConnectAccess();	
		try {
			String sql = "select * from tblStu where uStuNumber = ?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,recipientID);
			ResultSet rs = conAccess.pstmt.executeQuery();			
			if(rs.next()){
				recipientName = rs.getString("uName");	
			}
			else{
				sql =  "select * from tblTea where uId = ?";
				conAccess.pstmt = conAccess.conn.prepareStatement(sql);
				conAccess.pstmt.setString(1,recipientID);
				rs = conAccess.pstmt.executeQuery();
				if(rs.next()){
					recipientName = rs.getString("uName");
				}
			}
			if(recipientName!=null){
				sql =  "select * from tblStu where uStuNumber = ?";
				conAccess.pstmt = conAccess.conn.prepareStatement(sql);
				conAccess.pstmt.setString(1,senderID);
				rs = conAccess.pstmt.executeQuery();
				if(rs.next()){
					senderName = rs.getString("uName");
				}else{
					sql =  "select * from tblTea where uId = ?";
					conAccess.pstmt = conAccess.conn.prepareStatement(sql);
					conAccess.pstmt.setString(1,senderID);
					rs = conAccess.pstmt.executeQuery();
					if(rs.next()){
						senderName = rs.getString("uName");
					}
				}
				sql = "insert into tblMail(mSenderID,mSenderName,mRecipientID,mRecipientName,mTime,mTopic,mContent,mRead)values(?,?,?,?,?,?,?,?)";
				conAccess.pstmt = conAccess.conn.prepareStatement(sql);
				conAccess.pstmt.setString(1,senderID);
				conAccess.pstmt.setString(2,senderName);
				conAccess.pstmt.setString(3,recipientID);
				conAccess.pstmt.setString(4,recipientName);
				conAccess.pstmt.setString(5,time);
				conAccess.pstmt.setString(6,topic);
				conAccess.pstmt.setString(7,content);
				conAccess.pstmt.setBoolean(8,false);
				conAccess.pstmt.executeUpdate();
				server.sendDataToClient(new RequestData("true"));
			}else{
				server.sendDataToClient(new RequestData("false"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
