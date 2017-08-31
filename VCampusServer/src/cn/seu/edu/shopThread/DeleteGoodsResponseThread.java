package cn.seu.edu.shopThread;

import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.IdData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;

public class DeleteGoodsResponseThread{
	private ServerReaderThread server ;
	private IdData idData;
	private String goodsID;
	
	public DeleteGoodsResponseThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.idData = (IdData)reqData;
		this.goodsID = idData.getId();
	}

	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		
		try {
			String sql = "delete from tblGoods where  ID = ?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,goodsID);				
			conAccess.pstmt.executeUpdate();
			server.sendDataToClient(new RequestData("true"));
		} catch (SQLException e) {
			e.printStackTrace();
			server.sendDataToClient(new RequestData("false"));
		}
	
	}
}
