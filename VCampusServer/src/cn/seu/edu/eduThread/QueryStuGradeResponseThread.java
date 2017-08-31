package cn.seu.edu.eduThread;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.CourseData;
import cn.seu.edu.message.IdData;
import cn.seu.edu.message.MailData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;

public class QueryStuGradeResponseThread{
	private ServerReaderThread server ;
	private IdData idData;
	private String userID;
	
	public QueryStuGradeResponseThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.idData = (IdData)reqData;
		this.userID = idData.getId();
	}

	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			String sql = "select*from tblCourse where uStuNumb=? order by uTime asc";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,userID);
			ResultSet rs = conAccess.pstmt.executeQuery();
			
			PreparedStatement stin2=conAccess.conn.prepareStatement("select*from tblCourMessage where uCourNumb=?");
			ResultSet rs2;
			int c=0;
			
			CourseData cData = new CourseData("");
			while(rs.next() && c<24){
				String cn=rs.getString("uCourse");
				cData.obj[c][0]=cn;
				cData.obj[c][3]=rs.getString("uGrade");
				stin2.setString(1,cn);
				rs2=stin2.executeQuery();
				if(rs2.next()){
					cData.obj[c][1]=rs2.getString("uCourName");
					cData.obj[c][2]=rs2.getString("uCourTeacher");
				}
				
				c++;
			}
			server.sendDataToClient(cData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
