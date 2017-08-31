package cn.seu.edu.shopThread;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.BankData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;

public class CheckCardPasswordResponseThread{

	ServerReaderThread server;
	BankData bData;
	String password;
	String id;
	
	public CheckCardPasswordResponseThread(ServerReaderThread server,RequestData reqData){
		this.server = server;
		this.bData = (BankData)reqData;
		this.password = bData.getbPassword();
		this.id = bData.getId();
	}
	
	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		String pass = null;
		
		try {
			String sql = "select *from tblBank where uID = ?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1, id);
			ResultSet rs = conAccess.pstmt.executeQuery();
			if(rs.next()){
				pass = rs.getString("cardPassword");
			}
			if(password.equals(pass)){
				server.sendDataToClient(new RequestData("true"));
			}else{
				server.sendDataToClient(new RequestData("false"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
			server.sendDataToClient(new RequestData("false"));
		}
	}
}
