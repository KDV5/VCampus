package cn.seu.edu.shopThread;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.helper.Helper;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.message.ShopData;
import cn.seu.edu.server.ServerReaderThread;

public class UpdateGoodsResponseThread{
	private ServerReaderThread server ;
	private ShopData shopData;
	private String goodsId;
	private String goodsName;
	private String price;
	private int storage;
	private String uDetail;

	
	public UpdateGoodsResponseThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.shopData = (ShopData)reqData;
		this.goodsId = shopData.getuGoodsID();
		this.goodsName = shopData.getuGoodsName();
		this.price = shopData.getuPrice();
		this.uDetail = shopData.getuDetail();
		this.storage = shopData.getuStorage();
	}
	
	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			String sql = "update tblGoods set uGoodsName = ? , uPrice= ? ,  uStorage= ? , uDetail = ? where ID = ?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,goodsName);
			conAccess.pstmt.setString(2,price);
			conAccess.pstmt.setInt(3,storage);
			conAccess.pstmt.setString(4,uDetail);
			conAccess.pstmt.setString(5,goodsId);
			conAccess.pstmt.executeUpdate();
			server.sendDataToClient(new RequestData("true"));
		} catch (Exception e) {
			e.printStackTrace();
			server.sendDataToClient(new RequestData("false"));
		}
	}
}
