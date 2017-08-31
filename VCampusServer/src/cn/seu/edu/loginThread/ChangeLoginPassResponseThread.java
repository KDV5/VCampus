package cn.seu.edu.loginThread;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.LoginData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.ServerReaderThread;

public class ChangeLoginPassResponseThread {
	private ServerReaderThread server;
	private LoginData loginData;
	private String uName;
	private String uPassword;
	private String newPass;
	
	public ChangeLoginPassResponseThread(ServerReaderThread server,RequestData reqData){
		this.server = server;
		this.loginData = (LoginData)reqData;
		this.uName = loginData.getUserName();
		this.uPassword = loginData.getUserPassword();
		this.newPass = loginData.getNewPassword();
	}
	
	public void run(){
		ConnectAccess conAccess = new ConnectAccess();	
		try {
			String sql = "select * from tblUser where uName = ?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,uName);
			ResultSet rs = conAccess.pstmt.executeQuery();
			if(rs.next()){
				if(uPassword.equals(rs.getString("uPassword"))){
					sql = "update tblUser set uPassword = ?  where uName = ?";
					conAccess.pstmt = conAccess.conn.prepareStatement(sql);
					conAccess.pstmt.setString(1, newPass);
					conAccess.pstmt.setString(2,uName);					
					conAccess.pstmt.executeUpdate();				
					server.sendDataToClient(new RequestData("true"));
				}
				else{
					server.sendDataToClient(new RequestData("false"));
				}
			}else{
				server.sendDataToClient(new RequestData("false"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			server.sendDataToClient(new RequestData("false"));
		}
	}
}
