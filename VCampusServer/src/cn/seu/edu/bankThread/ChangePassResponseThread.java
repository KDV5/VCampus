package cn.seu.edu.bankThread;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.BankData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;

/*
 * ChangePassResponseThread
 * 修改一卡通/银行卡密码类
 *
 * @author Ge Danwei
 *
 * @Date 2016/9/3
 *
 */
public class ChangePassResponseThread{

	private ServerReaderThread server ;
	private BankData bankData;
	private String id;
	private String oldPass; 
	private String newPass;
	private String type;   //类型表示银行卡或一卡通
	
	public ChangePassResponseThread(ServerReaderThread server,RequestData reqData){
		this.server = server;
		this.bankData = (BankData)reqData;
		this.id = bankData.getId();
		this.oldPass = bankData.getCardPassword();
		this.newPass = bankData.getbPassword();
		this.type = bankData.getbName();
	}
	
	public void run(){
		ConnectAccess conAccess = new ConnectAccess();	
		try {
			String sql = "select * from tblBank where uID = ?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,id);
			ResultSet rs = conAccess.pstmt.executeQuery();
			if(rs.next()){
				if("bank".equals(type)){
					if(oldPass.equals(rs.getString("bPassword"))){
						sql = "update tblBank set bPassword = ?  where uID = ?";
						conAccess.pstmt = conAccess.conn.prepareStatement(sql);
						conAccess.pstmt.setString(1, newPass);
						conAccess.pstmt.setString(2,id);					
						conAccess.pstmt.executeUpdate();				
						server.sendDataToClient(new RequestData("true"));
					}
					else{
						server.sendDataToClient(new RequestData("false"));
					}
				}else if("card".equals(type)){
					if(oldPass.equals(rs.getString("cardPassword"))){
						sql = "update tblBank set cardPassword = ?  where uID = ?";
						conAccess.pstmt = conAccess.conn.prepareStatement(sql);
						conAccess.pstmt.setString(1, newPass);
						conAccess.pstmt.setString(2,id);					
						conAccess.pstmt.executeUpdate();				
						server.sendDataToClient(new RequestData("true"));
					}
					else{
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
