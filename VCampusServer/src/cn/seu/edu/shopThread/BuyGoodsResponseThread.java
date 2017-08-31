package cn.seu.edu.shopThread;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.MailData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.message.ShopData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;

public class BuyGoodsResponseThread {

	private ServerReaderThread server ;
	private ShopData shopData;
	private String goodsID;
	private String goodsName;
	private String uTime;
	private String userID;
	private String price=null;
	private int storage;
	private String uDetail;
	private String uSeller;
	private String uDorm ;
	private String uTel;
	
	public BuyGoodsResponseThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.shopData = (ShopData)reqData;
		this.goodsID = shopData.getuGoodsID();
		this.userID = shopData.getUserID();
		this.goodsName = shopData.getuGoodsName();
		this.uTime = shopData.getuTime();
		this.uDetail = shopData.getuDetail();
	}


	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			String sql = "select * from tblGoods where ID = ?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,goodsID);
			ResultSet rs = conAccess.pstmt.executeQuery();
			if(rs.next()){
				uSeller = rs.getString("uSellerId");
				price = rs.getString("uPrice");
				storage = rs.getInt("uStorage");
				if(storage>0){
					sql = "select * from tblBank where uID = ?";
					conAccess.pstmt = conAccess.conn.prepareStatement(sql);
					conAccess.pstmt.setString(1,userID);
					ResultSet rs1 = conAccess.pstmt.executeQuery();
					if(rs1.next()){
						String cardBalance = rs1.getString("cardBalance");
						double cBalance = Double.parseDouble(cardBalance);
						double goodPrice = Double.parseDouble(price);
						if(cBalance >= goodPrice){
							sql = "update tblBank set cardBalance = ?  where uID = ?";
							conAccess.pstmt = conAccess.conn.prepareStatement(sql);
							conAccess.pstmt.setString(1, Double.toString(cBalance-goodPrice));	
							conAccess.pstmt.setString(2,userID);					
							conAccess.pstmt.executeUpdate();
							
							sql = "update tblBank set bBalance = ?  where uID = ?";
							conAccess.pstmt = conAccess.conn.prepareStatement(sql);
							conAccess.pstmt.setString(1, Double.toString(cBalance+goodPrice));	
							conAccess.pstmt.setString(2,uSeller);					
							conAccess.pstmt.executeUpdate();
							
							sql = "update tblGoods set uStorage = uStorage-1  where ID = ?";
							conAccess.pstmt = conAccess.conn.prepareStatement(sql);	
							conAccess.pstmt.setString(1,goodsID);					
							conAccess.pstmt.executeUpdate();
							
							sql = "insert into tblHistory (uBuyer,uGoodsId,uGoodsName,uTime,uPrice,uSeller) values(?,?,?,?,?,?)";
							conAccess.pstmt = conAccess.conn.prepareStatement(sql);	
							conAccess.pstmt.setString(1,userID);
							conAccess.pstmt.setString(2,goodsID);
							conAccess.pstmt.setString(3,rs.getString("uGoodsName"));
							conAccess.pstmt.setString(4,uTime);
							conAccess.pstmt.setString(5,price);
							conAccess.pstmt.setString(6,uSeller);
							conAccess.pstmt.executeUpdate();
							
							if(userID.length()==8){
								sql = "select * from tblStu where uStuNumber = ?";
								conAccess.pstmt = conAccess.conn.prepareStatement(sql);
								conAccess.pstmt.setString(1,userID);
								ResultSet rs2 = conAccess.pstmt.executeQuery();
								if(rs2.next()){
									uDorm = rs2.getString("uDorm");
									uTel = rs2.getString("uTel");
								}
							}else if(userID.length() ==7){
								sql = "select * from tblTea where uId = ?";
								conAccess.pstmt = conAccess.conn.prepareStatement(sql);
								conAccess.pstmt.setString(1,userID);
								ResultSet rs2 = conAccess.pstmt.executeQuery();
								if(rs2.next()){
									uDorm = rs2.getString("uAddress");
									uTel = rs2.getString("uTel");
								}
							}
								
							
							MailData mData = new MailData("sendMail");
							mData.setmSenderID(userID);
							mData.setmRecipientID(uSeller);
							Date date = new Date();
							DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							mData.setmTime(format.format(date));
							mData.setmTopic("购买物品");
							mData.setmContent("您好，"+userID+"在"+mData.getmTime()+"购买了"+goodsID+goodsName+",宿舍地址为"+uDorm+"联系方式为"+uTel+"请尽快送货");
							
							if(storage == 1 && uSeller.length()!=6){
								sql = "delete from tblGoods where  uGoodsId = ?";
								conAccess.pstmt = conAccess.conn.prepareStatement(sql);
								conAccess.pstmt.setString(1,goodsID);				
								conAccess.pstmt.executeUpdate();
							}
							
							server.sendDataToClient(mData);
							
						}else{
							server.sendDataToClient(new RequestData("MoneyNotEnough"));
						}
					}
							
				}else{
					server.sendDataToClient(new RequestData("goodsNotEnough"));
				}
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
