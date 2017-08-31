package cn.seu.edu.bankThread;

import java.sql.ResultSet;

import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.BankData;
import cn.seu.edu.message.IdData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.ServerReaderThread;


/*
 * BalanceResponseThread
 * 查询余额类
 *
 * @author Ge Danwei
 *
 * @Date 2016/9/3
 *
 */

public class BalanceResponseThread{

	private ServerReaderThread server ;
	private IdData idData;
	private String id;      //用户ID
	
	public BalanceResponseThread(ServerReaderThread server,RequestData reqData){
		this.server = server;
		this.idData = (IdData)reqData;
		this.id = idData.getId();
	}
	
	public void run(){
		ConnectAccess conAccess = new ConnectAccess();	
		try {
			String sql = "select * from tblBank where uID = ?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,id);
			ResultSet rs = conAccess.pstmt.executeQuery();
			if(rs.next()){
				String bBalance = rs.getString("bBalance");
				String cardBalance = rs.getString("cardBalance");
				BankData bank = new BankData("");
				bank.setbBalance(bBalance);
				bank.setCardBalance(cardBalance);
				server.sendDataToClient(bank); //返回余额
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			server.sendDataToClient(new BankData("false"));
		}
		
	}
}
