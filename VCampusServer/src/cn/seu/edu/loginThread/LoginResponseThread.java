package cn.seu.edu.loginThread;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.LoginData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;


public class LoginResponseThread {

	private ServerReaderThread server;
	private LoginData loginData;
	private String uName;
	private String uPassword;
	
	public LoginResponseThread(ServerReaderThread server,RequestData reqData){
		this.server = server;
		this.loginData = (LoginData)reqData;
		this.uName = loginData.getUserName();
		this.uPassword = loginData.getUserPassword();
	}
	
	//登陆功能的实现
	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			String sql = "select * from tblUser where uName = ?";
//			System.out.println(uName);
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,uName);
			ResultSet rs = conAccess.pstmt.executeQuery();
		
			if(rs.next()){
//				System.out.println(rs.getString("uName"));
				if(uPassword.equals(rs.getString("uPassword")))
//					return true;
					server.sendDataToClient(new RequestData("true"));
				else{
//					return false;
					server.sendDataToClient(new RequestData("false"));
				}
			}
			else{
				server.sendDataToClient(new RequestData("unexist"));
//				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
//			return false;
		}
	}
}
