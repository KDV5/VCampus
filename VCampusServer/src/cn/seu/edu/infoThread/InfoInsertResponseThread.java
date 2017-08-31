package cn.seu.edu.infoThread;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.InfoData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.ServerReaderThread;

public class InfoInsertResponseThread  {

	ServerReaderThread server;
	InfoData iData;
	String uName;
	String uID;
	String uTitle;
	
	public InfoInsertResponseThread(ServerReaderThread server, RequestData reqData){
		this.server = server;
		this.iData = (InfoData) reqData;
		this.uName = iData.getName();
		this.uID = iData.getStuNumber();
		this.uTitle = iData.getTitle();
	}
	
	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			if("".equals(uTitle)){
				String sql = "select * from tblStu where uStuNumber = ?";
				conAccess.pstmt = conAccess.conn.prepareStatement(sql);
				conAccess.pstmt.setString(1,uID);
				ResultSet rs = conAccess.pstmt.executeQuery();
				if(rs.next()){
					server.sendDataToClient(new RequestData("exist"));
				}else{
					sql = "insert into tblStu(uStuNumber,uName)values(?,?)";
					conAccess.pstmt = conAccess.conn.prepareStatement(sql);
					conAccess.pstmt.setString(1,uID);
					conAccess.pstmt.setString(2,uName);
					conAccess.pstmt.executeUpdate();
					
					sql = "insert into tblBank(uID,uName,bNumber,bPassword,bBalance,cradNumber,cardPassword,cardBalance)values(?,?,?,?,?,?,?,?)";
					conAccess.pstmt = conAccess.conn.prepareStatement(sql);
					conAccess.pstmt.setString(1,uID);
					conAccess.pstmt.setString(2,uName);
					conAccess.pstmt.setString(3,"62178561000"+uID);
					conAccess.pstmt.setString(4,"000000");
					conAccess.pstmt.setString(5,"1000");
					conAccess.pstmt.setString(6,"2"+uID);
					conAccess.pstmt.setString(7,"000000");
					conAccess.pstmt.setString(8,"1000");
					
					server.sendDataToClient(new RequestData("true"));
				}
			}else{
				String sql = "select * from tblTea where uId = ?";
				conAccess.pstmt = conAccess.conn.prepareStatement(sql);
				conAccess.pstmt.setString(1,uID);
				ResultSet rs = conAccess.pstmt.executeQuery();
				if(rs.next()){
					server.sendDataToClient(new RequestData("exist"));
				}else{
					sql = "insert into tblTea(uId,uName,uTitle)values(?,?,?)";
					conAccess.pstmt = conAccess.conn.prepareStatement(sql);
					conAccess.pstmt.setString(1,uID);
					conAccess.pstmt.setString(2,uName);
					conAccess.pstmt.setString(3,uTitle);
					conAccess.pstmt.executeUpdate();
					
					sql = "insert into tblBank(uID,uName,bNumber,bPassword,bBalance,cradNumber,cardPassword,cardBalance)values(?,?,?,?,?,?,?,?)";
					conAccess.pstmt = conAccess.conn.prepareStatement(sql);
					conAccess.pstmt.setString(1,uID);
					conAccess.pstmt.setString(2,uName);
					conAccess.pstmt.setString(3,"621785611000"+uID);
					conAccess.pstmt.setString(4,"000000");
					conAccess.pstmt.setString(5,"1000");
					conAccess.pstmt.setString(6,"1"+uID);
					conAccess.pstmt.setString(7,"000000");
					conAccess.pstmt.setString(8,"1000");
					
					server.sendDataToClient(new RequestData("true"));
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
			server.sendDataToClient(new RequestData("false"));
		}
	}
}
