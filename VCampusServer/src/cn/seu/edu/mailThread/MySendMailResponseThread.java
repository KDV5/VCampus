package cn.seu.edu.mailThread;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.IdData;
import cn.seu.edu.message.MailData;
import cn.seu.edu.message.ObjListData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;
/*
 * MySendMailResponseThread
 * 返回我发出的邮件
 *
 * @author Ge Danwei
 *
 * @Date 2016/9/4
 *
 */
public class MySendMailResponseThread {

	private ServerReaderThread server ;
	private IdData idData;
	private String userID;
	
	public MySendMailResponseThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.idData = (IdData)reqData;
		this.userID = idData.getId();
	}

	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			String sql = "select * from tblMail where mSenderID = ? order by mTime desc";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,userID);
			ResultSet rs = conAccess.pstmt.executeQuery();
			
			ObjListData listData = new ObjListData("true");
			while(rs.next()){
				MailData mData = new MailData("readMyRecieveMail");
				mData.setmRecipientName(rs.getString("mRecipientName"));
				mData.setmTopic(rs.getString("mTopic"));
				mData.setmTime(rs.getString("mTime"));	
				mData.setmRead(rs.getBoolean("mRead"));
				mData.setmContent(rs.getString("mContent"));
				mData.setmID(rs.getInt("ID"));
				listData.getMailList().add(mData);

			}
			server.sendDataToClient(listData);
		} catch (SQLException e) {
			e.printStackTrace();
			server.sendDataToClient(new ObjListData("false"));
		}
		
	}
}
