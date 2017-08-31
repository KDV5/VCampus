package cn.seu.edu.shopThread;

import java.sql.SQLException;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.message.ShopData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;
import cn.seu.edu.helper.Helper;

public class AddNewGoodsResponseThread {
	ServerReaderThread server;
	ShopData sData;
	String uGoodsName;
	String uSellerID;
	String uPrice;
	String uDetail;
	int uStorage;
	ImageIcon icon;
	String ID;
	
	public AddNewGoodsResponseThread(ServerReaderThread server,RequestData reqData){
		this.server= server;
		this.sData = (ShopData)reqData;
		this.uGoodsName = sData.getuGoodsName();
		this.uSellerID = sData.getuSellerID();
		this.uPrice = sData.getuPrice();
		this.uDetail = sData.getuDetail();
		this.uStorage = sData.getuStorage();
		this.ID=sData.getuGoodsID();
		this.icon=sData.getIcon();
		
	}
	
	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			String sql = "insert into tblGoods (uSellerId,uGoodsName,uPrice,uStorage,uDetail) values(?,?,?,?,?)";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,uSellerID);
			conAccess.pstmt.setString(2,uGoodsName);
			conAccess.pstmt.setString(3,uPrice);
			conAccess.pstmt.setInt(4,uStorage);
			conAccess.pstmt.setString(5,uDetail);
			conAccess.pstmt.executeUpdate();
			String sql2 = "select @@IDENTITY AS currentID";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql2);
			ResultSet rs=conAccess.pstmt.executeQuery();
			if(rs.next()){
				ID=Long.toString(rs.getLong("currentID"));
				Image image = icon.getImage();					    
				BufferedImage image1 = Helper.toBufferedImage(image);
				ImageIO.write(image1, "jpg", new File("GoodsImage\\"+ID+".jpg"));
				server.sendDataToClient(new RequestData("true"));
			}else{
				server.sendDataToClient(new RequestData("false"));
			}
	
		} catch (Exception e) {
			server.sendDataToClient(new RequestData("false"));
			e.printStackTrace();
		}
	}

}
