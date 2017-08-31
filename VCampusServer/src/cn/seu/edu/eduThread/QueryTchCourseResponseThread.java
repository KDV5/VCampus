package cn.seu.edu.eduThread;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.CourseData;
import cn.seu.edu.message.IdData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.ServerReaderThread;

public class QueryTchCourseResponseThread  {

	private ServerReaderThread server ;
	private IdData iData;
	private String tchId;
	
	public QueryTchCourseResponseThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.iData = (IdData)reqData;
		this.tchId =iData.getId();
	}
	

	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			String sql = "select*from tblTea where uId=?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,tchId);
			ResultSet rs = conAccess.pstmt.executeQuery();
			if(rs.next()){
				String teaName = rs.getString("uName");
				PreparedStatement stin1=conAccess.conn.prepareStatement("select*from tblCourMessage where uCourTeacher=? ");
				stin1.setString(1,teaName);
				ResultSet rs1=stin1.executeQuery();
				CourseData cData = new CourseData("");
				int i = 0;
				while(rs1.next()){
					cData.tchCourse[i]=rs1.getString("uCourNumb")+" "+rs1.getString("uCourName");
					i++;
				}
				server.sendDataToClient(cData);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
