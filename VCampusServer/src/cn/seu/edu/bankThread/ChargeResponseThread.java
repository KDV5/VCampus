package cn.seu.edu.bankThread;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.BankData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.ServerReaderThread;

/*
 * ChargeResponseThread
 * 一卡通充值类
 *
 * @author Ge Danwei
 *
 * @Date 2016/9/3
 *
 */
public class ChargeResponseThread {

	private ServerReaderThread server ;
	private BankData bankData;
	private String id;
	private String account;
	private String cardPass;
	
	public ChargeResponseThread(ServerReaderThread server,RequestData reqData){
		this.server = server;
		this.bankData = (BankData)reqData;
		this.id = bankData.getId();
		this.account = bankData.getbBalance();
		this.cardPass = bankData.getCardPassword();
	}
	
	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			String sql = "select * from tblBank where uID = ?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,id);
			ResultSet rs = conAccess.pstmt.executeQuery();
			if(rs.next()){
				if(cardPass.equals(rs.getString("cardPassword"))){
					String bBalance = rs.getString("bBalance");
					String cardBalance = rs.getString("cardBalance");
					double trade = Double.parseDouble(account);
					double balance = Double.parseDouble(bBalance);
					double cBalance = Double.parseDouble(cardBalance);
					if(trade <= balance){
						sql = "update tblBank set bBalance = ?  where uID = ?";
						conAccess.pstmt = conAccess.conn.prepareStatement(sql);
						conAccess.pstmt.setString(1, Double.toString(balance-trade));				
						conAccess.pstmt.setString(2,id);					
						conAccess.pstmt.executeUpdate();
					
						sql = "update tblBank set cardBalance = ?  where uID = ?";
						conAccess.pstmt = conAccess.conn.prepareStatement(sql);
						conAccess.pstmt.setString(1, Double.toString(cBalance+trade));	
						conAccess.pstmt.setString(2,id);					
						conAccess.pstmt.executeUpdate();
					
						server.sendDataToClient(new RequestData("true"));
					}
					else{
						server.sendDataToClient(new RequestData("false"));
					}
				}
				else{
					server.sendDataToClient(new RequestData("wrong"));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			server.sendDataToClient(new RequestData("false"));
		}
	}
}
