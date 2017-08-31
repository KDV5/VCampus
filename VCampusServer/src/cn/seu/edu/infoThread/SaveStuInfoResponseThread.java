package cn.seu.edu.infoThread;

import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.InfoData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.ServerReaderThread;

public class SaveStuInfoResponseThread {
	ServerReaderThread server;
	InfoData infor;
	String id;
	public SaveStuInfoResponseThread(ServerReaderThread server,RequestData reqData){
		this.server = server;
		this.infor = (InfoData)reqData;
		this.id = infor.getStuNumber();
	}
	
	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		
		try {
			if(id.length() == 8){
			String sql = "update tblStu set uSchool = ?,uMajor = ?,uDorm = ?,uAge = ?,uRoot = ?,uTel = ?,uMail = ?,uAddress = ?,uSex = ?,uMark= ? where uStuNumber =? and uName = ?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1, infor.getSchool());
			conAccess.pstmt.setString(2, infor.getMajor());
			conAccess.pstmt.setString(3, infor.getDorm());
			conAccess.pstmt.setString(4, infor.getAge());
			conAccess.pstmt.setString(5, infor.getRoot());
			conAccess.pstmt.setString(6, infor.getTel());
			conAccess.pstmt.setString(7, infor.getMail());
			conAccess.pstmt.setString(8, infor.getAddress());
			conAccess.pstmt.setString(9, infor.getSex());
			conAccess.pstmt.setString(10, infor.getMark());
			conAccess.pstmt.setString(11, infor.getStuNumber());
			conAccess.pstmt.setString(12, infor.getName());
			conAccess.pstmt.executeUpdate();
			server.sendDataToClient(new RequestData("true"));
			}else if(id.length()==7){
		
				String sql="update tblTea set uSchool = ?,uMajor=? ,uAge = ?, uTel = ?,uMail = ?,uAddress = ?,uSex = ?,uMark = ? where uId =? and uName = ?";
				conAccess.pstmt = conAccess.conn.prepareStatement(sql);
					conAccess.pstmt =conAccess.conn.prepareStatement(sql);
				
					conAccess.pstmt.setString(1, infor.getSchool());
					conAccess.pstmt.setString(2, infor.getMajor());
					conAccess.pstmt.setString(3, infor.getAge());
					conAccess.pstmt.setString(4, infor.getTel());
					conAccess.pstmt.setString(5, infor.getMail());
					conAccess.pstmt.setString(6, infor.getAddress());
					conAccess.pstmt.setString(7, infor.getSex());
					conAccess.pstmt.setString(8, infor.getMark());
					conAccess.pstmt.setString(9, id);
					conAccess.pstmt.setString(10, infor.getName());
					conAccess.pstmt.executeUpdate();
					server.sendDataToClient(new RequestData("true"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			server.sendDataToClient(new RequestData("false"));
		}
		
	}

}
