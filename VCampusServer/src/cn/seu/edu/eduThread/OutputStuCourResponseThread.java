package cn.seu.edu.eduThread;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.CourseData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;

public class OutputStuCourResponseThread {
	private ServerReaderThread server ;
	private CourseData courseData;
	private String StuNumb;
	
	public OutputStuCourResponseThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.courseData = (CourseData)reqData;
		this.StuNumb = courseData.getStuId();	
	}

	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		PreparedStatement stin1;
		try {
			String sql = "select * from tblStu where uStuNumber = ?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,StuNumb);
			ResultSet rs = conAccess.pstmt.executeQuery();
			if(rs.next()){
			
				stin1 = conAccess.conn.prepareStatement("select*from tblCourse where uStuNumb=? order by uTime asc");
				stin1.setString(1,StuNumb);
				ResultSet rs1=stin1.executeQuery();
		
				PreparedStatement stin2=conAccess.conn.prepareStatement("select*from tblCourMessage where uCourNumb=?");
				ResultSet rs2;
			
				CourseData cData = new CourseData("true");
				cData.obj2[0][1]="周一";
				cData.obj2[0][2]="周二";
				cData.obj2[0][3]="周三";
				cData.obj2[0][4]="周四";
				cData.obj2[0][5]="周五";
				cData.obj2[1][0]="<html>"+"上午<br>1、2节";
				cData.obj2[2][0]="<html>"+"上午<br>3、4节";
				cData.obj2[3][0]="<html>"+"下午<br>1、2节";
				cData.obj2[4][0]="<html>"+"下午<br>3、4节";
				cData.obj2[5][0]="<html>"+"晚上<br>1、2节";
				String cn=null,nm=null,teacher = null,place=null;
				while(rs1.next()){
					cn=rs1.getString("uCourse");
					int time=rs1.getInt("uTime");
					stin2.setString(1,cn);
					rs2=stin2.executeQuery();
					if(rs2.next()){
						nm=rs2.getString("uCourName");
						teacher=rs2.getString("uCourTeacher");
						place=rs2.getString("uCourPlace");
					}
					cData.obj2[time%10][time/10]="<html>"+cn+"<br>"+nm+"<br>"+teacher+"<br>教学楼"+place;
			
				}
				server.sendDataToClient(cData);
			}else{
				server.sendDataToClient(new CourseData("false"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
