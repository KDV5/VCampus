package cn.seu.edu.shopThread;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.ObjListData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.message.ShopData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;

public class OutputNewGoodsResponseThread {
	private ServerReaderThread server ;
	private ShopData shopData;
	private String id;
	
	public OutputNewGoodsResponseThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.shopData = (ShopData)reqData;
		this.id = shopData.getuSellerID();
	}


	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			String sql = "select * from tblGoods where uSellerId<>? order by ID desc";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,id);
			ResultSet rs = conAccess.pstmt.executeQuery();
			ObjListData listData = new ObjListData("");
			int i = 0;
			while(rs.next() && i<20){
				ShopData shopData = new ShopData("");
				shopData.setuGoodsID(rs.getString("ID"));
				shopData.setuGoodsName(rs.getString("uGoodsName"));
				shopData.setuPrice(rs.getString("uPrice"));
				shopData.setuDetail(rs.getString("uDetail"));
				String sellerId = rs.getString("uSellerId");
				shopData.setuSellerID(sellerId);
				if(sellerId.length() == 6){
					shopData.setuType("系统商店");
				}else{
					shopData.setuType("二手市场");
				}
				i++;
				listData.getShopList().add(shopData);
				
			}
			server.sendDataToClient(listData);
			
		} catch (SQLException e) {
			e.printStackTrace();
			server.sendDataToClient(new ShopData("false"));
		}
	}
}
