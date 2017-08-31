package cn.seu.edu.eduThread;


import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.CourseData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;

public class CourseCanBeChooseResponseThread{
	private ServerReaderThread server ;
	private CourseData courseData;
	private int day;
	
	public CourseCanBeChooseResponseThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.courseData = (CourseData)reqData;
		this.day = courseData.getDay();
		
	}

	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			String sql = "select*from tblCourMessage where uTime like ?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,day+"_");
			ResultSet rs1 = conAccess.pstmt.executeQuery();
			
			CourseData cData = new CourseData("choosedCourse");
			int num=0;
			while(rs1.next()){
				cData.obj[num][0]=rs1.getString("uCourTime");
				cData.obj[num][1]=rs1.getString("uCourNumb");
				cData.obj[num][2]=rs1.getString("uCourName");
				cData.obj[num][3]=rs1.getString("uCourTeacher");
				cData.obj[num][4]=rs1.getInt("uTotal");

			    num++;
									
			}
			server.sendDataToClient(cData);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
