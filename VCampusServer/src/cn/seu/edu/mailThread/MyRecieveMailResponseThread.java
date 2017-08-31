package cn.seu.edu.mailThread;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.MailData;
import cn.seu.edu.message.IdData;
import cn.seu.edu.message.ObjListData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;


/*
 * MyRecieveMailResponseThread
 * 返回我收到的邮件
 *
 * @author Ge Danwei
 *
 * @Date 2016/9/4
 *
 */
public class MyRecieveMailResponseThread {
	private ServerReaderThread server ;
	private IdData idData;
	private String userID;
	
	public MyRecieveMailResponseThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.idData = (IdData)reqData;
		this.userID = idData.getId();
	}

	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			String sql = "select * from tblMail where mRecipientID = ? order by mTime desc";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,userID);
			ResultSet rs = conAccess.pstmt.executeQuery();
			
			ObjListData listData = new ObjListData("true");
			while(rs.next()){
				MailData mData = new MailData("readMyRecieveMail");
				mData.setmSenderName(rs.getString("mSenderName"));
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
