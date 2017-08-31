package cn.seu.edu.eduThread;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.CourseData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;

public class EverChooseResponseThread {
	private ServerReaderThread server ;
	private CourseData courseData;
	private String stuID;
	private int day;
	
	public EverChooseResponseThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.courseData = (CourseData)reqData;
		this.stuID = courseData.getStuId();
		this.day = courseData.getDay();
		
	}

	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			String sql = "select*from tblCourse where uStuNumb= ? order by uTime asc";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,stuID);
			ResultSet rs1 = conAccess.pstmt.executeQuery();
			PreparedStatement stin2 = conAccess.conn.prepareStatement("select*from tblCourMessage where uCourNumb=?");
			ResultSet rs2 = null;
			CourseData cData = new CourseData("choosedCourse");
			while(rs1.next()){
				int time=rs1.getInt("uTime");
				int num=time%10-1;
				String cn=rs1.getString("uCourse");
				stin2.setString(1,cn);
				rs2=stin2.executeQuery();
				if(rs2.next()){
					if(time/10==day){
						cData.obj1[num][0]=rs2.getString("uCourTime");
						cData.obj1[num][1]=cn;
						cData.obj1[num][2]=rs2.getString("uCourName");
						cData.obj1[num][3]=rs2.getString("uCourTeacher");
						cData.obj1[num][4]=rs2.getInt("uTotal");
					}
					
				}
			}
			server.sendDataToClient(cData);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
