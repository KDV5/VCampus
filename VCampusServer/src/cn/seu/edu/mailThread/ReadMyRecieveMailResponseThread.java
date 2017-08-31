package cn.seu.edu.mailThread;

import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.MailData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;

/*
 * ReadMyRecieveMailResponseThread
 * 阅读我收到的邮件
 *
 * @author Ge Danwei
 *
 * @Date 2016/9/6
 *
 */
public class ReadMyRecieveMailResponseThread{
	private ServerReaderThread server ;
	private MailData mData;
	private int mID;
	
	public ReadMyRecieveMailResponseThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.mData = (MailData)reqData;
		this.mID = mData.getmID();
	}

	public void run(){
		
		ConnectAccess conAccess = new ConnectAccess();
		try {
			String sql = "update tblMail set mRead = ?  where ID = ?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setBoolean(1,true);
			conAccess.pstmt.setInt(2,mID);
			conAccess.pstmt.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
