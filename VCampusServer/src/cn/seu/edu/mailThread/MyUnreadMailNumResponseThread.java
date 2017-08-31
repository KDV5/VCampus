package cn.seu.edu.mailThread;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.BankData;
import cn.seu.edu.message.IdData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;

/*
 * MyUnreadMailNumResponseThread
 * 返回我未读邮件的数量
 *
 * @author Ge Danwei
 *
 * @Date 2016/9/6
 *
 */

public class MyUnreadMailNumResponseThread {

	private ServerReaderThread server ;
	private IdData idData;
	private String id;
	
	public MyUnreadMailNumResponseThread(ServerReaderThread server,RequestData reqData){
		this.server = server;
		this.idData = (IdData)reqData;
		this.id = idData.getId();
	}
	
	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		int mailNum = 0;
		try {
			String sql = "select * from tblMail where mRecipientID = ?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,id);
			ResultSet rs = conAccess.pstmt.executeQuery();
			while(rs.next()){
				if(!rs.getBoolean("mRead")){
					mailNum++;
				}
			}
			server.sendDataToClient(new RequestData(Integer.toString(mailNum)));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
