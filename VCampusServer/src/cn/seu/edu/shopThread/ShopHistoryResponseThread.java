package cn.seu.edu.shopThread;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.IdData;
import cn.seu.edu.message.ObjListData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.message.ShopData;
import cn.seu.edu.server.ServerReaderThread;

public class ShopHistoryResponseThread {
	private ServerReaderThread server ;
	private IdData idData;
	private String userID;
	
	public ShopHistoryResponseThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.idData = (IdData)reqData;
		this.userID = idData.getId();
	}

	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			String sql = "select * from tblHistory where uSeller = ? or uBuyer = ?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,userID);
			conAccess.pstmt.setString(2,userID);
			ResultSet rs = conAccess.pstmt.executeQuery();
			ObjListData listData = new ObjListData("");
			while(rs.next()){
				ShopData shopData = new ShopData("");
				shopData.setuGoodsID(rs.getString("ID"));
				shopData.setuGoodsName(rs.getString("uGoodsName"));
				shopData.setuPrice(rs.getString("uPrice"));
				shopData.setuTime(rs.getString("uTime"));
				
				if(userID.equals(rs.getString("uSeller"))){
					shopData.setuRole("Âô³ö");
				}else if(userID.equals(rs.getString("uBuyer")))
				{
					shopData.setuRole("ÂòÈë");
				}
				
				listData.getShopList().add(shopData);
			}
			
			server.sendDataToClient(listData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}
