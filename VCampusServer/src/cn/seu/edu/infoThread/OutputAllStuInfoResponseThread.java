package cn.seu.edu.infoThread;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.InfoData;
import cn.seu.edu.message.ObjListData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.ServerReaderThread;

public class OutputAllStuInfoResponseThread extends Thread {

	ServerReaderThread server;
	public OutputAllStuInfoResponseThread(ServerReaderThread server,RequestData reqData){
		this.server = server;
	}
	
	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			String sql = "select * from tblStu";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			ResultSet rs = conAccess.pstmt.executeQuery();
			ObjListData listData = new ObjListData("");
			while(rs.next()){
				InfoData infor=new InfoData("");
				infor.setStuNumber(rs.getString("uStuNumber"));
				infor.setName(rs.getString("uName"));
				infor.setSchool(rs.getString("uSchool"));
				infor.setMajor(rs.getString("uMajor"));
				infor.setDorm(rs.getString("uDorm"));
				infor.setAge(rs.getString("uAge"));
				infor.setRoot(rs.getString("uRoot"));
				infor.setTel(rs.getString("uTel"));
				infor.setMail(rs.getString("uMail"));
				infor.setAddress(rs.getString("uAddress"));
				listData.getInfoList().add(infor);
				}
			server.sendDataToClient(listData);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
