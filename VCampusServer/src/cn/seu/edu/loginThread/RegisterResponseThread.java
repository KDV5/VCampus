package cn.seu.edu.loginThread;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.LoginData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;

public class RegisterResponseThread{
	private ServerReaderThread server;
	private LoginData loginData;
	private String uName;
	private String uPassword;
	
	public RegisterResponseThread(ServerReaderThread server,RequestData reqData){
		this.server = server;
		this.loginData = (LoginData)reqData;
		this.uName = loginData.getUserName();
		this.uPassword = loginData.getUserPassword();
	}
	
	//注册功能的实现
	public void run(){
		ConnectAccess conAccess = new ConnectAccess();	
		try {
			String sql = "select * from tblUser where uName = ?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,uName);
			ResultSet rs = conAccess.pstmt.executeQuery();
			
			if(rs.next()){
				server.sendDataToClient(new RequestData("exist"));
			}
			else{
				sql = "select * from tblStu where uStuNumber = ?";
				conAccess.pstmt = conAccess.conn.prepareStatement(sql);
				conAccess.pstmt.setString(1,uName);
				ResultSet rs1 = conAccess.pstmt.executeQuery();
				if(rs1.next()){
					
//					System.out.println(uName+uPassword+uRole);
					sql = "insert into tblUser(uName,uPassword)values(?,?)";
					conAccess.pstmt = conAccess.conn.prepareStatement(sql);
					conAccess.pstmt.setString(1,uName);
					conAccess.pstmt.setString(2,uPassword);
					conAccess.pstmt.executeUpdate();
					server.sendDataToClient(new RequestData("true"));
					
				}else{
					sql = "select * from tblTea where uId = ?";
					conAccess.pstmt = conAccess.conn.prepareStatement(sql);
					conAccess.pstmt.setString(1,uName);
					ResultSet rs2 = conAccess.pstmt.executeQuery();
					if(rs2.next()){
						
//						System.out.println(uName+uPassword+uRole);
						sql = "insert into tblUser(uName,uPassword)values(?,?)";
						conAccess.pstmt = conAccess.conn.prepareStatement(sql);
						conAccess.pstmt.setString(1,uName);
						conAccess.pstmt.setString(2,uPassword);
						conAccess.pstmt.executeUpdate();
						server.sendDataToClient(new RequestData("true"));
					}else{
						server.sendDataToClient(new RequestData("false"));
					}
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			server.sendDataToClient(new RequestData("false"));
		}
	}

}
