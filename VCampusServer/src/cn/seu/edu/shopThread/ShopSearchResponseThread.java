package cn.seu.edu.shopThread;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.ObjListData;
import cn.seu.edu.message.ShopData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;

public class ShopSearchResponseThread{
	private ServerReaderThread server ;
	private ShopData shopData;
	private String goodsName;
	private String id;
	
	public ShopSearchResponseThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.shopData = (ShopData)reqData;
		this.goodsName = shopData.getuGoodsName();
		this.id = shopData.getuSellerID();
	}


	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			String sql = "select * from tblGoods where uGoodsName like ? and uSellerId like ? order by ID";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,"%"+goodsName+"%");
			conAccess.pstmt.setString(2,"%"+id+"%");
			ResultSet rs = conAccess.pstmt.executeQuery();
			ObjListData listData = new ObjListData("");
			while(rs.next()){
				ShopData shopData = new ShopData("");
				shopData.setuGoodsID(rs.getString("ID"));
				shopData.setuGoodsName(rs.getString("uGoodsName"));
				shopData.setuPrice(rs.getString("uPrice"));
				shopData.setuDetail(rs.getString("uDetail"));
				shopData.setuStorage(rs.getInt("uStorage"));
				String sellerId = rs.getString("uSellerId");
				shopData.setuSellerID(sellerId);
				if(sellerId.length() == 6){
					shopData.setuType("系统商店");
				}else{
					shopData.setuType("二手市场");
				}
				
				listData.getShopList().add(shopData);
				
			}
			server.sendDataToClient(listData);
			
		} catch (SQLException e) {
			e.printStackTrace();
//			server.sendDataToClient(new ShopData("false"));
		}
		
	}
}
